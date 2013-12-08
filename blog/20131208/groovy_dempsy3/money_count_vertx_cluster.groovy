@Grab('net.dempsy:lib-dempsyimpl:0.7.9')
@Grab('org.slf4j:slf4j-log4j12:1.7.5')
@Grab('io.vertx:vertx-core:2.1M1')
@Grab('io.vertx:lang-groovy:2.0.0-final')
import com.nokia.dempsy.annotations.*
import com.nokia.dempsy.*
import com.nokia.dempsy.config.*
import com.nokia.dempsy.output.*

import com.nokia.dempsy.config.ClusterId
import com.nokia.dempsy.cluster.zookeeper.ZookeeperSessionFactory
import com.nokia.dempsy.router.SpecificClusterCheck
import com.nokia.dempsy.router.DecentralizedRoutingStrategy
import com.nokia.dempsy.serialization.kryo.KryoSerializer
import com.nokia.dempsy.monitoring.coda.StatsCollectorFactoryCoda
import com.nokia.dempsy.messagetransport.tcp.TcpTransport

import org.vertx.groovy.core.Vertx
import org.vertx.groovy.core.http.RouteMatcher

import groovy.transform.*

@TupleConstructor
class Money {
	String moneyText

	@MessageKey
	String getMoneyText() {
		moneyText
	}
}

@MessageProcessor
@AutoClone
class MoneyCount {
	private long count = 0
	private String key

	@Activation
	void setMoneyKey(String key) {
		println "MoneyCount.activation : ${key}"
		this.key = key
	}

	@MessageHandler
	void countMoney(Money money) {
		count++
	}

	@Output
	void printResults() {
		println "key: ${key}, count: ${count}"
	}
}

class Constants {
	final static def MONEYS = [
		'1', '5', '10', '50', '100', '500', '1000', '2000', '5000', '10000'
	]
}

class MoneyAdaptor implements Adaptor {
	Dispatcher dispatcher

	void start() {
		println 'MoneyAdaptor.start ...'

		def vertx = Vertx.newVertx()

		def rm = new RouteMatcher()
		rm.get '/:money', { req ->
			def money = req.params['money']

			if (Constants.MONEYS.contains(money)) {
				dispatcher.dispatch(new Money(req.params['money']))
			}
			req.response.end()
		}

		vertx.createHttpServer().requestHandler(rm.asClosure()).listen 8080
	}

	void stop() {
		println 'MoneyAdaptor.stop'
	}
}

def mp = new ClusterDefinition('mp', new MoneyCount())
mp.outputExecuter = new com.nokia.dempsy.output.RelativeOutputSchedule(10, java.util.concurrent.TimeUnit.SECONDS)

def app = new ApplicationDefinition('money-count').add(
	new ClusterDefinition('adaptor', new MoneyAdaptor()),
	mp
)

def cluster = args[0]

def dempsy = new Dempsy()

dempsy.applicationDefinitions = [app]
dempsy.clusterSessionFactory = new ZookeeperSessionFactory('localhost:2181', 5000)
dempsy.clusterCheck = new SpecificClusterCheck(new ClusterId('money-count', cluster))
// mp �� 3�m�[�h�\���ŏ�������ݒ�
dempsy.defaultRoutingStrategy = new DecentralizedRoutingStrategy(5, 3)
dempsy.defaultSerializer = new KryoSerializer()
dempsy.defaultStatsCollectorFactory = new StatsCollectorFactoryCoda()
dempsy.defaultTransport = new TcpTransport()

dempsy.start()

Runtime.runtime.addShutdownHook { ->
	println 'shutdown ...'
	dempsy.stop()
}

// Adaptor ���I������̂�h�~�iMessageProcessor �̏ꍇ�͕s�v�j
dempsy.waitToBeStopped()

@Grapes([
	@GrabResolver(name = "clojars.org", root = "http://clojars.org/repo"),
	@Grab("storm:storm:0.6.1-rc")
])
import backtype.storm.Config
import backtype.storm.LocalCluster
import backtype.storm.spout.SpoutOutputCollector
import backtype.storm.task.TopologyContext
import backtype.storm.topology.IRichSpout
import backtype.storm.topology.IBasicBolt
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.BasicOutputCollector
import backtype.storm.topology.TopologyBuilder
import backtype.storm.tuple.Fields
import backtype.storm.tuple.Tuple
import backtype.storm.tuple.Values
import backtype.storm.utils.Utils

//���̓f�[�^
class StdInSpout implements IRichSpout {
	def collector
	def dataSet

	boolean isDistributed() {
		false
	}

	void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector
		dataSet = System.in.newReader().iterator()
	}

	void nextTuple() {
		if (dataSet.hasNext()) {
			//�W�����͂� 1�s�����f�[�^�Ƃ��Đݒ�
			collector.emit(new Values(dataSet.next()))
		}
	}

	void declareOutputFields(OutputFieldsDeclarer decl) {
		decl.declare(new Fields("money"))
	}

	void close() {}
	void ack(msgId) {}
	void fail(msgId) {}
}
//�J�E���g����
class CountBolt implements IBasicBolt {
	def counter = [:]

	void execute(Tuple input, BasicOutputCollector collector) {
		def money = input.getValueByField("money")
		def count = (counter.containsKey(money))? counter.get(money): 0

		count++
		counter.put(money, count)

		collector.emit(new Values(money, count))
	}

	void declareOutputFields(OutputFieldsDeclarer decl) {
		decl.declare(new Fields("money", "count"))
	}

	void prepare(Map conf, TopologyContext context) {}
	void cleanup() {}
}
//�o�͏���
class PrintBolt implements IBasicBolt {
	void execute(Tuple input, BasicOutputCollector collector) {
		def money = input.getValueByField("money")
		def count = input.getValueByField("count")

		println "${money} = ${count}"
	}

	void prepare(Map conf, TopologyContext context) {}
	void cleanup() {}
	void declareOutputFields(OutputFieldsDeclarer decl) {}
}


def builder = new TopologyBuilder()

builder.setSpout("sp1", new StdInSpout())
//Bolt �̓V���A���C�Y����āA�e���[�J�[�ɓn����邽�߁A
//fieldsGrouping �œ���� money �𓯂����[�J�[�ŏ�������悤�w�肷��
builder.setBolt("bo1", new CountBolt(), 4).fieldsGrouping("sp1", new Fields("money"))
builder.setBolt("bo2", new PrintBolt(), 4).shuffleGrouping("bo1")

def conf = new Config()
conf.debug = false
//���[�J�����[�h�p�� Cluster
def cluster = new LocalCluster()
//Topology ��ݒ�
cluster.submitTopology("moneycount", conf, builder.createTopology())

//Topology �͏I�����Ȃ����� 5�b�҂�����ŃV���b�g�_�E���J�n
Utils.sleep(5000)

//Topology �̍폜
cluster.killTopology("moneycount")

//���O�t�@�C���̍폜�Ɏ��s���� IOException �� throw ����̂�
//try-catch ���Ă���
try {
	//�V���b�g�_�E��
	cluster.shutdown()
} catch (e) {
	println e
}

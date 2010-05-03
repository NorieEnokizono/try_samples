
import org.apache.camel.Processor
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder

class SampleRoute extends RouteBuilder {
	void configure() {
		from("direct:start").to("esper:test")

		//1�b�Ԃɔ��������C�x���g���܂Ƃ߂ď���
		from("esper:test?eql=select count(*) as counter, sum(value) as total from TestEvent.win:time_batch(1 sec)").process({println "*** 1 time_batch, counter: ${it.in.body.counter}, total: ${it.in.body.total}"} as Processor)

		//
		from("esper:test?eql=select count(*) as counter from TestEvent.win:time(1 sec)").process({println "--- 2 time counter: ${it.in.body.counter}"} as Processor)

		//�C�x���g�� 3���������珈��
		from("esper:test?eql=select count(*) as counter from TestEvent(value > 4).win:length_batch(3)").process({println "=== 3 length_batch counter: ${it.in.body.counter}"} as Processor)

		//�C�x���g�������ɏ����i3�܂ōŋ߂̃C�x���g��ێ��j
		from("esper:test?eql=select count(*) as counter from TestEvent.win:length(3)").process({println "+++ 4 length counter: ${it.in.body.counter}"} as Processor)

	}
}

class TestEvent {
	String name
	int value
}

ctx = new DefaultCamelContext()
ctx.addRoutes(new SampleRoute())

template = ctx.createProducerTemplate()

println "start"

ctx.start()

(1..10).each {
	template.sendBody("direct:start", new TestEvent(name: "test${it}", value: it))
	Thread.sleep(300);
}

Thread.sleep(1000);

ctx.stop()

println "stop"

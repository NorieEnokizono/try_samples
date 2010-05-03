
import org.apache.camel.Processor
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder

class SampleRoute extends RouteBuilder {
	void configure() {

		from("jetty:http://localhost/test").to("direct:response")

		from("direct:response").process({

			def req = it.in.getBody(javax.servlet.http.HttpServletRequest.class)
			def id = req.getParameter("id")

			it.out.setBody("<html><body><h1>id=${id}</h1></body></html>", String.class)
			//�ȉ��̂悤�ȋL�q�ł̓t�@�C�����o�͂���Ȃ����ߒ���
			//it.out.body = "<html><body><h1>id=${id}</h1></body></html>"
		} as Processor).to("file:logs")

		from("file:logs").process({
			//�t�@�C�����ƃt�@�C�����e���o��
			println "${it.file.name} : ${it.file.text}"
		} as Processor)
	}
}

ctx = new DefaultCamelContext()

ctx.addRoutes(new SampleRoute())

ctx.start()

println "start Camel"

System.in.read()

ctx.stop()


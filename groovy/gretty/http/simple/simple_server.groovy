/*
 * Gretty sample
 *   Groovy 1.8 �Ŏ��s����K�v����
 *   Groovy 2.0 �ł� ExceptionInitilizerError ����������
 *
 */
@GrabResolver(name = 'gretty', root = 'http://groovypp.artifactoryonline.com/groovypp/libs-releases-local/')
@Grab('org.mbte.groovypp:gretty:0.4.302')
import org.mbte.gretty.httpserver.*

GrettyServer server = []

server.groovy = [
	localAddress: new InetSocketAddress('localhost', 8080),
	defaultHandler: {
		response.redirect '/'
	},
	'/': {
		get {
			response.html = 'hello'
		}
	},
	'/:name': {
		get {
			response.text = "param : ${request.parameters['name']}"
		}
	}
]

server.start()

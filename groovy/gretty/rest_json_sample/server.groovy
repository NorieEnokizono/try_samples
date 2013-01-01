/*
 *  Groovy 1.8 �Ŏ��s����K�v����
 *  Groovy 2.0 �ł� ExceptionInitilizerError ����������
 */
@GrabResolver(name = 'gretty', root = 'http://groovypp.artifactoryonline.com/groovypp/libs-releases-local/')
@Grab('org.mbte.groovypp:gretty:0.4.302')
import static org.mbte.gretty.JacksonCategory.*
import org.mbte.gretty.httpserver.GrettyServer

GrettyServer server = []

server.groovy = [
	localAddress: new InetSocketAddress('localhost', 8080),
	defaultHandler: {
		response.redirect '/'
	},
	'/user/:id': {
		get {
			response.json = [
				id: request.parameters['id'],
				name: 'test'
			]
		}
	},
	'/user': {
		post {
			def data = fromJson(Map, request.contentText)
			println data

			response.json = ''
		}
	}
]

server.start()

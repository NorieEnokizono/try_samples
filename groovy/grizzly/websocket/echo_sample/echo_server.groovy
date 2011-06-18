
import org.glassfish.grizzly.http.server.*
import org.glassfish.grizzly.http.HttpRequestPacket
import org.glassfish.grizzly.websockets.*
import org.glassfish.grizzly.websockets.frame.*

/**
 * Grizzly 2.0.1�p �� WebSocket �T���v��
 *
 * Grizzly 2.1 �ȍ~��
 * WebSocket draft-ietf-hybi-thewebsocketprotocol-06 �Ή���
 * �ύX����Ă��邽�߉��L�̃X�N���v�g�͓��삵�Ȃ�
 *
 */
class EchoWebSocketApplication extends WebSocketApplication {
	boolean isApplicationRequest(HttpRequestPacket req) {
		println("${req}")
		true
	}

	void onMessage(WebSocket websocket, Frame data) {
		println("onMessage : ${data}")
		websocket.send(Frame.createTextFrame("echo : ${data.asText}"))
	}

	void onConnect(WebSocket websocket) {
		println("onConnect : ${websocket}")
		super.onConnect(websocket)
	}

	void onClose(WebSocket websocket) {
		println("onClose : ${websocket}")
		super.onClose(websocket)
	}
}

def server = HttpServer.createSimpleServer()

server.getListener("grizzly").registerAddOn(new WebSocketAddOn())

WebSocketEngine.engine.registerApplication("/sample", new EchoWebSocketApplication())

server.start()

System.in.read()

server.stop()

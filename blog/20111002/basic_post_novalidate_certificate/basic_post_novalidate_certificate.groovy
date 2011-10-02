import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

def url = new URL(args[0])
def user = args[1]
def pass = args[2]
def postData = args[3]

//Basic�F�؁igetPasswordAuthentication() ���I�[�o�[���C�h�j
Authenticator.default = {
	new PasswordAuthentication(user, pass.toCharArray())
} as Authenticator

// �z�X�g�������؂��Ȃ��悤�ɂ���ݒ�
//�i���jopenConnection ����O�ɐݒ肵�Ă����K�v����
HttpsURLConnection.defaultHostnameVerifier = {
	host, session -> true
} as HostnameVerifier

def con = url.openConnection()
con.doOutput = true
con.requestMethod = "POST"

//SSL �ؖ��������؂��Ȃ����߂̐ݒ�
def sslctx = SSLContext.getInstance("SSL")
def tmanager = [
	checkClientTrusted: {chain, authType -> },
	checkServerTrusted: {chain, authType -> },
	getAcceptedIssuers: {null}
] as X509TrustManager

sslctx.init(null as KeyManager[], [tmanager] as X509TrustManager[], new SecureRandom())

con.SSLSocketFactory = sslctx.socketFactory

//POST�f�[�^�̏o��
con.outputStream.withWriter {
	it.print postData
}

//���ʂ̏o��
print con.inputStream.text

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

if (args.length < 4) {
	println "${new File(System.getProperty('script.name')).name} <url> <basic user> <basic password> <post data>"
	return
}

def url = new URL(args[0])
def user = args[1]
def pass = args[2]
def post_data = args[3]

//Basic�F�ؗp�̐ݒ�igetPasswordAuthentication() ���I�[�o�[���C�h�j
Authenticator.default = {
	new PasswordAuthentication(user, pass.toCharArray())
} as Authenticator

//SSL �ؖ����𖳎����邽�߂̐ݒ�iHostnameVerifier �C���^�[�t�F�[�X�������j
//�i���jopenConnection ����O�ɐݒ肵�Ă����K�v����
HttpsURLConnection.defaultHostnameVerifier = {
	host, session -> true
} as HostnameVerifier

def con = url.openConnection()
con.doOutput = true
con.requestMethod = "POST"

//SSL �ؖ����𖳎����邽�߂̐ݒ�
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
	it.print post_data
}

//�w�b�_�[�o��
con.headerFields each {
	println it
}

//���X�|���X�o��
println con.inputStream.text

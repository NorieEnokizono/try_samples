
import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

/**
 * �M������Ȃ��ؖ����� HTTPS ��Basic�F�؂��g���� POST ����T���v��
 */
public class BasicPostNovalidateCertificate {
	public static void main(String[] args) throws Exception {

		URL url = new URL(args[0]);
		final String user = args[1];
		final String pass = args[2];
		String postData = args[3];

		//Basic�F��
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass.toCharArray());
			}
		});

		// �z�X�g�������؂��Ȃ��悤�ɂ���ݒ�
		//�iopenConnection ����O�ɐݒ肵�Ă����K�v����j
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String host, SSLSession ses) {
				return true;
			}
		});

		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");

		//SSL �ؖ��������؂��Ȃ����߂̐ݒ�
		SSLContext sslctx = SSLContext.getInstance("SSL");
		sslctx.init(null, new X509TrustManager[] {
			new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
				}
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
				}
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			}
		}, new SecureRandom());

		con.setSSLSocketFactory(sslctx.getSocketFactory());

		//POST�f�[�^�̏o��
		OutputStream os = con.getOutputStream();
		PrintStream ps = new PrintStream(os);
		ps.print(postData);
		ps.close();

		//���ʂ̏o��
		InputStream is = con.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);

		int len = 0;
		byte[] buf = new byte[1024];

		while ((len = bis.read(buf, 0, buf.length)) > -1) {
			System.out.write(buf, 0, len);
		}

		bis.close();
	}
}

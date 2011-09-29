
import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

/**
 * Basic�F�؂��g���� SSL �̏ؖ����𖳎����� HTTPS �� POST ����T���v��
 *
 */
public class BasicPostIgnoreCertificate {
	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.out.println("java BasicPostIgnoreCertificate <url> <basic user> <basic password> <post data>");
			return;
		}

		//https �� URL
		URL url = new URL(args[0]);

		final String user = args[1];
		final String pass = args[2];
		String postData = args[3];

		//Basic�F�ؗp�̐ݒ�
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass.toCharArray());
			}
		});

		//SSL �ؖ����𖳎����邽�߂̐ݒ�
		//�iopenConnection ����O�ɐݒ肵�Ă����K�v����j
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String host, SSLSession ses) {
				return true;
			}
		});

		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");

		//SSL �ؖ����𖳎����邽�߂̐ݒ�
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();
	}
}

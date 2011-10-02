using System;
using System.Net;

/**
 * �M������Ȃ��ؖ����� HTTPS ��Basic�F�؂��g���� POST ����T���v��
 */
class BasicPostNovalidateCertificate
{
	public static void Main(string[] args)
	{
		var url = args[0];
		var user = args[1];
		var pass = args[2];
		var postData = args[3];

		//SSL �ؖ��������؂��Ȃ��悤�ɂ���ݒ�
		//�i�ؖ��������ł��󂯓����悤�ɂ���j
		ServicePointManager.ServerCertificateValidationCallback = 
			(sender, cert, chain, errors) => true;

		using (WebClient wc = new WebClient())
		{
			//Basic�F��
			wc.Credentials = new NetworkCredential(user, pass);

			//Post
			var res = wc.UploadString(url, "POST", postData);

			//���ʂ̏o��
			Console.Write(res);
		}
	}
}

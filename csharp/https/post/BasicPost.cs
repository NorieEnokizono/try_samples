using System;
using System.Net;

class BasicPost
{
	public static void Main(string[] args)
	{
		var url = args[0];
		var user = args[1];
		var pass = args[2];
		var postData = args[3];

		//SSL�ؖ����𖳎�
		ServicePointManager.ServerCertificateValidationCallback = 
			(sender, cert, chain, errors) => true;

		WebClient wc = new WebClient();

		//Basic�F��
		wc.Credentials = new NetworkCredential(user, pass);

		//Post
		var res = wc.UploadString(url, "POST", postData);

		Console.Write(res);
	}
}

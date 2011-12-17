
using System;
using System.Data;
using Oracle.DataAccess.Client;

/*
 * Oracle Data Provider for .NET (ODP.NET�j���g���� DB �ڑ��T���v��
 *
 * �������A���s����ɂ� Oracle.DataAccess.dll ���J�����g�f�B���N�g���ɕK�v
 */
class ODPSample
{
	public static void Main(string[] args)
	{
		using (var con = new OracleConnection("Data Source=(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=127.0.1.2)(PORT=1522)))(CONNECT_DATA=(SERVICE_NAME=TEST))); User ID=U1; Password=P1"))
		{
			try
			{
				con.Open();

				using (var cmd = new OracleCommand("select * from CUSTOMER", con)) 
				{
					var reader = cmd.ExecuteReader();

					while (reader.Read())
					{
						Console.WriteLine("customer : {0}", reader.GetString(0));
					}

					reader.Close();
				}
			}
			catch (Exception ex) {
				Console.WriteLine(ex.StackTrace);
			}
		}
	}
}


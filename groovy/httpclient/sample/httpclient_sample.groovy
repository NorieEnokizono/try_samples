@Grab("org.apache.httpcomponents:httpclient:4.2.1")
import org.apache.http.client.methods.*
import org.apache.http.impl.client.*

def req = new HttpGet(args[0])

// �w�b�_�[��ݒ肷��ɂ� setHeader �� addHeader ���g�p
// req.setHeader('Host', 'xxx')

def httpClient = new DefaultHttpClient()
def res = httpClient.execute(req)

println res

/* �R���e���c�̓��e���擾����ɂ� getContent �� InputStream ���擾���邩
 * writeTo �� OutputStream �ɏ�������
 */
//println res.entity.content.text
/*
new File("dest.html").withOutputStream {
	res.entity.writeTo(it)
}
*/

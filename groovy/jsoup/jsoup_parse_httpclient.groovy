@Grab("org.apache.httpcomponents:httpclient:4.3.1")
@Grab("org.jsoup:jsoup:1.7.3")
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.jsoup.Jsoup

if (args.length < 2) {
	println '<url> <selector>'
	return
}

def http = new DefaultHttpClient()

def res = http.execute(new HttpGet(args[0]))

// �����R�[�h�� null �ɂ���� meta �^�O���當���R�[�h�𔻒�
def doc = Jsoup.parse(res.entity.content, null, '')

println doc.select(args[1]).dump()

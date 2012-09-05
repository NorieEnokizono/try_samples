@GrabResolver(name = 'sardine-google-svn-repo', root = 'http://sardine.googlecode.com/svn/maven')
@Grab('com.googlecode.sardine:sardine:314')
@Grab('org.slf4j:slf4j-nop:1.6.6')
/*
 * �ȉ��̃G���[���������邽��
 *   download failed �E�E�Ehttpclient;4.1.1!httpclient.jar,
 *   download failed �E�E�Ecommons-codec;1.4!commons-codec.jar
 *
 *  .groovy/grapes/com.googlecode.sardine/sardine/ivy-314.xml ��
 *  �ȉ��̂悤�ɕҏW
 *
 *    (1) httpclient �� httpcore �̃o�[�W������ 4.2.1 �ɕύX
 *    (2) commons-codec �̃o�[�W������ 1.6 �ɕύX
 *
 */
import com.googlecode.sardine.*

if (args.length < 3) {
	println "groovy listup_sardine.groovy <url> <user> <password>"
	return
}

def url = args[0]
def user = args[1]
def pass = args[2]

def sardine = SardineFactory.begin(user, pass)

sardine.list(url).each {
	println it
}


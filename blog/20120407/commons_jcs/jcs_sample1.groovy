@Grapes([
	@Grab('org.apache.jcs:jcs:1.3'),
	@GrabExclude('logkit#logkit'),
	@GrabExclude('avalon-framework#avalon-framework')
])
import org.apache.jcs.JCS

//��莞�ԂŖ����ɂȂ�L���b�V��
def jcs = JCS.getInstance('sample1')

jcs.put('data1', 'cached_data')

Thread.sleep(2000)

println "2�b��F ${jcs.get('data1')}"

Thread.sleep(2000)

println "�����2�b��F ${jcs.get('data1')}"

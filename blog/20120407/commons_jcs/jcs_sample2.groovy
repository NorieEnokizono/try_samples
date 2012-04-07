@Grapes([
	@Grab('org.apache.jcs:jcs:1.3'),
	@GrabExclude('logkit#logkit'),
	@GrabExclude('avalon-framework#avalon-framework')
])
import org.apache.jcs.JCS

//��莞�ԎQ�Ƃ���Ȃ��ƍ폜�����L���b�V��
def jcs = JCS.getInstance('sample2')

jcs.put("data1", "cached_data1")
jcs.put("data2", "cached_data2")

println "data1 = ${jcs.get('data1')}, data2 = ${jcs.get('data2')}"

Thread.sleep(2000)

println "2�b��F data1 = ${jcs.get('data1')}"

Thread.sleep(4500)

println "�����4.5�b��F data1 = ${jcs.get('data1')}, data2 = ${jcs.get('data2')}"

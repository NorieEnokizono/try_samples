@Grapes([
	@Grab('org.apache.jcs:jcs:1.3'),
	@GrabExclude('logkit#logkit'),
	@GrabExclude('avalon-framework#avalon-framework')
])
import org.apache.jcs.JCS

def check = {cache, key, expected, info ->
	def res = cache.get(key) == expected
	println "$info - get('$key') == $expected : $res"
}

//�����ԓ��ɃA�N�Z�X���Ȃ���Ώ�����L���b�V��
def jcs = JCS.getInstance("sample2")

jcs.put("shrinker1", "test")
jcs.put("shrinker2", "no")

Thread.sleep(2000)

check jcs, "shrinker1", "test", "2�b��"

Thread.sleep(3000)

check jcs, "shrinker1", "test", "3�b��"

Thread.sleep(2000)

check jcs, "shrinker1", "test", "2�b��"
check jcs, "shrinker2", null, "7�b��"

Thread.sleep(5000)

check jcs, "shrinker1", null, "5�b��"


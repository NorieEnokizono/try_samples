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

//�L�������iMaxLifeSeconds�j�̂���L���b�V��
def jcs = JCS.getInstance("sample1")

Thread.sleep(5000)

jcs.put("life1", "abc")

Thread.sleep(2000)

check jcs, "life1", "abc", "2�b��"

Thread.sleep(7000)

jcs.put("life2", 123)

check jcs, "life1", "abc", "9�b��"

Thread.sleep(2000)

//�L�������؂�
check jcs, "life1", null, "11�b��"

check jcs, "life2", 123, "2�b��"

Thread.sleep(8000)

//�L�������؂�
check jcs, "life2", null, "10�b��"


interface Tester {
	def test(msg)
}

class User {
	def name
	@Delegate Tester tester = {msg -> println "test- $name - $msg"} as Tester
}

def u = new User(name: "abc")
u.test("check")

//User �� Tester �C���^�[�t�F�[�X�̎����N���X�����ƂȂ�
println u instanceof Tester

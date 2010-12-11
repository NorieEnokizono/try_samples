
class UserFeature {
	def name

	def test(msg) {
		"$name - $msg"
	}
}

class User {
	@Delegate UserFeature feature = new UserFeature()

	void printState() {
		println name
	}
}

def u = new User()
u.feature.name = "test"

u.printState()

//u.feature.test() �����s�����
println u.test("abc")

//u.feature.name ���擾�����
println "${u.name}"

println "--- methods ---"
println User.metaClass.methods

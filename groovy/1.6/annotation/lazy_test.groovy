
class User {
	@Lazy name = "test"
}

def u = new User()

//���̒i�K�ł� name �� null
println u.dump()

//�Q�b�^�[���\�b�h�Ăяo���iname �������j
println u.name

//name �ɒl�������Ă���
println u.dump()

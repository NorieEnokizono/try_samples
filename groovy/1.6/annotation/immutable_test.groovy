
@Immutable class TestData {
	int number
	String name

	//def ���ƃG���[������
	//def name

	/* �R���X�g���N�^�ȊO�Ńv���p�e�B�͕ύX�ł��Ȃ�
	def changeName(newName) {
		name = newName
	}
	*/

}

def t1 = new TestData(10, "test")
def t2 = new TestData(number: 10, name: "test")

println t1 == t2

//���[�h�I�����[�̂��߃v���p�e�B�͕ύX�ł��Ȃ�
//t2.name = "a"

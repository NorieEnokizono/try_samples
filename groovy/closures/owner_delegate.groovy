
def cl1 = {arg1, arg2 ->
	println "owner = $owner"
	println "delegate = $delegate"
	println "arg = $arg1, $arg2"
}

def cl2 = cl1.clone()

cl1(1, 2)

cl2.owner = "test1"
cl2.delegate = "test2"

// owner �͕ς��Ȃ��� delegate �͐ݒ肵���l�ɕς��
cl2(2, 3)


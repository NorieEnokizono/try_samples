
class Customer {
	String name
	//addresses �ł͖��� �N���X�� + 's' �Ƃ��Ȃ����
	//ObjectGraphBuilder �ŏ�������Ȃ�
	List<Address> addresss = []
}

class Address {
	String city
}


def builder = new ObjectGraphBuilder()

def c1 = builder.customer(name: '�e�X�g���[�U�[') {
	address(city: '����')
	address(city: '���')
}

println c1.dump()
// <Customer@aeba3ff name=�e�X�g���[�U�[ addresss=[Address@5c508d73, Address@2c76a85e]>

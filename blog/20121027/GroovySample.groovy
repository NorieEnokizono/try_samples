
def items = [
	new ProductItem("id1", "���i1", new BigDecimal("1000"), 1),
	new ProductItem("id2", "���i2", new BigDecimal("3000"), 2),
	new ProductItem("id3", "���i3", new BigDecimal("1500"), 3)
]
// (1) �}�b�s���O
def names = items.collect { it.name }

names.each { println it }

println "-----"

// (2) �t�B���^�����O
def highItems = items.findAll { it.getPrice().compareTo(new BigDecimal("1500")) >= 0 }

highItems.each { println it.name }

println "-----"

// (3) ��ݍ���
def total = items.inject(0) {a, b ->
	a + b.price * b.qty
}

println total

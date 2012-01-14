@Grab("org.drools:drools-core:5.4.0.Beta1")
@Grab("org.drools:drools-compiler:5.4.0.Beta1")
@Grab("com.sun.xml.bind:jaxb-xjc:2.2.5-b09")
import org.drools.KnowledgeBaseFactory
import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType
import org.drools.io.ResourceFactory

//���i
class Product {
	String category
	String name
	int price
}
//�Z�b�g���i
class SetProduct extends Product {
	List<Product> productList = []

	SetProduct(String name, int price) {
		this.name = name
		this.price = price
	}

	String getName() {
		super.getName() + "(" +productList.collect{it.name}.join(", ") + ")"
	}
}

//����
class Order {
	String orderNo
	List<OrderItem> itemList = []

	int getTotalPrice() {
		itemList.inject(0) {acc, item -> acc + item.totalPrice}
	}
}

//��������
class OrderItem {
	Product product
	int qty = 1

	OrderItem(Product product) {
		this.product = product
	}

	int getTotalPrice() {
		qty * product.price
	}
}

//�������i
class OrderProduct {
	Product product
	boolean done = false
}

//���[���G���W���̃Z�b�V�����쐬
def createSession = {drlFilePath ->
	def builder = KnowledgeBuilderFactory.newKnowledgeBuilder()

	builder.add(ResourceFactory.newClassPathResource(drlFilePath, getClass()), ResourceType.DRL)

	if (builder.hasErrors()) {
		println builder.errors
		System.exit(1)
	}

	def base = KnowledgeBaseFactory.newKnowledgeBase()
	base.addKnowledgePackages(builder.getKnowledgePackages())

	base.newStatefulKnowledgeSession()
}


def inputData = [
	[product: new Product(category: "A", name: "���i1", price: 4000), qty: 1],
	[product: new Product(category: "B", name: "���i2", price: 3000), qty: 4],
	[product: new Product(category: "C", name: "���i3", price: 3500), qty: 3],
	[product: new Product(category: "A", name: "���i4", price: 4500), qty: 2],
	[product: new Product(category: "D", name: "���i5", price: 4500), qty: 3]
]

def session = createSession("set_discount.drl")

def order = new Order(orderNo: "order:001")

session.insert(order)

inputData.each {item ->
	(0..<item.qty).each {
		session.insert(new OrderProduct(product: item.product))
	}
}

session.fireAllRules()
session.dispose()

println "���v���z = ${order.totalPrice}"

order.itemList.each {
	println "���� : <${it.product.category}> ${it.product.name} x ${it.qty} = ${it.totalPrice}"
}

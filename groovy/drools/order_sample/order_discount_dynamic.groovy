@Grab("org.drools:drools-core:5.4.0.Beta1")
@Grab("org.drools:drools-compiler:5.4.0.Beta1")
@Grab("com.sun.xml.bind:jaxb-xjc:2.2.5-b09")
import org.drools.KnowledgeBaseFactory
import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType
import org.drools.io.ResourceFactory

import groovy.transform.AutoClone
import groovy.text.GStringTemplateEngine

/*
 * Drools �̃��[����` DRL ���e���v���[�g�G���W�����g���ē��I�ɍ쐬����
 * ���[����K�p����T���v���X�N���v�g
 *
 */

//���[����`�̃e���v���[�g
def drl = '''
dialect "mvel"

<% patterns.each { %>
	rule "${it.title}"
		no-loop
		salience ${it.priority}
		when
			\\$order : Order()
			<% it.condition.eachWithIndex {c, i -> %>
				\\$item${i} : Product(${c}
					<% if (i > 0) { %>
					, this not in (<%= (0..<i).collect{"\\$item${it}"}.join(',') %>)
					<% } %>
					, discount == false)
			<% } %>
		then
			\\$cp = new DiscountCampaign("${it.title}")
			\\$cp.${it.action}

			<% it.condition.eachWithIndex {c, i -> %>
				\\$cp.getItemList().add(\\$item${i})
				\\$item${i}.setDiscount(true)
			<% } %>

			\\$order.getPriceList().add(\\$cp)

			update(\\$order)

			<% it.condition.eachWithIndex {c, i -> %>
				update(\\$item${i})
			<% } %>
	end
<% } %>
'''


interface Pricer {
	String getName()
	int totalPrice()
}

//���i
@AutoClone
class Product implements Pricer {
	def category
	def id
	String name
	int price = 0
	boolean discount = false

	int totalPrice() {
		price
	}
}

//����
class Order implements Pricer {
	String name = ""
	List<OrderItem> itemList = []
	List<Pricer> priceList = []

	int totalPrice() {
		calculate(priceList.isEmpty()? itemList: priceList)
	}

	private int calculate(list) {
		list.inject(0) {acc, val -> acc + val.totalPrice()}
	}
}

//��������
class OrderItem implements Pricer {
	String name = ""
	Product product
	int qty

	int totalPrice() {
		qty * product.totalPrice()
	}
}

//�����L�����y�[��
class DiscountCampaign implements Pricer {
	def baseName = ""
	def price = 0
	def rate = 0.0
	List<Product> itemList = []

	DiscountCampaign(String baseName) {
		this.baseName = baseName
	}

	String getName() {
		"${baseName} - [" + itemList.collect{"${it.name}"}.join(', ') + "]"
	}

	int totalPrice() {
		(rate == 0.0)? price: itemList.inject(0) {acc, val -> acc + val.totalPrice()} * rate
	}
}

//�����L�����y�[���K�p
class DiscountCampaignBuilder {
	def drlString
	def bindParams = [:]

	def apply(Order order) {
		def session = createSession()

		if (session) {
			session.insert(order)

			def items = divideItems(order.itemList)

			items.each {
				session.insert(it)
			}

			//���[���̓K�p
			session.fireAllRules()
			session.dispose()

			//�����L�����y�[�����K�p����Ȃ��������i��ǉ�
			order.priceList += items.findAll {it.discount == false}
		}
	}

	//�������ׂ����i 1�_�P�ʂɕ����i���i�̍������j
	def divideItems(itemList) {
		itemList.collect {item ->
			(0..<item.qty).collect {
				item.product.clone()
			}
		}.flatten().sort({a, b -> b.totalPrice() <=> a.totalPrice()})
	}

	def createSession() {
		def builder = KnowledgeBuilderFactory.newKnowledgeBuilder()

		//�e���v���[�g�������s���ă��[����`�𐶐�
		def drl = new GStringTemplateEngine().createTemplate(drlString).make(bindParams).toString()

		println drl

		builder.add(ResourceFactory.newReaderResource(new StringReader(drl)), ResourceType.DRL)

		if (builder.hasErrors()) {
			println builder.errors
			return
		}

		def base = KnowledgeBaseFactory.newKnowledgeBase()
		base.addKnowledgePackages(builder.getKnowledgePackages())

		base.newStatefulKnowledgeSession()
	}
}


def order = new Order()

def item1 = new OrderItem(qty: 4, product: new Product(category: "A", id: "001", name: "�e�X�g���i1", price: 4000))
order.itemList.add(item1)

def item2 = new OrderItem(qty: 3, product: new Product(category: "B", id: "002", name: "�e�X�g���i2", price: 3000))
order.itemList.add(item2)

def item3 = new OrderItem(qty: 6, product: new Product(category: "C", id: "003", name: "�e�X�g���i3", price: 3500))
order.itemList.add(item3)

def item4 = new OrderItem(qty: 2, product: new Product(category: "D", id: "004", name: "�e�X�g���i4", price: 4500))
order.itemList.add(item4)


def builder = new DiscountCampaignBuilder(drlString: drl)

//�����L�����y�[���̃p�����[�^��`
builder.bindParams["patterns"] = [
	[
		title: "�Z�b�g�L�����y�[��1�iA1�_ + B1�_�j",
		priority: 30,
		condition: [
			'category == "A"',
			'category == "B"'
		], 
		action: 'setPrice(5500)'
	],
	[
		title: "�Z�b�g�L�����y�[��2�iC1�_ + A1�_�j",
		priority: 20,
		condition: [
			'category == "C"',
			'category == "A"'
		], 
		action: 'setPrice(6000)'
	],
	[
		title: "�Z�b�g�L�����y�[��3�iC1�_ + A1�_ + ���ł� 1�_�j",
		priority: 20,
		condition: [
			'category == "C"',
			'category == "A"',
			'true'
		], 
		action: 'setRate(0.8)'
	],
	[
		title: "�Z�b�g�L�����y�[��4�iA�ȊO�� 4000�~�ȏ� 1�_�� A�ȊO 1�_�j",
		priority: 10,
		condition: [
			'category != "A" && price >= 4000',
			'category != "A"'
		], 
		action: 'setRate(0.9)'
	]
]

def beforePrice = order.totalPrice()

//�����L�����y�[���K�p
builder.apply(order)

println "�����O ���v���z = ${beforePrice}"
println "������ ���v���z = ${order.totalPrice()}"

order.priceList.each {
	println "���� : ${it.name} = ${it.totalPrice()}"
}


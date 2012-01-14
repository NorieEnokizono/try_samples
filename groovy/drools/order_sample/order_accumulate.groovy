@Grab("org.drools:drools-core:5.4.0.Beta1")
@Grab("org.drools:drools-compiler:5.4.0.Beta1")
@Grab("com.sun.xml.bind:jaxb-xjc:2.2.5-b09")
import org.drools.KnowledgeBaseFactory
import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType
import org.drools.io.ResourceFactory

/*
 * DRL ���Ŗ��ׂ��W�v��������T���v��
 *
 *
 */

//���[����`1
def drl1 = '''
dialect "mvel"

rule "A �J�e�S����S�̂� 5��~�ȏ�w������Ƒ�������"
    no-loop
    when
        $order : Order()
        //A �J�e�S���Œ������ׂ��W�v���� 5000 �ȏ�̃p�^�[����`
        $total : Number(doubleValue >= 5000) from accumulate(
            OrderItem(
                $price : totalPrice(), product.category == "A"
            ) from $order.getItemList(),
            sum($price)
        )
    then
        System.out.println("** �������� : A�J�e�S���w�����z=" + $total)
        $order.setSippingCost(0)
end
'''

//����
class Order {
    def sippingCost = 500
    List<OrderItem> itemList = []

    int totalPrice() {
        itemList.inject(0) {acc, val -> acc + val.totalPrice()} + sippingCost
    }
}
//��������
class OrderItem {
    Product product
    int qty

    int totalPrice() {
        qty * product.price
    }
}
//���i
class Product {
    def category
    def name
    def price = 0
}

def order = new Order()

order.itemList.add(new OrderItem(qty: 1, product: new Product(category: "A", name: "�e�X�g���i1", price: 4000)))

order.itemList.add(new OrderItem(qty: 3, product: new Product(category: "B", name: "�e�X�g���i2", price: 3000)))

order.itemList.add(new OrderItem(qty: 2, product: new Product(category: "A", name: "�e�X�g���i3", price: 1500)))


def builder = KnowledgeBuilderFactory.newKnowledgeBuilder()

builder.add(ResourceFactory.newReaderResource(new StringReader(drl1)), ResourceType.DRL)

if (builder.hasErrors()) {
    println builder.errors
    return
}

def base = KnowledgeBaseFactory.newKnowledgeBase()
base.addKnowledgePackages(builder.getKnowledgePackages())

def session = base.newStatefulKnowledgeSession()

println "���[���K�p�O�F ${order.dump()}"

session.insert(order)

session.fireAllRules()
session.dispose()

println "���[���K�p��F ${order.dump()}"


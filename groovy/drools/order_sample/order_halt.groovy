@Grab("org.drools:drools-core:5.4.0.Beta1")
@Grab("org.drools:drools-compiler:5.4.0.Beta1")
@Grab("com.sun.xml.bind:jaxb-xjc:2.2.5-b09")
import org.drools.KnowledgeBaseFactory
import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType
import org.drools.io.ResourceFactory

//���[����`
def drl1 = '''
    dialect "mvel"

    rule "��~�ȏ� 3�S�~��"
        no-loop
        salience 40
        when
            $cart : Cart($price : price, price >= 1000)
        then
            System.out.println("*** 3�S�~��")
            $cart.setPrice($price - 300)
            //���[���̓K�p���I��
            drools.halt()
    end

    rule "5�S�~�ȏ� �S�~��"
        no-loop
        salience 30
        when
            $cart : Cart($price : price, price >= 500)
        then
            System.out.println("*** �S�~��")
            $cart.setPrice($price - 100)
            //���[���̓K�p���I��
            drools.halt()
    end
'''

class Cart {
    def price = 0
}

def builder = KnowledgeBuilderFactory.newKnowledgeBuilder()

builder.add(ResourceFactory.newReaderResource(new StringReader(drl1)), ResourceType.DRL)

if (builder.hasErrors()) {
    println builder.errors
    return
}

def base = KnowledgeBaseFactory.newKnowledgeBase()
base.addKnowledgePackages(builder.getKnowledgePackages())

def session = base.newStatefulKnowledgeSession()

def cart = new Cart(price: 5000)

session.insert(cart)

session.fireAllRules()
session.dispose()

println "���z = ${cart.price}"

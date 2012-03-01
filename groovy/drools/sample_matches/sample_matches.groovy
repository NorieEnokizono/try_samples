@Grab("org.drools:drools-core:5.4.0.Beta2")
@Grab("org.drools:drools-compiler:5.4.0.Beta2")
@Grab("com.sun.xml.bind:jaxb-xjc:2.2.5-b09")
import org.drools.KnowledgeBaseFactory
import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType
import org.drools.io.ResourceFactory

def drl = '''
    import java.math.BigDecimal

    dialect "mvel"

    rule "�J�e�S�� AA ����n�܂�ꍇ�� 5�S�~����"
        no-loop
        salience 10
        when
            $item : ItemParameter(done == false, category matches "AA.*")
            //�ȉ��ł���
            //$item : ItemParameter(done == false, category.startsWith("AA"))
        then
            System.out.println("�l���� : " + $item.getName());
            $item.setPrice($item.getPrice().subtract(new BigDecimal("500")));
            $item.setDone(true);

            update($item);
    end

    rule "�ΏۊO"
        no-loop
        salience 1
        when
            $item : ItemParameter(done == false)
        then
            System.out.println("�ΏۊO : " + $item.getName)

            $item.setDone(true)
            update($item)
    end
'''

class ItemParameter {
    String category
    String name
    BigDecimal price
    boolean done = false
}

def createSession = {drlString ->
    def builder = KnowledgeBuilderFactory.newKnowledgeBuilder()

    builder.add(ResourceFactory.newReaderResource(new StringReader(drlString)), ResourceType.DRL)

    if (builder.hasErrors()) {
        println builder.errors
        throw new RuntimeException("invalid DRL")
    }

    def base = KnowledgeBaseFactory.newKnowledgeBase()
    base.addKnowledgePackages(builder.getKnowledgePackages())

    base.newStatefulKnowledgeSession()
}

def session = createSession(drl)

//�������e
def inputData = [
    new ItemParameter(category: "AABB", name: "�A�C�e��1", price: 4000),
    new ItemParameter(category: "BAAC", name: "�A�C�e��2", price: 1000),
    new ItemParameter(category: "AABB", name: "�A�C�e��3", price: 4000),
    new ItemParameter(category: "CCCE", name: "�A�C�e��4", price: 2000),
    new ItemParameter(category: "ABDA", name: "�A�C�e��5", price: 1300),
    new ItemParameter(category: "AAWQ", name: "�A�C�e��6", price: 2000),
    new ItemParameter(category: "CAAQ", name: "�A�C�e��7", price: 2000)
]

inputData.each {
    session.insert(it)
}

session.fireAllRules()
session.dispose()

println "--- ���� ---"
inputData.each {
    println "${it.category}, ${it.name}, ${it.price}, ${it.done}"
}

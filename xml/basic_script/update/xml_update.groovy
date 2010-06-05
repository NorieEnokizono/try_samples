
import groovy.xml.StreamingMarkupBuilder

def doc = new XmlSlurper().parse(new File(args[0]))

//�v�f�̒ǉ� <data id="3"><details>added</details></data>
doc.appendNode {
	data(id: "3") {
		details("added")
	}
}



def builder = new StreamingMarkupBuilder()

//�������XML�擾
def xmlString = builder.bind{
	mkp.yield doc
}

println xmlString


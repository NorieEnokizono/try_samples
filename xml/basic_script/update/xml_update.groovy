
import groovy.xml.StreamingMarkupBuilder

def doc = new XmlSlurper().parse(new File(args[0]))

//�v�f�̒ǉ� <data id="3"><details>added</details></data>
doc.appendNode {
	data(id: "3") {
		details("added")
	}
}



//�������XML���擾���ďo��
println new StreamingMarkupBuilder().bind{
	mkp.yield doc
}


import groovy.xml.StreamingMarkupBuilder

def xml = "<root><a><value>123</value></a><b><value>test</value></b></root>"

def doc = new XmlSlurper().parseText(xml)

println doc.getClass()

//c �v�f�̒ǉ�
doc.appendNode {
	c("abc")
}

//�����̒ǉ�
doc.b.@id = "no1"

//�v�f�̒u��
doc.b.value.replaceNode { n ->
	item(type: "data") {
		value("test data")
	}
}

//a �v�f�̍폜
doc.a.replaceNode {}

def builder = new StreamingMarkupBuilder()

//�������XML�擾
def xmlString = builder.bind{
	mkp.yield doc
}

println xmlString

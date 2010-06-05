
import groovy.xml.StreamingMarkupBuilder

def doc = new XmlSlurper().parse(new File(args[0]))

//�v�f�̒ǉ� <data id="3"><details>added</details></data>
doc.appendNode {
	data(id: "3") {
		details("added")
	}
}

//�����̒ǉ� <data id="2"> �� type="node" ��ǉ�
doc.data.find {it.@id == "2"}.@type = "node"
//�ȉ��ł���
doc.data[1].@type = "node"

//�ȉ��͕s�BappendNode �����v�f�� find ��z��ŃA�N�Z�X�ł��Ȃ��͗l
//doc.data.find {it.@id == "3"}.@type = "node1"
//doc.data[2].@type = "node2"


//�v�f�̍폜 <data id="1">�E�E�E</data> ���폜
doc.data.find {it.@id == "1"}.replaceNode {}


//�v�f�̕ύX
doc.data.find {it.@id == "2"}.children()[0].replaceNode {
	text("update test")
}
//�ȉ��ł���
/*
doc.data[1].details[0].replaceNode {
	text("update test")
}
*/

//�v�f�̒l��ύX
doc.data.find {it.@id == "2"}.details[1] = "after"

//�����̕ύX <data id="2" ext="none"> �� ext="updated" �ɕύX
doc.data.find {it.@id == "2"}.@ext = "updated"


//�����񉻂��ďo��
println new StreamingMarkupBuilder().bind{
	mkp.yield doc
}

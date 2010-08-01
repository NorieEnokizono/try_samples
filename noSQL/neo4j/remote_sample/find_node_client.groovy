
import org.neo4j.remote.RemoteGraphDatabase
import org.neo4j.graphdb.*

def id = (args.length > 0)? args[0].toInteger(): 1

def db = new RemoteGraphDatabase("rmi://localhost/remote-test")

def tx = db.beginTx()

//Node �� name �v���p�e�B�̒l���o��
def printName = {n -> 
	if (n.hasProperty("name")) {
		println n.id + " - " + n.getProperty("name")
	}
	else {
		println n.id + " - ** no name **"
	}
}

try {
	//id=1 �� Node �擾
	def n = db.getNodeById(id)

	//id=1 �� Node �̖��O�o��
	printName(n)

	//Node �̑SRelationship�擾
	n.relationships.each {
		//End Node �̖��O�o��
		printName(it.endNode)
	}

	println "--------------------"

	def know = DynamicRelationshipType.withName("knows")

	//2�w�ڂŒT�����~
	def s = {pos -> pos.depth() == 2} as StopEvaluator
	//name �v���p�e�B������Node�̂ݕԂ��B
	def r = {pos -> pos.currentNode().hasProperty("name")} as ReturnableEvaluator

	//Relationship=know �Ōq�����Ă��� Node �� 2�w�ڂ܂ŒT��
	//�iRelationship �̌����͂ǂ���ł��悢�j
	def t = n.traverse(Traverser.Order.BREADTH_FIRST, s, r, know, Direction.BOTH)

	//t.each ���g���ƃG���[�̔����p�x������
	//�ȉ��̃R�[�h�ł��G���[���������鎞�Ɣ������Ȃ���������
	t.getAllNodes().each {
		printName(it)
	}

	println "---------------"

} catch (e) {
//	e.printStackTrace()
} finally {
	tx.failure()
	tx.finish()
}

println("shutdown db ...")
db.shutdown()


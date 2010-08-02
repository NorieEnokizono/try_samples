
import org.neo4j.kernel.EmbeddedGraphDatabase
import org.neo4j.graphdb.*

def id = (args.length > 0)? args[0].toInteger(): 1

def db = new EmbeddedGraphDatabase("local-sample")

//Node �� name �v���p�e�B�̒l���o��
def printName = {n -> 
	if (n.hasProperty("name")) {
		println n.id + " - " + n.getProperty("name")
	}
	else {
		println n.id + " - ** no name **"
	}
}

//�w�� id �� Node �擾
def n = db.getNodeById(id)

//Node �̖��O�o��
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

t.each {
	printName(it)
}

println "---------------"

//�S Node �T��
def all = n.traverse(Traverser.Order.BREADTH_FIRST, StopEvaluator.END_OF_GRAPH, ReturnableEvaluator.ALL, know, Direction.BOTH)

all.each {
	printName(it)
}


println("shutdown db ...")
db.shutdown()


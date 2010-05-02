
import com.mongodb.*

def con = new Mongo("localhost")

println "---- db names ------"

con.databaseNames.each {
	println it
}

def db = con.getDB("local")

println "---- collection names ------"

db.collectionNames.each {
	println it
}

println "--------------"

//�R���N�V�����̎擾�i����������V�K�쐬�j
def col = db.getCollection("test")

doc = new BasicDBObject()
doc.put("name", "testdb")
doc.put("title", "�e�X�g�f�[�^")

//�h�L�������g�̓o�^
col.insert(doc)

println col.findOne()

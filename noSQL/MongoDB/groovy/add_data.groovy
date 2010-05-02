
import com.mongodb.*
import com.mongodb.util.JSONParser

def con = new Mongo("localhost")

def db = con.getDB("local")

//�R���N�V�����̎擾�i����������V�K�쐬�j
def col = db.getCollection("test-col")

(1..5).each {
	println it

	def doc = new BasicDBObject()
	doc.put("no", it)
	doc.put("title", "�e�X�g�f�[�^" + it)

	//BasicDBObjectBuilder ���g���� BasicDBObject �𐶐�
	details = BasicDBObjectBuilder.start(["value": it * it, "category": "A"]).get()
	doc.put("details", details)

	col.insert(doc)
}

//JSONParser ���g���� JSON �����񂩂� BasicDBObject �𐶐�
def json = new JSONParser("{'no': 6, 'title': 'test6'}")
col.insert(json.parseObject())

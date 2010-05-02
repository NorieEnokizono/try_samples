
import com.mongodb.*

def con = new Mongo("localhost")

def db = con.getDB("local")

def col = db.getCollection("test-col")


//�S�擾
col.find().each {
	println it
}

println "------- no=5 ---------"
//no=5 ���擾
col.find(new BasicDBObject("no", 5)).each {
	println it
}

println "------- value < 10 ---------"
//value < 10 ���擾
def query = new BasicDBObject("details.value", new BasicDBObject('$lt', 10))
col.find(query).each {
	println it
}

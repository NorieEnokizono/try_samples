//   ���L���� Caught: javax.json.JsonException: 
//   Provider org.glassfish.json.JsonProviderImpl not found �ƂȂ�
//
//@Grab('javax.json:javax.json-api:1.0')
@Grab('org.glassfish:javax.json:1.0')
import javax.json.*

def reader = Json.createReader(new StringReader('{"test": "a"}'))

println reader.readObject()

reader.close()

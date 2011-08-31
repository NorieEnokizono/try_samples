@Grapes([
	@Grab("org.jclouds:jclouds-all:1.1.1"),
	@Grab("org.jclouds:jclouds-allblobstore:1.1.1")
])
import org.jclouds.blobstore.BlobStoreContextFactory

def ctx = new BlobStoreContextFactory().createContext("transient", "identity", "credential")

def containerName = "sample1"

def store = ctx.blobStore
store.createContainerInLocation(null, containerName)

//Blob �̍쐬
def blob = store.newBlob("test")
//���e�̐ݒ�
blob.setPayload("abc")

//Blob �� put
store.putBlob(containerName, blob)

//Blob �� get
def b = store.getBlob(containerName, "test")

println "${b} - ${b.payload} - ${b.payload.rawContent}"

print "payload = "
b.payload.writeTo(System.out)

ctx.close()

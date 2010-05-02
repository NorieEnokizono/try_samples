
import javax.xml.bind.JAXBContext
import javax.xml.bind.annotation.*

//groovy �̃N���X���ז�����̂� @XmlAccessorType(XmlAccessType.NONE) ���g��
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Customer {
	@XmlElement
	String id

	@XmlElement
	String name
}

def ctx = JAXBContext.newInstance(Customer.class)

ctx.createMarshaller().marshal(new Customer(id: "id:1", name: "test"), System.out)


import javax.xml.bind.annotation.*;

public class Mail {
	//XML �����Ƃ��ďo��
	@XmlAttribute
	public String type;
	//XML �v�f�ւ̒l�Ƃ��ďo��
	@XmlValue
	public String mail;

	public Mail() {
	}
	public Mail(String type, String mail) {
		this.type = type;
		this.mail = mail;
	}
}
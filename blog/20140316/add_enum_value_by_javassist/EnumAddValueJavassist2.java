
import javassist.*;

public class EnumAddValueJavassist2 {
	public static void main(String... args) throws Exception {
		ClassPool pool = ClassPool.getDefault();

		CtClass cc = pool.get("EType");

		// Second �t�B�[���h�̒ǉ�
		CtField second = CtField.make("public static final EType Second = new EType(\"Second\", 1);", cc);
		cc.addField(second);

		// �ÓI�������q�istatic �C�j�V�����C�U�j�֏�����ǉ����� $VALUES ����������
		cc.getClassInitializer().insertAfter("$VALUES = new EType[] { First, Second };");

		cc.toClass();

		System.out.println(EType.valueOf("Second"));

		System.out.println("-----");

		for (EType type : EType.values()) {
			System.out.println(type);
		}
	}
}

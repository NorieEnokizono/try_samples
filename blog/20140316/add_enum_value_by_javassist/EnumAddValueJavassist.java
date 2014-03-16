
import javassist.*;

public class EnumAddValueJavassist {
	public static void main(String... args) throws Exception {
		ClassPool pool = ClassPool.getDefault();

		CtClass cc = pool.get("EType");

		// �ÓI�������q�istatic �C�j�V�����C�U�j�� $VALUES �����������鏈����ǉ�
		cc.getClassInitializer().insertAfter("$VALUES = new EType[] { First, new EType(\"Second\", 1) };");

		cc.toClass();

		System.out.println(EType.valueOf("Second"));

		System.out.println("-----");

		for (EType type : EType.values()) {
			System.out.println(type);
		}
	}
}


import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.tools.Compiler;

class GroovySimpleCompiler {
	public static void main(String[] args) {

		CompilerConfiguration conf = new CompilerConfiguration();
		//�o�͐�f�B���N�g����ݒ�
		conf.setTargetDirectory("dest");

		Compiler compiler = new Compiler(conf);

		//���s�������Ŏw�肵�� Groovy �X�N���v�g�t�@�C�����R���p�C��
		compiler.compile(args);
	}
}

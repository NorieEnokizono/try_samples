import scala.Iterator;
import scala.io.Source$;

public class ReadFileLine {
	public static void main(String[] args) {

		//Singleton �I�u�W�F�N�g��  object��$.MODULE$ �ŃA�N�Z�X��
		Iterator<String> it = Source$.MODULE$.fromFile(args[0]).getLines();

		while(it.hasNext()) {
			System.out.print(it.next());
		}
	}
}

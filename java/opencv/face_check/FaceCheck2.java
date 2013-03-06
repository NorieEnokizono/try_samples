import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class FaceCheck2 {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("java FaceCheck <image file> [<classifier file>]");
			return;
		}

		String classifierFile = (args.length == 1)? "haarcascade_frontalface_default.xml": args[1];

		CascadeClassifier detector = new CascadeClassifier(classifierFile);

		// �摜�t�@�C���̓ǂݍ���
		Mat img = Highgui.imread(args[0]);

		MatOfRect result = new MatOfRect();

		// ���o����
		detector.detectMultiScale(img, result);

		for (Rect rect : result.toArray()) {
			// ���o�ӏ���Ԑ��̉~�ň͂ޏ���
			Core.circle(img, new Point(rect.x + rect.width / 2, rect.y + rect.height / 2), rect.width / 2, new Scalar(0, 0, 255), 2);
		}

		Highgui.imwrite("dest.png", img);
	}
}
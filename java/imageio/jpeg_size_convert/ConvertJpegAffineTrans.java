
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;

//JPEG�̃T�C�Y�ϊ��iAffineTransform�Łj
// drawImage �������������������Ȗ͗l
public class ConvertJpegAffineTrans {

	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("java ConvertJpegDrawImage [�ϊ���̉���] [����JPEG�t�@�C��] [�o��JPEG�t�@�C��]");
			return;
		}

		//����JPEG�ǂݍ���
		BufferedImage input = ImageIO.read(new File(args[1]));

		//�ϊ���̉���
		int toWidth = Integer.parseInt(args[0]);

		double rate = (double)toWidth / input.getWidth();

		int width2 = (int)(input.getWidth() * rate);
		int height2 = (int)(input.getHeight() * rate);

		BufferedImage output = new BufferedImage(width2, height2, input.getType());
		//�T�C�Y�ϊ��̒�`
		AffineTransformOp at = new AffineTransformOp(AffineTransform.getScaleInstance(rate, rate), null);
	//	AffineTransformOp at = new AffineTransformOp(AffineTransform.getScaleInstance(rate, rate), AffineTransformOp.TYPE_BICUBIC);

		//�ϊ�
		at.filter(input, output);

		//JPEG�o��
		ImageIO.write(output, "jpg", new File(args[2]));
	}
}


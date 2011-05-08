
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * �T���l�C���p�摜�쐬
 * java Thumbnail [�ϊ���̉���] [����JPEG�t�@�C��] [�o��JPEG�t�@�C��]
 */
public class Thumbnail {
    public static void main(String[] args) throws Exception {
        //����JPEG�ǂݍ���
        BufferedImage input = ImageIO.read(new File(args[1]));

        //�ϊ���̉���
        int toWidth = Integer.parseInt(args[0]);

        double rate = (double)toWidth / input.getWidth();

        int toHeight = (int)(input.getHeight() * rate);

        //�o�͗p�C���[�W
        BufferedImage output = new BufferedImage(toWidth, toHeight, input.getType());
        //�T�C�Y�ϊ���`
        AffineTransformOp at = new AffineTransformOp(AffineTransform.getScaleInstance(rate, rate), null);

        //�T���l�C���摜�쐬�i�T�C�Y�ϊ��j
        at.filter(input, output);

        //�T���l�C���摜�o��
        ImageIO.write(output, "jpg", new File(args[2]));
    }
}


import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.util.concurrent.Future;

public class AsyncFileReadSample2 {
	public static void main(String[] args) throws Exception {

		AsynchronousFileChannel fc = 
				AsynchronousFileChannel.open(Paths.get(args[0]));

		ByteBuffer buf = ByteBuffer.allocate((int)fc.size());

		Future<Integer> future = fc.read(buf, 0);

		//�t�@�C���̓ǂݍ��݂���������܂� wait
		int res = future.get();

		System.out.println("read size = " + res);

		System.out.println(buf.toString());
		buf.rewind();
		System.out.println(buf.toString());

		CharBuffer chb = Charset.defaultCharset().decode(buf);

		//�t�@�C���̓��e���o��
		System.out.println(chb.toString());
	}
}
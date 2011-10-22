
import java.io.*;
import java.util.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import static java.nio.file.StandardCopyOption.*;

public class AsyncDownloadWeb2 {
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("java AsyncDownloadWeb2 <output dir>");
			return;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		final ExecutorService exec = Executors.newCachedThreadPool();

		final String dir = args[0];
		String urlString = null;

		while ((urlString = reader.readLine()) != null) {
			final URI uri = URI.create(urlString);

			//URL�ڑ������̔񓯊����s
			final Future<InputStream> stream = exec.submit(new Callable<InputStream>() {
				public InputStream call() throws Exception {
					return uri.toURL().openStream();
				}
			});

			//�_�E�����[�h�����̔񓯊����s
			final Future<Path> file = exec.submit(new Callable<Path>() {
				public Path call() throws Exception {
					String fileName = new File(uri.getPath()).getName();
					Path filePath = Paths.get(dir, fileName);

					Files.copy(stream.get(), filePath, REPLACE_EXISTING);
					return filePath;
				}
			});

			//���ʏo��
			exec.submit(new Runnable() {
				public void run() {
					try {
						System.out.printf("downloaded: %s => %s\n", uri, file.get());
					} catch (Exception ex) {
						System.out.printf("failed: %s, %s\n", uri, ex);
					}
				}
			});
		}

		//�I��
		exec.shutdown();
	}
}
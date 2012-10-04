
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(value = "/async", asyncSupported = true)
public class AsyncSampleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		final AsyncContext asyncCtx = req.startAsync();

		// �^�C���A�E�g�����ɐݒ�
		asyncCtx.setTimeout(0);

		asyncCtx.addListener(new AsyncListener() {
			// ���̃��\�b�h�͎��s����Ȃ��͗l
			public void onStartAsync(AsyncEvent ev) {
				System.out.println("Start : " + ev);
			}
			public void onComplete(AsyncEvent ev) {
				System.out.println("Complete : " + ev);
			}
			public void onError(AsyncEvent ev) {
				System.out.println("Error : " + ev + ", " + ev.getThrowable());
			}
			public void onTimeout(AsyncEvent ev) {
				System.out.println("Timeout : " + ev);
			}
		});

		asyncCtx.start(new Runnable() {
			public void run() {
				System.out.println("sleep start - " + this);

				try {
					Thread.currentThread().sleep(5000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				System.out.println("sleep stop - " + this);

				// complete ���Ăяo���ƃ��X�|���X����
				asyncCtx.complete();
			}
		});

		PrintWriter pw = res.getWriter();
		pw.println("job added");
		pw.close();
	}
}

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	private String contentType = "application/x-msdownload";
	private String enc = "utf-8";
	private String fileRoot = "";

	/**
	 * 初始化contentType，enc，fileRoot
	 */
	public void init(ServletConfig config) throws ServletException {
		String tempStr = config.getInitParameter("contentType");
		if (tempStr != null && !tempStr.equals("")) {
			contentType = tempStr;
		}
		tempStr = config.getInitParameter("enc");
		if (tempStr != null && !tempStr.equals("")) {
			enc = tempStr;
		}
		tempStr = config.getInitParameter("fileRoot");
		if (tempStr != null && !tempStr.equals("")) {
			fileRoot = tempStr;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filepath = request.getParameter("filepath");
		String fullFilePath = fileRoot + filepath;
		System.out.println(fullFilePath);
		/* 读取文件 */
		File file = new File(fullFilePath);
		/* 如果文件存在 */
		if (file.exists()) {
			String filename = URLEncoder.encode(file.getName(), enc);
			response.reset();
			response.setContentType(contentType);
			
			response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			int fileLength = (int) file.length();
			response.setContentLength(fileLength);
			/* 如果文件长度大于0 */
			if (fileLength != 0) {
				/* 创建输入流 */
				InputStream inStream = new FileInputStream(file);
				byte[] buf = new byte[4096];
				/* 创建输出流 */
				ServletOutputStream servletOS = response.getOutputStream();
				int readLength;
				while (((readLength = inStream.read(buf)) != -1)) {
					servletOS.write(buf, 0, readLength);
				}
				inStream.close();
				servletOS.flush();
				servletOS.close();
			}
		}
	}
}
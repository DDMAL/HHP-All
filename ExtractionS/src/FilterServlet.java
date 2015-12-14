import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.*;
import java.net.URLEncoder;
import java.util.*;  
import org.apache.commons.fileupload.*;  
import org.apache.commons.fileupload.servlet.*;  
import org.apache.commons.fileupload.disk.*;  
 
// Servlet 文件上传  
public class FilterServlet extends HttpServlet  
{  
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
	        Filter filt=new Filter();
	        filt.filterdata(fullFilePath);

		}
		System.out.println("load success");
		response.sendRedirect("Index.jsp");
	}
} 
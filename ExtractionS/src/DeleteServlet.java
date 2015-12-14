import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.*;
import java.net.URLEncoder;
import java.util.*;  
// Servlet 文件上传  
public class DeleteServlet extends HttpServlet  
{  
	private String contentType = "application/x-msdownload";
	private String enc = "utf-8";
	private String fileRoot = "";

	/**
	 * 初始化contentType，enc，fileRoot
	 */
	public void init(ServletConfig config) throws ServletException {
		fileRoot = config.getInitParameter("fileRoot");
		
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
			file.delete();
		}
		System.out.println("load success");
		response.sendRedirect("Index.jsp");
	}
} 
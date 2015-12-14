import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.*;
import java.net.URLEncoder;
import java.util.*;  
import org.apache.commons.fileupload.*;  
import org.apache.commons.fileupload.servlet.*;  
import org.apache.commons.fileupload.disk.*;  
 
// Servlet �ļ��ϴ�  
public class LoadServlet extends HttpServlet  
{  
	private String contentType = "application/x-msdownload";
	private String enc = "utf-8";
	private String fileRoot = "";

	/**
	 * ��ʼ��contentType��enc��fileRoot
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
		/* ��ȡ�ļ� */
		File file = new File(fullFilePath);
		/* ����ļ����� */
		if (file.exists()) {
			
			System.out.println(fullFilePath);
			System.out.println(filepath);
			Load load=new Load();
			//load.Loadfilter(fullFilePath,filepath);
		}
		System.out.println("load success");
		response.sendRedirect("Index.jsp");
	}
} 
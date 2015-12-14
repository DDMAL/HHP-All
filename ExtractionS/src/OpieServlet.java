import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.*;
import java.net.URLEncoder;
import java.util.*;  
import org.apache.commons.fileupload.*;  
import org.apache.commons.fileupload.servlet.*;  
import org.apache.commons.fileupload.disk.*;  

public class OpieServlet extends HttpServlet  
{  
	private String contentType = "application/x-msdownload";
	private String enc = "utf-8";
	private String fileRoot = "";
	private String jaddress = "";
	private String systemenv="";
	private String linuxparam="";

	/**
	 * 初始化contentType，enc，fileRoot
	 */
	public void init(ServletConfig config) throws ServletException {
		 fileRoot = config.getInitParameter("fileRoot");
		 jaddress = config.getInitParameter("jaddress");
		 systemenv = config.getInitParameter("systemenv");
		 linuxparam = config.getInitParameter("linuxparam");

		
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
			OpenieFile of=new OpenieFile();
			String bataddress=of.UseOpenie(fullFilePath, jaddress,systemenv);
			try {
				of.excjar(bataddress,systemenv,linuxparam);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					}
		System.out.println("extraction filted success");
		response.sendRedirect("Index.jsp");
	}
} 
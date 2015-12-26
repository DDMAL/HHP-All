

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jblas.util.Random;

import com.google.gson.Gson;

/**
 * Servlet implementation class SesameWriteServlet
 */
@WebServlet("/SesameWriteServlet")
public class SesameWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SesameWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the URL parameter
		String data = request.getParameter("data");
		System.out.println(data);
		
		//Now insert the data into Sesame
		
		//TODO insert data
		
		//Create the response that the servlet returns - this should be a valid JSON object
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JsonStatus jss = new JsonStatus("Response from Servlet (random number) click for another number: " + Random.nextDouble());
		
		// This is a convenience thing - Gson converts  objects into valid json
		Gson gson = new Gson();
		out.println(gson.toJson(jss));
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

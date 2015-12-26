

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
 * Servlet implementation class SesameReadServlet
 */
@WebServlet("/SesameReadServlet")
public class SesameReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SesameReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * This Servlet works with the following URL parameters:
		 * lookup: the query to perform against Sesame
		 * 
		 * For example: http://localhost:8080/ExtractionS/SesameReadServlet?lookup=test
		 */
		
		String lookup = request.getParameter("lookup");
		// use the lookup to formulate your query
		System.out.println(lookup);
		
		//Perform the Sesame Lookup - let's assume this returns RDF triples in a list
		//like the following
		
		List<RDFTriple> list = new ArrayList<RDFTriple>();
		for (Integer i = 1; i <= 20; i++)
		{
			// generating some fake data
			RDFTriple a = new RDFTriple("Fred" + Random.nextInt(50), "ate", "Lunch" + Random.nextInt(50), "Graph1", i.toString());
			list.add(a);
		}
		
		//Create the response that the servlet returns - this should be a valid JSON object
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		
		// This is a convenience thing - Gson converts  objects into valid json
		Gson gson = new Gson();
		out.println(gson.toJson(list));
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}



import java.io.File;
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
import org.openrdf.OpenRDFException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.nativerdf.NativeStore;

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
		System.out.println("Hello");
		/*
		 * This Servlet works with the following URL parameters:
		 * lookup: the query to perform against Sesame
		 * 
		 * For example: http://localhost:8080/ExtractionS/SesameReadServlet?lookup=test
		 */
		
		File dataDir = new File("./");
		Repository rep = new SailRepository(new NativeStore(dataDir));
		rep.initialize();
		
		List<String> list = new ArrayList<String>();
		
		try {
			   RepositoryConnection con = rep.getConnection();
			   try {
			      String queryString = "SELECT x, y FROM {x} p {y}";
			      TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SERQL, queryString);
			      TupleQueryResult result = tupleQuery.evaluate();
			      try {
			         list.add(result.toString());
			      }
			      finally {
			         result.close();
			      }
			   }
			   finally {
			      con.close();
			   }
			}
			catch (OpenRDFException e) {
			   // handle exception
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

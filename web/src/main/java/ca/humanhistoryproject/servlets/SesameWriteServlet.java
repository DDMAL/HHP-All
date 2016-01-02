package ca.humanhistoryproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.model.IRI;
import org.openrdf.model.Statement;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.http.HTTPRepository;

import ca.humanhistoryproject.GlobalVars;
import ca.humanhistoryproject.utils.JsonStatus;
import ca.humanhistoryproject.utils.RDFTriple;

import com.google.gson.Gson;

/**
 * Servlet implementation class SesameWriteServlet
 */
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

		Gson gson = new Gson();
		RDFTriple[] array = gson.fromJson(data, RDFTriple[].class);

		for (RDFTriple r : array)
		{	
			HTTPRepository rep = new HTTPRepository(GlobalVars.RepositoryURL);		
			RepositoryConnection conn = rep.getConnection();
			String namespace = "http://humanhistoryproject.ca/";
			
			ValueFactory factory = rep.getValueFactory();
			
			IRI s = factory.createIRI(namespace + r.Subject);
			IRI p = factory.createIRI(namespace + r.Predicate);
			IRI o = factory.createIRI(namespace + r.Object);
			
			Statement statement = factory.createStatement(s, p, o);
			conn.add(statement);
			conn.close();	
		}
		
		//Create the response that the servlet returns
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JsonStatus jss = new JsonStatus("Added to Sesame");		
		out.println(gson.toJson(jss));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
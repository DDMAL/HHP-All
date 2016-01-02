package ca.humanhistoryproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openrdf.model.Statement;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.http.HTTPRepository;

import ca.humanhistoryproject.GlobalVars;
import ca.humanhistoryproject.utils.RDFTriple;

import com.google.gson.Gson;

/**
 * Servlet implementation class SesameReadServlet
 */
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<RDFTriple> finalList = new ArrayList<RDFTriple>();

		HTTPRepository rep = new HTTPRepository(GlobalVars.RepositoryURL);
		RepositoryConnection conn = rep.getConnection();
		String namespace = "http://humanhistoryproject.ca/";

		RepositoryResult<Statement> statements = conn.getStatements(null, null,
				null, true);
		int index = 0;
		while (statements.hasNext()) {
			Statement s = statements.next();
			finalList.add(new RDFTriple(s.getSubject().stringValue()
					.replaceAll(namespace, ""), s.getPredicate().stringValue()
					.replaceAll(namespace, ""), s.getObject().stringValue()
					.replaceAll(namespace, ""), s.getContext() + "", ""
					+ (index++)));

		}
		conn.close();

		// Create the response that the servlet returns
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.println(gson.toJson(finalList));
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}

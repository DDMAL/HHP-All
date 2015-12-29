

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

import com.google.gson.Gson;
import info.aduna.iteration.Iterations;

import org.openrdf.model.IRI;
import org.openrdf.model.Literal;
import org.openrdf.model.Model;
import org.openrdf.model.Namespace;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.Rio;
import org.openrdf.sail.memory.MemoryStore;
import org.openrdf.sail.nativerdf.NativeStore;

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


		// sesame start up stuff
		File dataDir = new File("~/Projects/HHP/HHP/ExtractionS/");
		Repository rep = new SailRepository(new NativeStore(dataDir));
		rep.initialize();
		String namespace = "http://example.org/";
		
		try {
			rep.initialize();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RepositoryConnection conn = null;
		try {
			conn = rep.getConnection();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//
				
		String[] splitString = data.split("\n");
		ValueFactory factory = ValueFactoryImpl.getInstance();
		
		for(int i = 0; i < splitString.length; i+=3) {
			//IRI john = factory.createIRI(namespace, "john");
			//conn.add(john, RDF.TYPE, RDF.SUBJECT);

			IRI a = factory.createIRI(namespace + splitString[i+0]);
			IRI b = factory.createIRI(namespace + splitString[i+1]);
			Statement statement = factory.createStatement(a, b, factory.createLiteral(splitString[i+2]));
			conn.add(statement);
			System.out.println(statement);
			//System.out.println(statement.toString());
			try {
				//conn.add(statement);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		conn.close();
		rep.shutDown();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scala.collection.Seq;
import edu.knowitall.openie.Instance;
import edu.knowitall.openie.OpenIE;
import edu.knowitall.openie.Part;
import edu.knowitall.tool.parse.ClearParser;
import edu.knowitall.tool.postag.ClearPostagger;
import edu.knowitall.tool.srl.ClearSrl;
import edu.knowitall.tool.tokenize.ClearTokenizer;

/**
 * Servlet implementation class DoitAllServlet
 */
@WebServlet("/DoitAllServlet")
public class DoitAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoitAllServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String text = request.getParameter("data");

		System.out.println("Processing Step 1. - CoreNLP");
		Filter filt = new Filter();

		String processed_text = filt.filterdata(text);
		System.out.println("Processing Step 1. - CoreNLP Done.");
		
		
		OpenIE openIE = new OpenIE(new ClearParser(new ClearPostagger(
				new ClearTokenizer(ClearTokenizer.defaultModelUrl()))),
				new ClearSrl(), false);

		StringBuilder openIEOutput = new StringBuilder();
		System.out.println("Processing Step 2. - Openie.");
		for (String sentence : processed_text.split("\\. ")) {
			System.out.println(sentence);
			Seq<Instance> extractions = openIE.extract(sentence);

			Instance[] arr = new Instance[extractions.length()];
			extractions.copyToArray(arr);

			for (Instance inst : arr) {
				StringBuilder sb = new StringBuilder();
				sb.append(inst.sentence() + "\n");
				Double conf = inst.confidence();
				String stringConf = conf.toString().substring(0, 4);
				sb.append(stringConf).append(" (")
						.append(inst.extr().arg1().text()).append("; ")
						.append(inst.extr().rel().text()).append("; ");

				Part[] arr2 = new Part[inst.extr().arg2s().length()];
				inst.extr().arg2s().copyToArray(arr2);
				for (Part arg : arr2) {
					sb.append(arg.text()).append("");
				}
				sb.append(")\n\n");
				openIEOutput.append(sb.toString());
			}
		}
		System.out.println("Processing Step 2. - Openie Done.");

		System.out.println("Processing Step 3. - Rewrite");
		Load load = new Load();
		System.out.println(openIEOutput.toString());
		String result = load.Loadfilter(openIEOutput.toString());
		System.out.println("Processing Step 3. - Rewrite Done");

		System.out.println(result);

		response.sendRedirect("Index.jsp");

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

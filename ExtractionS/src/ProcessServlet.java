

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import scala.collection.Seq;
import edu.knowitall.openie.Instance;
import edu.knowitall.openie.OpenIE;
import edu.knowitall.openie.Part;
import edu.knowitall.tool.parse.ClearParser;
import edu.knowitall.tool.postag.ClearPostagger;
import edu.knowitall.tool.srl.ClearSrl;
import edu.knowitall.tool.tokenize.ClearTokenizer;



/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ProcessServlet")
public class ProcessServlet extends LongRunningProcessServlet {

	private static final long serialVersionUID = 1L;



	@Override
	protected ThreadProcessor initThreadProcessor(HttpServletRequest request,
			String uuid) {

		//System.out.println(request.getQueryString());
		//System.out.println(request.getParameter("op"));
		return new AnalyzerThreadProcessor(request, uuid);
	}

	public class AnalyzerThreadProcessor extends ThreadProcessor {
		private String text = "";
		private String result;


		public AnalyzerThreadProcessor(HttpServletRequest request, String uuid) {
			super(request, uuid);

			try {
				this.text = java.net.URLDecoder.decode(request.getQueryString().substring(3), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(text);

		}


		@Override
		protected void process() throws Exception {
			this.status = "{\"status\":\"Now processing CoreNLP.\",\"starttime\":" + start_time
					+ ",\"uuid\":\"" + this.uuid + "\"}";

			System.out.println("Processing Step 1. - CoreNLP");
			System.out.println(text);
			String processed_text = Filter.filterdata(text);
			System.out.println("Processing Step 1. - CoreNLP Done.");

			/*
			OpenIE openIE = new OpenIE(new ClearParser(new ClearPostagger(
					new ClearTokenizer(ClearTokenizer.defaultModelUrl()))),
					new ClearSrl(), false);
			 */
			StringBuilder openIEOutput = new StringBuilder();
			System.out.println("Processing Step 2. - Openie.");
			this.status = "{\"status\":\"Now processing Openie.\",\"starttime\":" + start_time
					+ ",\"uuid\":\"" + this.uuid + "\"}";
			String temp = "";
			for (String sentence : processed_text.split("\\. ")) {

				Seq<Instance> extractions = GlobalVars.openIE.extract(sentence);

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

					temp += inst.extr().arg1().text() + "\n" +  inst.extr().rel().text() + "\n";

					Part[] arr2 = new Part[inst.extr().arg2s().length()];
					inst.extr().arg2s().copyToArray(arr2);
					/*
					for (Part arg : arr2) {
						sb.append(arg.text()).append("");
						System.out.println("%" + arg.text() + "%");
					}*/
					if(arr2.length != 0) {
						System.out.println("Hats: " + arr2[0]);
						temp += arr2[0] + "\n";
						sb.append(arr2[0]);
						sb.append(")\n\n");
						openIEOutput.append(sb.toString());
					}
				}
			}
			System.out.println("Processing Step 2. - Openie Done.");
			this.status = "{\"status\":\"Now processing Rewrite.\",\"starttime\":" + start_time
					+ ",\"uuid\":\"" + this.uuid + "\"}";
			System.out.println("Processing Step 3. - Rewrite");
			Load load = new Load();
			System.out.println("This is the output: " + openIEOutput.toString());
			//result = load.Loadfilter(openIEOutput.toString());
			System.out.println("Processing Step 3. - Rewrite Done");

			System.out.println(result);
			result = temp;
			//result = "hello you";

			//response.sendRedirect("Index.jsp");
		}

		protected String setEndProcessingStatus() {
			Gson g = new Gson();

			return  "{\"datatable\":" + g.toJson(result)
			+ ",\"status\":\"OK\",\"starttime\":" + start_time
			+ ",\"uuid\":\"" + this.uuid + "\"}";
		}



		@Override
		protected void prepareResponse(HttpServletResponse response)
				throws IOException {
			// TODO Auto-generated method stub

		}
	}
}

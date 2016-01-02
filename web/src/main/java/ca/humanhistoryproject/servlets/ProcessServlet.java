package ca.humanhistoryproject.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scala.collection.Seq;
import ca.humanhistoryproject.Filter;
import ca.humanhistoryproject.GlobalVars;
import ca.humanhistoryproject.utils.LongRunningProcessServlet;
import ca.humanhistoryproject.utils.ThreadProcessor;

import com.google.gson.Gson;

import edu.knowitall.openie.Instance;
import edu.knowitall.openie.Part;

/**
 * Servlet implementation class TestServlet
 */

public class ProcessServlet extends LongRunningProcessServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected ThreadProcessor initThreadProcessor(HttpServletRequest request,
			String uuid) {

		// System.out.println(request.getParameter("op"));
		return new AnalyzerThreadProcessor(request, uuid);
	}

	public class AnalyzerThreadProcessor extends ThreadProcessor {
		private String text = "";
		private String result;

		public AnalyzerThreadProcessor(HttpServletRequest request, String uuid) {
			super(request, uuid);

			try {
				this.text = java.net.URLDecoder.decode(request.getQueryString()
						.substring(3), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(text);

		}

		@Override
		protected void process() throws Exception {

			// initialize models - if they were not initialized on server
			// startup
			this.status = "{\"status\":\"Now processing - Initializing Models...\",\"starttime\":"
					+ start_time + ",\"uuid\":\"" + this.uuid + "\"}";
			GlobalVars.initialize();

			// Processing Step 1. - CoreNLP
			this.status = "{\"status\":\"Now processing CoreNLP.\",\"starttime\":"
					+ start_time + ",\"uuid\":\"" + this.uuid + "\"}";
			String processed_text = Filter
					.filterdata(GlobalVars.pipeline, text);

			// Processing Step 2. - Openie"
			this.status = "{\"status\":\"Now processing Openie.\",\"starttime\":"
					+ start_time + ",\"uuid\":\"" + this.uuid + "\"}";
			StringBuilder openIEOutput = new StringBuilder();
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

					temp += inst.extr().arg1().text() + "\n"
							+ inst.extr().rel().text() + "\n";

					Part[] arr2 = new Part[inst.extr().arg2s().length()];
					inst.extr().arg2s().copyToArray(arr2);
					/*
					 * for (Part arg : arr2) { sb.append(arg.text()).append("");
					 * System.out.println("%" + arg.text() + "%"); }
					 */
					if (arr2.length != 0) {
						System.out.println("Hats: " + arr2[0]);
						temp += arr2[0] + "\n";
						sb.append(arr2[0]);
						sb.append(")\n\n");
						openIEOutput.append(sb.toString());
					}
				}
			}

			// Processing Step 3. - Rewrite
			this.status = "{\"status\":\"Now processing Rewrite.\",\"starttime\":"
					+ start_time + ",\"uuid\":\"" + this.uuid + "\"}";
			// Load load = new Load();
			// result = load.Loadfilter(openIEOutput.toString());
			result = temp;
		}

		protected String setEndProcessingStatus() {
			Gson g = new Gson();

			return "{\"datatable\":" + g.toJson(result)
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

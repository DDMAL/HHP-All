

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



/**
 * Servlet implementation class LongRunningProcessServlet
 */

public abstract class LongRunningProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected static final Logger LOG = LogManager
			.getLogger(LongRunningProcessServlet.class);

	protected Map<String, ThreadProcessor> tempProcessQueue;

	/**
	 * Default constructor.
	 */
	public LongRunningProcessServlet() {
		// TODO Auto-generated constructor stub
		tempProcessQueue = new HashMap<String, ThreadProcessor>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Log the entry to this servlet
		System.out.println("Entering " + this.getClass().getName() + " with request: "
				+ request.getQueryString());

		String taskid = request.getParameter("taskid");
		String taskid_for_file = request
				.getParameter("request_file_for_taskid");

		if (taskid != null) {
			ThreadProcessor r = tempProcessQueue.get(taskid);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.println(r.getStatus());

			if (r.getStatus().contains("\"status\":\"OK\""))
				tempProcessQueue.remove(taskid);

			out.close();

		} else if (taskid_for_file != null) {

			ThreadProcessor r = tempProcessQueue.get(taskid_for_file);
			tempProcessQueue.remove(taskid_for_file);
		
			r.prepareResponse(response);
			
			response.getOutputStream().flush();

		} else {
			String uuid = UUID.randomUUID().toString();

			// Log the entry to this servlet
			LOG.info("Starting thread in " + this.getClass().getName());

			ThreadProcessor r = initThreadProcessor(request, uuid);
			tempProcessQueue.put(uuid, r);
			new Thread(r).start();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(r.getStatus());
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected abstract ThreadProcessor initThreadProcessor(
			HttpServletRequest request, String uuid);

}

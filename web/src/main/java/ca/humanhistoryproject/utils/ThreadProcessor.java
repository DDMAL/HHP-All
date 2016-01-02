package ca.humanhistoryproject.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;


public abstract class ThreadProcessor implements Runnable {

	protected static final Logger LOG = LogManager
			.getLogger(ThreadProcessor.class);
	
	protected String uuid;
	protected long start_time;
	protected String status;

	public ThreadProcessor(HttpServletRequest request, String uuid) {

		this.uuid = uuid;
		start_time = System.currentTimeMillis();
		status = setBeginProcessingStatus();
	}
	
	/**
	 * Set the json status returned by the servlet when the long running workload starts
	 * @return
	 */
	protected String setBeginProcessingStatus()
	{
		return "{\"status\":\"processing\",\"starttime\":" + start_time
				+ ",\"uuid\":\"" + this.uuid + "\"}";
	}
	
	/**
	 * Set the json status returned by the servlet when the long running workload ended
	 * @return
	 */
	protected String setEndProcessingStatus()
	{
		return "{\"status\":\"OK\",\"starttime\":" + start_time
				+ ",\"uuid\":\"" + this.uuid + "\"}";
	}

	public String getStatus() {
		return status;
	}
	
	protected abstract void prepareResponse(HttpServletResponse response) throws IOException;

	public void run() {

		try {
			
			process();
				
			status = setEndProcessingStatus();

		} catch (Exception e) {
			e.printStackTrace();
			
			LOG.error(e);
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			printWriter.flush();
			Gson gson = new Gson();
			String error_json = gson.toJson(stringWriter.toString());
			printWriter.close();

			DataTable dt = new DataTable();

			status = "{\"datatable\":" + dt.toJson() + ",\"status\":"
					+ error_json + ",\"starttime\":" + start_time
					+ ",\"uuid\":\"" + this.uuid + "\"}";

		}
	}
	
	protected abstract void process() throws Exception;
}
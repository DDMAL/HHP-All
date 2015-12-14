

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * A class representing a table
 * @author nkasch
 */
public class DataTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5766869328021369713L;
	private List<String> headers;
	private Map<String, String> values;
	private int max_rows;
	private int curser;

	/**
	 * Empty datatable
	 */
	public DataTable() {
		headers = new ArrayList<String>();
		values = new HashMap<String, String>();
		max_rows = -1;
		curser = -1;
	}

	public DataTable(String[] headers) {
		this.headers = Arrays.asList(headers);
		values = new HashMap<String, String>();
		max_rows = -1;
		curser = -1;
	}

	private String makeKey(int row, int col) {
		return row + "|" + col;
	}
	
	public void resetCursor()
	{
		curser = -1;
	}

	/**
	 * Get the int in the col at the current row
	 * 
	 * @param col
	 * @return
	 */
	public Integer getInt(int col) {
		return Integer.parseInt(values.get(makeKey(curser, col)));
	}

	/**
	 * Get the string in the col at the current row
	 * 
	 * @param col
	 * @return
	 */
	public String getString(int col) {
		return values.get(makeKey(curser, col));
	}

	/**
	 * Move to the next row
	 */
	public boolean next() {
		curser++;
		if (curser <= max_rows)
			return true;
		return false;
	}

	/**
	 * Add a new column to the table
	 * 
	 * @param new_column
	 * @return
	 */
	public DataTable addColumn(String new_column) {
		headers.add(new_column);
		return this;
	}

	/**
	 * Get the column name at the specified index
	 * 
	 * @param index
	 * @return
	 */
	public String getColumnName(int index) {
		return headers.get(index);
	}

	/**
	 * Get the number of columns in the table
	 * 
	 * @return
	 */
	public int getColumnCount() {
		return headers.size();
	}

	/**
	 * Get the number of rows in the table
	 * 
	 * @return
	 */
	public int getRowCount() {
		return max_rows;
	}

	public String toJson() {
		Gson gson = new Gson();
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"headers\":[");
		for (int i = 0; i < headers.size(); i++) {
			if (i > 0)
				sb.append(",");
			sb.append(gson.toJson(headers.get(i)));
		}

		sb.append("]");
		sb.append(",\"rows\":[");

		for (int row = 0; row <= max_rows; row++) {
			if (row > 0)
				sb.append(",");
			sb.append("[");
			for (int col = 0; col < headers.size(); col++) {
				if (col > 0)
					sb.append(",");
				sb.append(gson.toJson(values.get(makeKey(row, col))));
			}
			sb.append("]");
		}
		sb.append("]}");

		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < headers.size(); i++) {
			if (i > 0)
				sb.append("\t");
			sb.append(headers.get(i));
		}
		sb.append("\n");
		for (int row = 0; row <= max_rows; row++) {
			for (int col = 0; col < headers.size(); col++) {
				if (col > 0)
					sb.append("\t");
				sb.append(values.get(makeKey(row, col)));
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public DataTable addRowValue(int row, int col, String value) {

		if (max_rows < row)
			max_rows = row;
		values.put(makeKey(row, col), value);
		return this;

	}

	public String getValue(int row, int col) {
		return values.get(makeKey(row, col));
	}

}

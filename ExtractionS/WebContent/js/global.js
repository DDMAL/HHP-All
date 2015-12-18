/**
 * Toggle the progress bar
 * 
 * @param enable
 */
function toggle_progress(name, enable) {
	if (enable)
		$("#" + name).show()
	else
		$("#" + name).hide()
};

/**
 * Given an array of checkboxes, returns an array with the boxes that are
 * checked
 * 
 * @param checkboxes
 * @returns {Array}
 */
function getChecked(checkboxes) {
	var checked_checkboxes = []
	for (var i = 0; i < checkboxes.length; i++) {

		if ($("#" + checkboxes[i][1]).is(':checked')) {
			checked_checkboxes.push(checkboxes[i][0])

		}
	}
	return checked_checkboxes;
};

/**
 * A convenience function to aid in the process of repeatedly checking if a long
 * running servlet process has finished
 * 
 * @param url
 *            The servlet to connect to
 * @param params
 *            The parameters to provide the servlet
 * @param progressbar_name
 *            The name of the progress bar that should be updated
 * @param status_text_name
 *            The name of the status text element that should be updated
 * @param success_action_function
 *            A function to execute when the long running process is complete.
 */
function checkStatusOfLongRunningServletTask(url, params, progressbar_name,
		status_text_name, success_action_function) {

	console.log(url + params);

	$.ajax({
		type : "GET",
		dataType : "text",
		beforeSend : function() {
			// $("#" + status_text_name).text("");
			toggle_progress(progressbar_name, true)
		},
		url : url + params,
		success : function(data, status, xhr) {

			var json = $.parseJSON(data);
			$("#" + status_text_name).text(
					json.status + " "
							+ (new Date().getTime() - new Date(json.starttime))
							+ " ms elapsed.");
			console.log(json.uuid);
			if (json.status.indexOf('processing') > -1) {
				tid = setTimeout(function() {
					checkStatusOfLongRunningServletTask(url, "?taskid="
							+ json.uuid, progressbar_name, status_text_name,
							success_action_function)
				}, json_refresh_interval);
			} else if (json.status == 'OK') {
				toggle_progress(progressbar_name, false);
				success_action_function(json);
			} else if (json.status == 'Download Ready') {
				success_action_function(json);
				toggle_progress(progressbar_name, false);
			}
			else if (json.status.search("java.lang.") != -1 ) // indicates an error
				toggle_progress(progressbar_name, false);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			$("#" + status_text_name).append(textStatus + ":" + errorThrown);
			toggle_progress(progressbar_name, false);
		}
	});
};

/**
 * A convenience function to update the popup windows for selecting columns
 * 
 * @param table
 *            The name of the table to update
 * @param json
 * @param id_prefix
 * @param include_checkboxes
 * @returns {Array}
 */
function updatePopupTable(table, json, id_prefix, include_checkboxes) {
	$('#' + table + ' tbody tr').remove();

	checkboxes_array = [];

	$.each(json.columns, function(i, item) {
		checkboxes_array.push([ item.name, id_prefix + item.name ]);
		var html = '<tr><td>' + item.name + '</td><td>' + item.datatype
				+ '</td><td>' + item.position + '</td>'
		if (include_checkboxes)
			html += '<td><input type="checkbox" id="' + id_prefix + item.name
					+ '" unchecked></td>' + '</tr>';

		// append new html to table body
		$('#' + table + "  tbody").append(html);
		// let the plugin know that we made a update
		$("#" + table).trigger("update");
	});
	return checkboxes_array;
};

function updateTableWithJson(tablename, json) {
	$('#' + tablename + ' thead tr').remove();
	var htmlStringHeader = "";

	$.each(json.datatable.headers, function(i, item) {
		htmlStringHeader += "<th>" + item + "</th>";
	});
	
	$("#" + tablename + " thead").append("<tr>" + htmlStringHeader + "</tr>");
	$('#' + tablename + ' tbody tr').remove();

	$.each(json.datatable.rows, function(i, item) {

		var htmlString = "";
		$.each(item, function(j, v) {
			htmlString += "<td class=\""
					+ json.datatable.headers[j].replace("%", "_") + "\">" + v
					+ "</td>";
		});

		$("#" + tablename + " tbody").append("<tr>" + htmlString + "</tr>");
	});

	$("#" + tablename).tablesorter();
	$("#" + tablename).trigger("updateAll");
}

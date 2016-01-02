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

availableTags = [];
currentDate = new Date();

json_refresh_interval = 1000; // interval on how often to check if a long run
// servlet process has finished
json_first_check_interval = 500; // the initial check interval - should be
// low in case a query returns immediately

/*
 * The checkboxes that are used to keep track which columns are selected
 */
checkboxes_profile = []

/**
 * Initialize the UI components
 */
function initialize_components() {

	// enable tooltips on UI components
	$(document).tooltip();

	/**
	 * Initialize the progress bars on each tab
	 */
	var progressbar_names = [ "progressbar_profile" ];
	$.each(progressbar_names, function(index, name) {

		$("#" + name).progressbar({
			value : false
		});
		$("#" + name).find(".ui-progressbar-value").css({
			"background" : '#A4A4A4'
		});

		toggle_progress(name, false);
	});

	/**
	 * Button to send to editor
	 */
	$('#action-profile').click(
			function() {
				params = "?op=" + encodeURI($('#fileDisplayArea').val());
				tid = setTimeout(function() {
					checkStatusOfLongRunningServletTask(url + "ProcessServlet",
							params, 'progressbar_profile', 'info_profile',
							function(json) {
								$('#fileDisplayArea').val(json.datatable)

							});
				}, json_first_check_interval);

			});

	$('#action-edit').click(function() {

		params = "?data=" + encodeURI($('#fileDisplayArea').val());
		

		window.location = "eventsEditor.html" + params;

	});

	$('#action-save').click(
			function() {
				
				
					
					$.ajax({
						type : "GET",
						dataType : "text",
						beforeSend : function() {
						},
						url : url + "SesameWriteServlet?data="
								+ encodeURI($('#fileDisplayArea').val()), // note the
																	// encodeURI
																	// takes care of
																	// URL encoding
						success : function(data, status, xhr) {

							var json = $.parseJSON(data);
							// console.log(json);
							$('#info_profile').text(json.status)
							window.location = "eventsEditor.html"

						},
						error : function(jqXHR, textStatus, errorThrown) {
							console.log(errorThrown);
						}
					});
					
					
					
			});

};
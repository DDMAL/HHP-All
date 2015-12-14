<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type='text/javascript' src='js/jquery.js'></script>
<script type='text/javascript' src='js/jquery-migrate.min.js'></script>
<script type='text/javascript' src='js/jquery-2.1.4.min.js'></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>Relation Extraction System: SRED</title>
</head>

<script type="text/javascript">
	function inputshow(dt) {
		console.log(dt);
		$("#p1").text('Selected' + dt);
	}
</script>
<body
	class="single single-post postid-29987 single-format-standard it-background bp-page colorbox-slideshow">
	<div id="ajax-error"></div>
	<div id="fb-root"></div>
	<div class="after-header no-sticky no-logo">
		<div
			class="container-fluid no-padding single-wrapper sidebar-right post-29987 post type-post status-publish format-standard has-post-thumbnail hentry category-information-gathering tag-infogathering tag-osint tag-osint-analysis"
			data-location="single-page" itemscope
			itemtype="http://schema.org/Article">
			<div class="row">

				<div class="col-md-12">

					<div class="container-inner single-content-wrapper classic-post">
						<div class="row">
							<div class="col-md-9 single-post-selector">
								<div class="single-page   category- hidden-contents-menu">
									<h1 class="padded-panel main-title single-title entry-title">
										Relation Extraction System: SRED</h1>
									<p id="breadcrumbs" class="sticky-color">
										<span rel="v:child" typeof="v:Breadcrumb">System of
											Relation Extraction on Documents</span>
									</p>

									<div id="post-29987" class="post-content post-selector"
										data-postid="29987">
										<div class="post-right content-panel wide no-control-bar">
											<div class="image-container">
												<div class="featured-image-wrapper">
													<div class="featured-image-inner">
														<img width="1000" height="485"
															src="img/technology.jpg"
															class=" wp-post-image" alt="information-extraction"
															title="Information Extraction: MITIE" itemprop="image" />
													</div>
												</div>
											</div>
											<div class="the-content padded-panel">
												<div class="it-ad-wrapper clearfix">
													<div id="content-anchor-inner" class="clearfix">
														<p style="text-align: justify;">
														The SRED (Relation Extraction on Documents) project is part of the Human History Project. 
														SRED provides a state-of-the-art relation
														extraction system including document
														processing, document extraction, and extraction editing
														tools. The current version supports relation extraction on documents
														in English.
														</p>
														
														<h3>Process file</h3>
														<form name="uploadandfilterForm" method="POST"
															enctype="MULTIPART/FORM-DATA" action="upload">
															<p style="text-align: justify;">Choose a document in txt format.
																Most texts from wikipedia,
																or other online sources are supported.
																The uploaded file will be added to File List below.</p>
															<a href="#" class='file'> <input type="file"
																name="file" id="uploadandfilterfile"
																Onchange="inputshow(this.value)" />Choose File
															</a> <label id="p1"></label> <input type="submit"
																class='styled btn  btn-info ' name="submit"
																value="Upload"> <input type="reset"
																class='styled btn  btn-info ' name="reset" value="reset">
														</form>
														<p> </p>
														 <table>
															<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
															<%
																File file = new File(application.getInitParameter("storageaddress"));
																
																String[] filelist = file.list();
																//String[] filelist = new String[]{};
																List<String> txtlist = new ArrayList<String>();
																for (int i = 0; i < filelist.length; i++) {
																	if (filelist[i].endsWith(".txt")) {
																		txtlist.add(filelist[i]);
																	}
																}
																for (int i = 0; i < txtlist.size(); i++) {
																	out.append("<tr><td><a href=downurl?filepath=" + txtlist.get(i) + ">" + txtlist.get(i) + "</a></td>\n");
																	out.append("<td><a role='button' class='styled btn  btn-info ' href=FilterServlet?filepath="
																			+ txtlist.get(i) + ">" + "Processing</a></td>\n");
																	out.append("<td><a role='button' class='styled btn  btn-info ' href=OpieServlet?filepath="
																			+ txtlist.get(i) + ">" + "Extraction</a></td>\n");
																	out.append("<td><a role='button' class='styled btn  btn-info ' href=LoadServlet?filepath="
																			+ txtlist.get(i) + ">" + "Reformat</a></td>\n");
																	out.append("<td><a role='button' class='styled btn  btn-info ' href=DeleteServlet?filepath="
																			+ txtlist.get(i) + ">" + "Delete</a></td>\n");
																	out.append("<td><a role='button' class='styled btn  btn-info ' href=DoitAllServlet?data=Heinrich%20Glarean%0Awas%0Aa%20Swiss%20music%20theorist%2C%20poet%20and%20humanist%0AHeinrich%20Glarean%0Awas%20born%0Ain%20Mollis>" + "DoitAll</a></td>\n");
																			
																	out.append("<td><a role='button' class='styled btn  btn-info ' href=eventsEditor.html?data=Heinrich%20Glarean%0Awas%0Aa%20Swiss%20music%20theorist%2C%20poet%20and%20humanist%0AHeinrich%20Glarean%0Awas%20born%0Ain%20Mollis>" + "Edit Events</a></td>\n");
																	out.append("<tr/>\n");
																}
															%>
														</table>

														<h3>Edit extractions in Event Editor</h3>
														</p>
														<p>
															Now the extraction could be edited in <a
																href="http://humanhistoryproject.ca/eventsEditor/"
																class="styled btn  btn-info">Events Editor</a>. First
															download the file you want to load, then you can load the
															file from Event Editor.
														</p>
														
														<h3>Processing Document</h3>
														</p>
														<p style="text-align: justify;">Replace pronouns with names based on Stanford
															Coreference Resolution for better extraction on
															documents. Current Information Extraction tools don't
															well support texts with brackts. So processing will also
															remove brackets with content inside them. This step will
															take a minute for loading models. Names are
															recognized by NER tool.</p>


														<h3>Document Extraction</h3>
														</p>
														<p style="text-align: justify;">
															Perforam Relation Extraction based on OpenIE 4.0. Then
															you could transform the extraction file into format could
															be read by <a
																href="http://humanhistoryproject.ca/eventsEditor/"
																class="styled btn  btn-info">Events Editor</a>. This process 
															takes a minute because several models are loading.
														</p>

														
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3 single-sidebar-selector clearfix">
								<div class="content-panel fixed-object single-sidebar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/after-header-->
	<style type="text/css">
.container-inner, .builder-topten.tall .scroller, .builder-topten .scroller,
	.builder-trending .scroller, .builder-connect .connect-inner,
	.builder-utility .utility-inner, .loop-panel .article-info, .loop-panel .article-meta,
	.article-info, .sections-inner, .builder-connect .connect-email input.email-textbox,
	div.scrollingHotSpotLeft, div.scrollingHotSpotRight, .post-panel .category-icon-wrapper,
	.post-left, .longform-post .longform-left, .content-panel, .ratings .rating-label,
	.ratings .rating-value-wrapper, .builder-utility ul li ul, .loop-panel.comparing .overlay-link,
	a.loop-play, .authorinfo .thumbnail, .postinfo .post-source a,
	.trending-bar, .widget-topics .trending-bar {
	background: #ffffff
}

.loop-panel .article-fade {
	background: -moz-linear-gradient(top, rgba(255, 255, 255, 0) 0%,
		rgba(255, 255, 255, 1) 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(255,
		255, 255, 0)), color-stop(100%, rgba(255, 255, 255, 1)));
	background: -webkit-linear-gradient(top, rgba(255, 255, 255, 0) 0%,
		rgba(255, 255, 255, 1) 100%);
	background: -o-linear-gradient(top, rgba(255, 255, 255, 0) 0%,
		rgba(255, 255, 255, 1) 100%);
	background: -ms-linear-gradient(top, rgba(255, 255, 255, 0) 0%,
		rgba(255, 255, 255, 1) 100%);
	background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 0%,
		rgba(255, 255, 255, 1) 100%);
}

#comments .comment-ratings, .reactions-wrapper, .postinfo, .authorinfo,
	.recommended-categories a, #respond, #comments a.btn, #respond .form-control,
	#respond #submit, #respond .ratings .rating-value-wrapper, .postinfo .post-source a:hover.table-comparison .row-label,
	.table-comparison tr.separator td, .table-comparison tr.separator.subheader td,
	.table-comparison .row-label, .load-more, .bp-page #buddypress .activity-list li.load-more,
	.bp-page #buddypress .activity-list li.load-newest, .last-page,
	.trending-color, .trending-color, .trending-color-layer {
	background: #F5F5F5
}

#logo-bar, #logo-bar .container-inner {
	background: #FFFFFF
}

#logo, #logo h1 a, #logo h1 a:hover, #logo .subtitle {
	color: #999999
}

#sticky-bar, #sticky-bar .container-inner, #sticky-bar .loop-panel .article-info,
	#sticky-bar .loop-panel .article-meta, #sticky-bar .article-info,
	#section-menu.standard-menu ul ul li a.parent-item:hover, #section-menu.standard-menu ul ul li.over>a.parent-item,
	#section-menu.standard-menu ul ul li.current-menu-item>a.parent-item,
	#section-menu.standard-menu ul ul li a:hover, #section-menu.standard-menu ul ul li.over>a,
	#section-menu.standard-menu ul ul li.current-menu-item>a, #section-menu .placeholder,
	#section-menu .term-list, #section-menu .category-icon-wrapper {
	background: #FFFFFF
}

#sticky-bar, #section-menu>ul>li>a, #section-menu .article-info,
	#section-menu .header, #section-menu .read-more, #section-menu .compact-panel .article-title,
	.section-menu-mobile ul li a, #section-menu .mega-wrapper .term-list a,
	#sticky-bar span.theme-icon-search {
	color: #333333
}

.terms-more .sort-toggle.active span, .terms-more .sort-toggle.over span
	{
	color: #333333 !important
}

.section-menu-mobile ul li a:hover, .section-menu-mobile ul li.over>a,
	.section-menu-mobile ul li.current-menu-item>a {
	background-color: rgba(0, 0, 0, .2)
}

#nav-toggle span, #nav-toggle span:before, #nav-toggle span:after {
	background: #333333
}

.bar-header, .loop-panel .article-info, .loop-panel .article-meta,
	.pagination, .sections-inner, .compact-panel, .builder-connect .connect-inner,
	.builder-connect .connect-social, .builder-connect .connect-email input.email-textbox,
	.builder-utility .utility-inner, a.more-link, .builder-utility ul li ul,
	.contents-menu .contents-title, .single-page .divider-line, .procon-box .procon.pro,
	.procon-box .procon.con, .ratings .rating-value-wrapper, .single-page blockquote,
	.reactions-wrapper, .postinfo, .authorinfo, .reactions-label,
	.recommended-header, .comments-header, #comments .comment-ratings,
	#comments .comment-wrapper, h3#reply-title, #respond, .postinfo .post-source a,
	#comments ul ul .comment, .share-bar, .share-bar .addthis_toolbox,
	.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td, .the-content table>tbody>tr>td,
	.the-content table>tbody>tr>th, .recommended-categories a, #respond #submit,
	#comments a.btn, .form-control, .longform-post .share-bar .authorship,
	.author-panel, .load-more, .bp-page #buddypress .activity-list li.load-more,
	.bp-page #buddypress .activity-list li.load-newest, .last-page {
	border-color: #e7e7e7
}

.ratings .rating-line, .single-page .divider-line {
	background: #e7e7e7
}

body, .loop-panel .excerpt, .pagination a, .pagination>span.page-number,
	.pagination>span.page-numbers, .trending-toggle, .compare-toggle,
	.loop-panel .article-title, .bar-header .label-text, .sort-toggle, a.more-link,
	.bar-header .metric-text, .builder-connect .connect-email input.email-textbox,
	.social-badges a, .bar-header .sortbar-link, .control-bar .trending-toggle,
	h1.main-title, h1.page-title, .share-bar .author a, .share-bar .date:not(th),
	.share-bar .meta-comments a, .single-page .main-subtitle, .single-page blockquote small,
	.ratings .rating-value-wrapper, .ratings .hovertorate .hover-text,
	.big-like .labeltext, .big-like .thistext, .big-like a.like-button,
	.reaction.clickable, .postinfo a, .authorinfo a.author-name,
	.authorinfo .author-bio, #comments .comment-author a, #respond #submit,
	.ratings .hovertorate .theme-icon-down-fat, #respond p.logged-in-as,
	.table-comparison .stars span, .table-comparison .total-row .stars span,
	.table-comparison .heat-index, .table-comparison .view-count,
	.table-comparison .numcount, .comparison-page .compare-count,
	.the-content .table-comparison .category-list a, .the-content .table-comparison .tag-list a,
	a.load-more, .bp-page #buddypress .activity-list li.load-more a,
	.bp-page #buddypress .activity-list li.load-newest a, .compact-panel .stars span,
	.overlay-panel .stars span, .bar-header .label-text span, .trending-bar .trending-meta .metric,
	.trending-bar .rating .number, .trending-bar .rating .letter,
	#wp-calendar tbody, .trending-bar .heat-index .theme-icon-flame {
	color: #333333
}

.builder-utility ul a, .builder-utility .home-button a, .authorinfo .author-profile-fields a,
	#comments a.comment-meta {
	color: #333333 !important
}

a:not (.styled ):hover, #section-menu ul li a.parent-item:hover,
	#section-menu ul li.over>a.parent-item, #section-menu ul li.current-menu-item>a.parent-item,
	#section-menu .active .article-title, #section-menu .read-more:hover,
	.terms-more .sort-toggle.active span, .terms-more .sort-toggle.over span,
	.compare-go a:hover, #section-menu.standard-menu ul li a.parent-item:hover,
	#section-menu.standard-menu ul li.over>a.parent-item, #section-menu.standard-menu ul li.current-menu-item>a.parent-item,
	#section-menu.standard-menu ul li a:hover, #section-menu.standard-menu ul li.over>a,
	#section-menu.standard-menu ul li.current-menu-item>a, #section-menu .terms-more .sort-toggle.active span,
	#section-menu .terms-more .sort-toggle.over span, #section-menu .mega-wrapper .term-list a:hover,
	#section-menu .mega-wrapper .term-list a.active, #section-menu .term-link:hover,
	.sort-toggle.active, .sortbar-link:hover, .compact-panel.active .article-title,
	.social-counts a:hover .social-number, .widgets .it-widget-tabs .sort-buttons a:hover,
	#footer .widgets .it-widget-tabs .sort-buttons a:hover, .widgets .it-widget-tabs .ui-tabs-active a,
	#footer .widgets .it-widget-tabs .ui-tabs-active a, a.reply-link, a.more-link:hover,
	#main-menu ul li a:hover, #main-menu ul li.over>a, #main-menu ul li.current-menu-item>a,
	.highlighted-label, .sort-buttons a.active, .sort-buttons a:hover,
	.widget-section a.more-link:hover, .topic-panel.active .topic-name,
	.trending-bar.active .title, #comments-social-tab a:hover, .widgets .social-badges a:hover,
	a.nav-link:hover, .contents-menu .nav>li>a:hover, .reaction.clickable.active,
	.reaction.selected, .reaction.selected .theme-icon-check, .postinfo a:hover,
	#comments .comment-pagination a:hover, .utility-menu a:hover,
	.utility-menu li.over>a, .utility-menu li.current-menu-item a,
	.utility-menu li.current-menu-parent>a, .utility-menu li.current-menu-ancestor>a,
	.widgets #menu-utility-menu a:hover, .widgets #wp-calendar a:hover,
	.contents-menu .nav>li.active>a, .builder-connect .social-badges a:hover,
	.woocommerce.woocommerce-page ul.cart_list li a:hover, .woocommerce.woocommerce-page ul.product_list_widget li a:hover
	{
	color: #e9211f;
}

.builder-utility ul a:hover, .builder-utility .home-button a:hover {
	color: #e9211f !important
}

.new-articles .selector.active, .new-articles .selector.over,
	.bar-toggle:hover, .bar-toggle.active, .bar-dropdown .bar-submit.active,
	.recommended-categories a:hover, .recommended-categories a.active,
	#comments .pagination .active, #comments .pagination a:active,
	#comments .pagination a:hover, #comments .pagination span.page-number,
	#comments .pagination span.page-numbers.current, .section-toggle.active,
	.section-toggle.over, .woocommerce.woocommerce-page span.onsale,
	.woocommerce.woocommerce-page #content input.button, .woocommerce.woocommerce-page #respond input#submit,
	.woocommerce.woocommerce-page a.button, .woocommerce.woocommerce-page button.button,
	.woocommerce.woocommerce-page input.button {
	background: rgba(233, 33, 31, 1);
	filter: none;
}

.circled-icon, .meter-wrapper .meter, .large-meter .meter-wrapper .meter
	{
	border-color: #e9211f;
}

.details-box-wrapper, .ratings .total-wrapper, .woocommerce.woocommerce-page ul.products li.product a img
	{
	border-bottom-color: #e9211f;
}

.new-articles .selector.active .triangle, .new-articles .selector.over .triangle
	{
	border-top-color: #e9211f
}

.the-content, .the-content p {
	font-size: 18px;
}

.the-content p {
	line-height: 1.42
}

body.it-background {
	background-color: #FFFFFF !important;
}

.billboard-gradient {
	background: -moz-linear-gradient(top, rgba(255, 255, 255, 0) 68%,
		rgba(255, 255, 255, 1) 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(68%, rgba(255
		, 255, 255, 0)), color-stop(100%, rgba(255, 255, 255, 1)));
	background: -webkit-linear-gradient(top, rgba(255, 255, 255, 0) 68%,
		rgba(255, 255, 255, 1) 100%);
	background: -o-linear-gradient(top, rgba(255, 255, 255, 0) 68%,
		rgba(255, 255, 255, 1) 100%);
	background: -ms-linear-gradient(top, rgba(255, 255, 255, 0) 68%,
		rgba(255, 255, 255, 1) 100%);
	background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 68%,
		rgba(255, 255, 255, 1) 100%);
}

#logo a {
	font-size: 43px;
	letter-spacing: -2px
}

#buddypress table.profile-fields tr td.label {
	color: #333;
	display: table-cell
}

#breadcrumbs a:hover {
	color: #489f48;
	text-decoration: none
}

#breadcrumbs {
	display: block;
	text-align: left;
	padding: 0px 15px 10px;
	position: relative;
	width: 100%;
	height: auto;
	font-family: 'ForzaMedium';
	font-size: 18px;
	line-height: 20px;
	border-bottom: 1px solid #ccc;
}

.billboard-wrapper .review-label {
	display: none
}

.trending-toggle {
	display: none
}

.featured-image-inner {
	text-align: center
}

.top-menu {
	font-family: "ForzaBook";
}

.builder-boxes br.clearer {
	display: none
}

.box-overlay {
	display: none
}

.widget_e .article-title {
	background: #000
}

.theme-icon-star-full {
	display: none
}

.widget_f .sort-wrapper {
	display: none
}

.widget-trending .sort-wrapper {
	display: none
}

.widget-topten .sort-wrapper {
	display: none
}

.box-info h2 {
	background: #000
}

.rss-aggregator .page-numbers {
	font-size: 25px
}
</style>
	<style>
.file {
	position: relative;
	display: inline-block;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
	margin-bottom: 0;
	font-weight: normal;
	text-align: center;
	vertical-align: middle;
	border: 1px solid transparent;
	white-space: nowrap;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	border-radius: 4px;
	color: #fff;
	background-color: #5bc0de;
	border-color: #46b8da;
}

.file input {
	color: #fff;
	background-color: #5bc0de;
	border-color: #46b8da;
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	color: #fff;
	background-color: #5bc0de;
	border-color: #46b8da;
	text-decoration: none;
}
</style>

	<script type="text/javascript">
		var addthis_config = {
			data_track_clickback : false
		};
	</script>
</body>
</html>

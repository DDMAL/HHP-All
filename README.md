
To edit this project:

First, check out the project using "git clone".  Using the import utility, add the project to Eclipse (this should be already configured as an Eclipse project).  You'll need plugins for Maven and Apache Tomcat (you also may need to point Eclipse at your Tomcat installation).

The project need two jars not included in the git repo to function: stanford-corenlp-3.5.2-models.jar and openi-4.1.jar.  Both of these can be downloaded online and put in WebContent/WEB-INF/lib (don't forget to also go to proproties and add them to your classpath.)

If you're working locally, you might have to change urls.js in the javascript folder to reflect the correct port / url (usually localhost and 8080). 

Project Overview:

A lot of the project may be legacy code; we were unable to go through every file and see exactly what it was used for.  Right now, the Java Resources/src has all the servlets.

Filter.java: preprocesses text before doing the extraction on it.

LongRunningProccessServlet.java: allows servlets to give status updates while running.

ProcessServlet.java: Actually does the NLP to find the triples, using OpenIE.  This is what happens when you hit the analyze button.

SesameReadServlet.java: Reads all triples from the database and writes them out in a list of RDFTriples.

SesameWriteServlet.java: Triggers when you hit the "Save" button, writes the triples to Sesame.

GlobalVars.java: Contains a handle for OpenIE and the Sesame database.

In WebContent/, the files should be pretty self expanatory.  eventsEditor.html is the database front end, upload.html is the part that does the analysis and puts things into the datbabase.  WebContent/js/functions.js is where the buttons get mapped to the servlets.

For more information, please contact: Ichiro Fujinaga (ichiro.fujinaga@mcgill.ca).


Previous notes:

# HHP

Had to gitignore the following files because they exceed the Github limit of 100MB per file:

ExtractionS/WebContent/WEB-INF/lib/openie-4.1.jar
ExtractionS/WebContent/WEB-INF/lib/stanford-corenlp-3.5.2-models.jar

Make sure to add them to the WEB-INF/lib dir, otherwise the WAR will not have all required jars and the server will not do it's tasks.

# HHP

Had to gitignore the following files because they exceed the Github limit of 100MB per file:

ExtractionS/WebContent/WEB-INF/lib/openie-4.1.jar
ExtractionS/WebContent/WEB-INF/lib/stanford-corenlp-3.5.2-models.jar

Make sure to add them to the WEB-INF/lib dir, otherwise the WAR will not have all required jars and the server will not do it's tasks.

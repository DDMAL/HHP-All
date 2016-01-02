package ca.humanhistoryproject;

import java.util.Properties;

import edu.knowitall.openie.OpenIE;
import edu.knowitall.tool.parse.ClearParser;
import edu.knowitall.tool.postag.ClearPostagger;
import edu.knowitall.tool.srl.ClearSrl;
import edu.knowitall.tool.tokenize.ClearTokenizer;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class GlobalVars {

	/**
	 * The URL for Sesame where to store Triples
	 */
	public static String RepositoryURL = "http://humanhistoryproject.ca/openrdf-sesame/repositories/1";
	/**
	 * Open IE object
	 */
	public static OpenIE openIE;
	/**
	 * Stanford Object
	 */
	public static StanfordCoreNLP pipeline;

	/**
	 * Flag if OpenIE and StanfordCoreNLP has been initialized
	 */
	public static Boolean initialized = false;
	/**
	 * Flag whether to initialize OpenIE and StanfordCoreNLP on server startup
	 */
	public static Boolean init_on_startup = false;

	/**
	 * Initialize the components
	 */
	public static void initialize() {
		if (!initialized) {
			Properties props = new Properties();
			props.setProperty("annotators",
					"tokenize, ssplit, pos, lemma, ner, parse, dcoref");

			pipeline = new StanfordCoreNLP(props);

			openIE = new OpenIE(new ClearParser(new ClearPostagger(
					new ClearTokenizer(ClearTokenizer.defaultModelUrl()))),
					new ClearSrl(), false);

			initialized = true;
		}
	}

}
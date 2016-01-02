import java.io.File;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.nativerdf.NativeStore;

import edu.knowitall.openie.OpenIE;
import edu.knowitall.tool.parse.ClearParser;
import edu.knowitall.tool.postag.ClearPostagger;
import edu.knowitall.tool.srl.ClearSrl;
import edu.knowitall.tool.tokenize.ClearTokenizer;

public class GlobalVars {

	public static File dataDir = new File("~/Projects/HHP/HHP/ExtractionS/");
	//public static File dataDir = new File("/usr/share/tomcat7/.aduna/openrdf-sesame/repositories/1");
	
	
	public static Repository rep = new SailRepository(new NativeStore(dataDir));
	//public static Repository rep = new HTTPRepository("http://humanhistoryproject.ca/openrdf-sesame/repositories/1");
	public static RepositoryConnection conn = null;
	
	public static OpenIE openIE = new OpenIE(new ClearParser(new ClearPostagger(
			new ClearTokenizer(ClearTokenizer.defaultModelUrl()))),
			new ClearSrl(), false);
	
	
}

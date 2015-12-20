import edu.knowitall.openie.OpenIE;
import edu.knowitall.tool.parse.ClearParser;
import edu.knowitall.tool.postag.ClearPostagger;
import edu.knowitall.tool.srl.ClearSrl;
import edu.knowitall.tool.tokenize.ClearTokenizer;

public class GlobalVars {

	public static OpenIE openIE = new OpenIE(new ClearParser(new ClearPostagger(
			new ClearTokenizer(ClearTokenizer.defaultModelUrl()))),
			new ClearSrl(), false);
	public static String rootDir = "/Users/nkasch/Documents/HHP";
}

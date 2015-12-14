import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import scala.collection.Iterator;
import scala.collection.Seq;
import edu.knowitall.openie.Instance;
import edu.knowitall.openie.OpenIE;
import edu.knowitall.openie.Part;
import edu.knowitall.srlie.SrlExtraction.Argument;
import edu.knowitall.tool.parse.ClearParser;
import edu.knowitall.tool.postag.ClearPostagger;
import edu.knowitall.tool.srl.ClearSrl;
import edu.knowitall.tool.tokenize.ClearTokenizer;

public class Test {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		String b = "Heinrich%20Glarean%20%28also%20Glareanus%29%20%28June%201488%20%u2013%2028%20March%201563%29%20was%20a%20Swiss%20music%20theorist%2C%20poet%20and%20humanist.%20He%20was%20born%20in%20Mollis%20%28in%20the%20canton%20of%20Glarus%2C%20hence%20his%20name%29%20and%20died%20in%20Freiburg.%0A%0AAfter%20a%20thorough%20early%20training%20in%20music%2C%20he%20enrolled%20in%20the%20University%20of%20Cologne%2C%20where%20he%20studied%20theology%2C%20philosophy%2C%20and%20mathematics%20as%20well%20as%20music.%20It%20was%20there%20that%20he%20wrote%20a%20famous%20poem%20as%20a%20tribute%20to%20Emperor%20Maximilian%20I.%20Shortly%20afterwards%2C%20in%20Basle%2C%20he%20met%20Erasmus%20and%20the%20two%20humanists%20became%20lifelong%20friends.";
		 System.out.println(java.net.URLDecoder.decode(b));
		 
		 if (true)
			 return;

		
		// Filter filt = new Filter();
		// filt.filterdata("/Users/nkasch/Documents/HHP/example.txt");

		/*
		 * String fileRoot = ""; String jaddress =
		 * "/Users/nkasch/Documents/HHP/"; String systemenv = "linux"; String
		 * linuxparam = "sh";
		 * 
		 * String fullFilePath = "/Users/nkasch/Documents/HHP/example.txt";
		 * 
		 * OpenieFile of = new OpenieFile(); String bataddress =
		 * of.UseOpenie(fullFilePath, jaddress, systemenv);
		 * 
		 * System.out.println(bataddress); of.excjar(bataddress, systemenv,
		 * linuxparam);
		 */

		/*
		OpenIE openIE = new OpenIE(new ClearParser(new ClearPostagger(
				new ClearTokenizer(ClearTokenizer.defaultModelUrl()))),
				new ClearSrl(), false);

		String file = "Heinrich Glarean was a Swiss music theorist, poet and humanist. "
				+ "Heinrich Glarean was born in Mollis and died in Freiburg. "
				+ "After a thorough early training in music, Heinrich Glarean enrolled in the University of Cologne,"
				+ "where Heinrich Glarean studied theology, philosophy, and mathematics as well as music. "
				+ "It was there that Heinrich Glarean wrote a famous poem as a tribute to Emperor Maximilian I. "
				+ "Shortly afterwards, in Basle, Heinrich Glarean met Erasmus and the two humanists became lifelong friends.";

		for (String sentence : file.split("\\. ")) {

			Seq<Instance> extractions = openIE.extract(sentence);

			Instance[] arr = new Instance[extractions.length()];
			extractions.copyToArray(arr);

			for (Instance inst : arr) {
				StringBuilder sb = new StringBuilder();
				sb.append(inst.sentence() + "\n");
				Double conf = inst.confidence();
				String stringConf = conf.toString().substring(0, 4);
				sb.append(stringConf).append(" (")
						.append(inst.extr().arg1().text()).append("; ")
						.append(inst.extr().rel().text()).append("; ");

				Part[] arr2 = new Part[inst.extr().arg2s().length()];
				inst.extr().arg2s().copyToArray(arr2);
				for (Part arg : arr2) {
					sb.append(arg.text()).append("");
				}
				sb.append(")\n");
				System.out.println(sb.toString());
				}
			}*/
			 Load load=new Load();
			 String a = " Heinrich Glarean was a Swiss music theorist, poet and humanist Heinrich Glarean was born in Mollis\n"
					 + "0.96 (Heinrich Glarean; was; a Swiss music theorist, poet and humanist Heinrich Glarean was born in Mollis)\n"
					 + "\n"
					 + "Heinrich Glarean was a Swiss music theorist, poet and humanist.\n"
					 + "0.96 (a Swiss music theorist, poet and humanist Heinrich Glarean; was born; in Mollis)"
			 		+ "\n"
			 		+ "  ";
			 System.out.println(load.Loadfilter(a));

		

	}
}

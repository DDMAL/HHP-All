
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class OpenieFile {

	public String UseOpenie(String filepath, String jaddress, String systemenv)
			throws FileNotFoundException, IOException {

		if (systemenv.equals("windows")) {
			File file = new File("filepath");
			String str1 = "java -Xmx4g -XX:+UseConcMarkSweepGC ";
			String strJarAddress = "-jar " + jaddress + "openie-4.1.jar --split ";
			String inputfile = filepath + " ";

			// Creat outputfile
			String outputfile = filepath.substring(0, filepath.length() - 4) + "_extraction.txt";
			PrintWriter outputfileWriter = new PrintWriter(outputfile);
			outputfileWriter.print("");
			outputfileWriter.close();

			// Creat bat files
			String bataddress = jaddress + "temp.bat";
			System.out.println(bataddress);
			PrintWriter batwriter = new PrintWriter(bataddress);
			batwriter.println(str1 + strJarAddress + "" + inputfile + outputfile);
			batwriter.println("exit");
			batwriter.close();
			return bataddress;
		} else if (systemenv.equals("linux")) {
			File file = new File("filepath");
			String str1 = "java -Xmx4g -XX:+UseConcMarkSweepGC ";
			String strJarAddress = "-jar " + jaddress + "openie-4.1.jar --split ";
			String inputfile = filepath + " ";

			// Creat outputfile
			String outputfile = filepath.substring(0, filepath.length() - 4) + "_extraction.txt";
			// PrintWriter outputfileWriter = new PrintWriter(outputfile);
			// outputfileWriter.print("");
			// outputfileWriter.close();

			// Creat bat files
			String bataddress = jaddress + "temp.sh";
			System.out.println(bataddress);
			PrintWriter batwriter = new PrintWriter(bataddress);
			batwriter.println(str1 + strJarAddress + "" + inputfile + outputfile);
			batwriter.println("exit");
			batwriter.close();
			return bataddress;

		} else {
			return "";
		}
	}

	public void excjar(String bataddress, String systemenv, String linuxparam) throws IOException, InterruptedException {

		Runtime rt = Runtime.getRuntime();
		if (systemenv.equals("windows")) {
			rt.exec("cmd.exe /c start " + bataddress).waitFor();
		} else if (systemenv.equals("linux")) {
			String[] cmd={"sh", bataddress }; 
			System.out.println(cmd[0] + "\t" + cmd[1]);
			rt.exec(cmd).waitFor();

		}
	}

}

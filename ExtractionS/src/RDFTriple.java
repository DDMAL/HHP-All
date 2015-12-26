
public class RDFTriple {
	
	public String Subject;
	public String Predicate;
	public String Object;
	public String GraphName;
	public String Index;
	
	public RDFTriple(String subject, String predicate, String object, String graphname, String index)
	{
		this.Subject = subject;
		this.Predicate = predicate;
		this.Object = object;
		this.GraphName = graphname;
		this.Index = index;
	}
	
}

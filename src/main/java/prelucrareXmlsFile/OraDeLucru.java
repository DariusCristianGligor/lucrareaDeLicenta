package prelucrareXmlsFile;

public class OraDeLucru {
	String subject;
	String profesor;
	String ora;

	public OraDeLucru(String subject, String profesor, String ora) {
		this.subject = subject;
		this.profesor = profesor;
		this.ora = ora;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}
	
}
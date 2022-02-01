package prelucrareXmlsFile;

import java.util.ArrayList;
import java.util.List;

public class AcoperireSalaDeClasa {
	private String Name;
	private String Ziua;
	private List<OraDeLucru>OreDeLucru;
	public AcoperireSalaDeClasa(String name, String ziua) {
		this.OreDeLucru = new ArrayList();
		this.Name = name;
		this.Ziua = ziua;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getZiua() {
		return Ziua;
	}
	public void setZiua(String ziua) {
		Ziua = ziua;
	}
	public List<OraDeLucru> getOreDeLucru() {
		return OreDeLucru;
	}
	public void setOreDeLucru(List<OraDeLucru> oreDeLucru) {
		OreDeLucru = oreDeLucru;
	}
	public void AddOraDeLucru(OraDeLucru oraDelucru) {
		this.OreDeLucru.add(oraDelucru);
	}
	
}
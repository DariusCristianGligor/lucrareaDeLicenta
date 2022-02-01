package prelucrareXmlsFile;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//jexel;
public class Principal {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		List<OraDeLucru> acoperireSaliDeClasa = new ArrayList<OraDeLucru>(); 
		obtinemNumarulDeSubgrupe("orar_engleza.xlsx");
		getOrarProfesor("Selea","ccc.xlsx");
		extragereAcoperireSaliDeClasa("ccc.xlsx"); 
	}
	
	public static void extragereAcoperireSaliDeClasa(String fileName) {
		List<AcoperireSalaDeClasa> acoperireSalaDeClase = new ArrayList();
		HashMap<String,Integer>saliDeClasa = new HashMap();
		saliDeClasa.put(fileName, null);
		String ziuaCurenta = null;
		String oraDesfasurarii = null;
		List<OraDeLucru> acoperireSaliDeClasa = new ArrayList<OraDeLucru>(); 
		//o structure de genu sala de clasa, zi si o lista cu orele respective;
		try {
			File file = new File(fileName); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
					if(ZiuaSaptamanii(cell.getStringCellValue()) != null){
						ziuaCurenta = cell.getStringCellValue();
				  }								
					else if((ziuaCurenta != null) && (esteOraDeDesfasurare(cell.getStringCellValue())!= null)){
								oraDesfasurarii = cell.getStringCellValue();
				  }
				  else if((ziuaCurenta != null) && (oraDesfasurarii != null) && !cell.getStringCellValue().contains("OBSERVATIE")&&!cell.getStringCellValue().contains("Mar")){  
					  String[] oreDeLucru = cell.getStringCellValue().split("/");
					  for(String oraDeLucru: oreDeLucru){
						  System.out.println("aaaaaaaaaaaaaaaaaaa:" + cell.getStringCellValue() + " "+ ziuaCurenta);
						  if(saliDeClasa.containsKey(getNumeleSaliiDeClasa(cell.getStringCellValue())+ziuaCurenta)==true)
						  {
							  for(AcoperireSalaDeClasa acoperireSalaDeClasa: acoperireSalaDeClase)
							  {
								  if(acoperireSalaDeClasa.getName() ==  getNumeleSaliiDeClasa(cell.getStringCellValue()) && acoperireSalaDeClasa.getZiua() == ziuaCurenta)
								  {
									  acoperireSalaDeClasa.AddOraDeLucru(new OraDeLucru(getMaterie(oraDeLucru),getProfesorulOrei(oraDeLucru),oraDesfasurarii));
								  }
							  }
						  }
						  else {
							  acoperireSalaDeClase.add(new AcoperireSalaDeClasa(getNumeleSaliiDeClasa(cell.getStringCellValue()), ziuaCurenta));
							  acoperireSalaDeClase.get(acoperireSalaDeClase.size()-1).AddOraDeLucru(new OraDeLucru(getMaterie(oraDeLucru),getProfesorulOrei(oraDeLucru),oraDesfasurarii));
							  saliDeClasa.put(getNumeleSaliiDeClasa(cell.getStringCellValue())+ziuaCurenta, 1);
						  }
				  		}
				  }
						break;
					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
						System.out.print("Darius here: " + cell.getNumericCellValue() + "\t\t\t");
						break;
					default:
					}
				}
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 for(AcoperireSalaDeClasa acoperireSalaDeClasa: acoperireSalaDeClase)
		 {
			 System.out.println(acoperireSalaDeClasa.getName()+ " in ziua "+acoperireSalaDeClasa.getZiua()+ " are orele: ");
			 for(OraDeLucru oraDeLucru: acoperireSalaDeClasa.getOreDeLucru())
			 {
				 System.out.println(oraDeLucru.ora +" "+ oraDeLucru.profesor +" " + oraDeLucru.subject);
			 }
		 }
	}

	private static String esteOraDeDesfasurare(String stringCellValue) {
		if(stringCellValue.contains("-") && stringCellValue.contains(".") && (stringCellValue.contains("0") || stringCellValue.contains("3")))
			return stringCellValue;
		return null;
	}

	private static String getMaterie(String stringCellValue) {
		String[] secventeMaterie = stringCellValue.split("-");
		return secventeMaterie[0];	
	}

	private static String getProfesorulOrei(String stringCellValue) {
		String[] secventeProfesor = stringCellValue.split("-");
		return secventeProfesor[1];	
	}

	private static String getNumeleSaliiDeClasa(String stringCellValue) {
		System.out.println(stringCellValue);
		String[] secventeSala = stringCellValue.split("-");
			
		return secventeSala[2];	
	}

	public static void obtinemNumarulDeSubgrupe(String fileName)
	{
		int numberOfSg = 0;
		int ok = 0;
		try {
			File file = new File(fileName); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			while (itr.hasNext() && ok == 0) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
						if (cell.getStringCellValue().toLowerCase().contains("subgrupa"))
							ok = 1;
						if (cell.getStringCellValue().toLowerCase().contains("sg.")) {
							numberOfSg++;
							System.out.println(cell.getStringCellValue());
						}
						break;
					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
						System.out.print("Darius here: " + cell.getNumericCellValue() + "\t\t\t");
						break;
					default:
					}
				}
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ok == 1)
			System.out.println("numarul de subgrupe este: " + numberOfSg);
		else
			System.out.println("Nu avem subgrupe, posibil sa fii ales orar-ul gresit");
	}
	public static void getOrarProfesor(String name, String fileName)
	{
		String ziuaCurenta="";
		String oraCurenta="";
		int numberOfSg = 0;
		String orarProfesor="";
		try {
			File file = new File(fileName); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
						if(ZiuaSaptamanii(cell.getStringCellValue()) != null)
						{
							ziuaCurenta = ZiuaSaptamanii(cell.getStringCellValue());
							oraCurenta = null;
						}	
						if(oraZi(cell.getStringCellValue())!=null)
						{
							oraCurenta=cell.getStringCellValue();
						}
						if (cell.getStringCellValue().toLowerCase().contains(name.toLowerCase())) {
//							System.out.print("aaa "+cell.getStringCellValue());
							orarProfesor= orarProfesor + esteGasit(name,cell.getStringCellValue(),"M") + " " + ziuaCurenta + "- " + oraCurenta+"\n";
						}
						break;
					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
						System.out.print("Darius here: " + cell.getNumericCellValue() + "\t\t\t");
						break;
					default:
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	System.out.println("Orarul profesorului "+ name +" este "+ orarProfesor);
	}

public static String ZiuaSaptamanii(String celulaTabel) {
	
	List<String>zileleSaptamanii=new ArrayList<String>();
	zileleSaptamanii.add("Luni");
	zileleSaptamanii.add("Marți");
	zileleSaptamanii.add("Miercuri");
	zileleSaptamanii.add("Joi");
	zileleSaptamanii.add("Vineri");
	zileleSaptamanii.add("Sâmbătă");
	zileleSaptamanii.add("Duminică");
	
	for(String ziua : zileleSaptamanii)
	{
		if(ziua.contentEquals(celulaTabel) == true)
			return ziua;
	}
	return null;
}




public static String oraZi(String celulaTabel) {
	
	List<String>oreleZilei=new ArrayList<String>();	
	oreleZilei.add("8.00-9.30");
	oreleZilei.add("9.40-11.10");
	oreleZilei.add("11.20-12.50");
	oreleZilei.add("13.00-14.30");
	oreleZilei.add("16.20-17.50");
	oreleZilei.add("18.00-19.30");
	oreleZilei.add("19.40-21.10");
	
	for(String ora : oreleZilei)
	{
		if(ora.contentEquals(celulaTabel) == true)
			return ora;
	}
	
	return null;
}
public static String esteGasit(String name, String celulaTabel,String initialaPrenume)
{	
	String orarProfesor;
	if(celulaTabel.contains("/") == true)
	{
		String[] secventeTabel = celulaTabel.split("/");
		for(String x: secventeTabel)
		{
			if(x.toLowerCase().contains(name) == true)
				{
					String []detaliiCelula = x.split("-");
					return detaliiCelula[0]+" in sala de clasa: "+detaliiCelula[2]; 
				}
		}
	}
	else {

		String secventaTabel=celulaTabel;
		if(secventaTabel.toLowerCase().contains(name.toLowerCase()) == true)
		{
			String []detaliiCelula = secventaTabel.split("-");
			String []detaliiProfesor = detaliiCelula[1].split(" ");
			if(detaliiProfesor.length>2)
			{if(detaliiProfesor[detaliiProfesor.length-2].toLowerCase().contains(initialaPrenume.toLowerCase()))
				return detaliiCelula[0]+" in sala de clasa: "+detaliiCelula[2];}
			else {
				return detaliiCelula[0]+" in sala de clasa: "+detaliiCelula[2];
			}
		}
	}
		
	return null;
}
}

package Exercice19;

import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class PersonWriter extends FileWriter {

	
	FileWriter fW;
	
	public PersonWriter(FileWriter fW) throws IOException {
		super(FileDescriptor.in);
		this.fW = fW;
	}

	void write (List<Object> beans) {
		

		AnalyzeBean a = new AnalyzeBean ();
		
		try (FileWriter freader = this.fW ; 
			BufferedWriter person = new BufferedWriter(freader)) {
			
			for (Object p : beans) {
				
				if (a.getProperties(p).contains("salary")) {
					Employee pEmployee = (Employee) p ;
					String firstName = (String) a.get2(pEmployee, "firstName");
					String lastName = (String) a.get2(pEmployee, "lastName");
					String age = Integer.toString((int) a.get2(pEmployee, "age"));
					String salary = Integer.toString((int) a.get2(pEmployee, "salary"));
					String s = "Employe : firstName = " + firstName + ", lastName = " + lastName + ", age = " + age + ", salaire = " + salary ; 
					 
					person.write(s);
					person.write("\n");
				}
				else {
					Person pPerson = (Person) p ;
					String firstName = (String) a.get2(pPerson, "firstName");
					String lastName = (String) a.get2(pPerson, "lastName");
					String age = Integer.toString((int) a.get2(pPerson, "age"));
					String s = "Person : firstName = " + firstName + ", lastName = " + lastName + ", age = " + age ;
					 
					person.write(s);
					person.write("\n");
				}
					  
			
			}
		} catch (IOException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
	}
}

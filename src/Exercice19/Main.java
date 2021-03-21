package Exercice19;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		// TODO Auto-generated method stub

		
		AnalyzeBean a = new AnalyzeBean();
		Employee employee = new Employee ("Ziden","Zinedine", 47, 1500);
		Person person = new Person ("Ziden","Zinedine", 47);
		
				// Question 1 : getClassName
		System.out.println("********** Test getClassName **********\n\n" + a.getClassName(employee));
		
				//Question 2 : getInstance
		Person p = (Person)a.getInstance(a.getClassName(person));
		System.out.println("\n********** Test getInstance **********\n\n new instance = " + p);
		
		
				// Question 3 : getProperties
		System.out.println("\n********** Test getProperties **********\n\r Liste des propriétés de la classe Person \n ");
		
		List<String> listOfProperties = new ArrayList<>();
		listOfProperties = a.getProperties(person);
		listOfProperties.forEach(t -> System.out.println(t));
		
		System.out.println("\r Liste des propriétés de la classe Employee \n ");
		
		listOfProperties = a.getProperties(employee);
		listOfProperties.forEach(t -> System.out.println(t));
		
				//Question 4
		System.out.println("\n********** Test get(Object bean, String property) **********\n\r les valeurs de propriétés de person \n");
		
		Field fieldLastName = a.get(person, "lastName");
		String s = (String) fieldLastName.get(person); 
		System.out.println("La valeur du champ " + fieldLastName.getName() + " de person est  : " + s);
		
		Field fieldFirstName = a.get(person, "firstName");
		String s1 = (String) fieldFirstName.get(person); 
		System.out.println("La valeur du champ " + fieldFirstName.getName() + " de person est  : " + s1);
		
		Field fieldAge = a.get(person, "age");
		int sAge = (int) fieldAge.get(person); 
		System.out.println("La valeur du champ " + fieldAge.getName() + " de person est  : " + sAge);
		
				
		System.out.println("\r les valeurs de propriétés d' employee  \n ");
		
		String firstName = (String) a.get2(employee, "firstName");
		String lastName = (String) a.get2(employee, "lastName");
		String age = Integer.toString((int) a.get2(employee, "age"));
		String salary = Integer.toString((int) a.get2(employee, "salary"));
		System.out.println("Employe : firstName = " + firstName + ", lastName = " + lastName + ", age = " + age + ", salaire = " + salary);
		
				//Question 5
		System.out.println("\n********** Test setProperty **********\n");
		
		Person person2 = new Person ();
		a.set(person2, "lastName", "Dhifli");
		a.set(person2, "firstName", "Hela");
		a.set(person2, "age", 23);
		System.out.println("p = " + person2);
		
		Employee em = new Employee ();
		a.set(em, "salary", 12000);
		a.set(em, "lastName", "Auguin");
		a.set(em, "firstName", "Christophe");
		a.set(em, "age", 45);
		System.out.println("em = " + em);
		
				//Question 6
		System.out.println("\n********** Test PersonReader **********\n");
		
		try (FileReader personFile = new FileReader ("files/FilePerson.txt");
			PersonReader pr = new PersonReader(personFile) ;) {
			
			List <Object> listPersonReader = pr.readPersonReader();
			listPersonReader.forEach(t -> System.out.println(t));
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
				//Question 7
		System.out.println("\n********** Test PersonWriter **********\n");
		
		List<Object> beans = List.of (
				new Person ("Eric", "Tabarly", 32),
				new Employee ("Christophe", "Auguin", 45, 12000));
		
		
		
		try (FileWriter personEmployee = new FileWriter ("files/personEmployee.txt");
			 PersonWriter pW = new PersonWriter(personEmployee) ;) {
				
			pW.write(beans);
				
		} catch (IOException e) {
				System.out.println(e);
			}
		
	}

}

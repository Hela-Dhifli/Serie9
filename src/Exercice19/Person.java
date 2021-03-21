package Exercice19;

import java.io.Serializable;

public class Person implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String lastName;
	private String firstName;
	private int age;
	
	public Person () {
	}

	

	
	public Person (String firstName, String lastName, int age) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	
	@Override
	public String toString() {
		return "Person : lastName = " + lastName + " firstName = " + firstName + " age = " + age ;
	}
	
	
	
}


package Exercice19;

public class Employee extends Person {

	
	
	private static final long serialVersionUID = 1L;

	private int salary;

	
	public Employee() {
		super();
	}
	
	public Employee(String firstName, String lastName , int age, int salary) {
		super(firstName,lastName, age);
		this.salary = salary ;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee : " + super.toString() + " salary = " + getSalary();
	}

	
	
	
	
	
}

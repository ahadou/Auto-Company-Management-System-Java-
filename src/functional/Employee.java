package functional;

//Child class employee inherits person
public class Employee extends Person
{
	//instance variables
	private int employeeID;
	private String employeeJob;
	private double employeeWeeklySalary;
	
	//overloaded constructors
	public Employee()
	{
		super();
		setEmployeeID(0);
		setEmployeeJob("-");
		setEmployeeSalary(0);
	}
	
	public Employee(String name, String address, String phoneNum, String DOB, int employeeID,
			String employeeJob, double employeeWeeklySalary)
	{
		super(name, address, phoneNum, DOB);
		setEmployeeID(employeeID);
		setEmployeeJob(employeeJob);
		setEmployeeSalary(employeeWeeklySalary);
	}
	
	//Methods
	public int getEmployeeID()
	{
		return this.employeeID;
	}
	
	public int incrementEmployeeID()
	{
		return ++employeeID;
	}
	
	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}
	
	public String getEmployeeJob()
	{
		return this.employeeJob;
	}
	
	public void setEmployeeJob(String employeeJob)
	{
		this.employeeJob = employeeJob;
	}
	
	public double getEmployeeSalary()
	{
		return this.employeeWeeklySalary;
	}
	
	public void setEmployeeSalary(double employeeWeeklySalary)
	{
		this.employeeWeeklySalary = employeeWeeklySalary;
	}
	
	public String toString() 
	{
		return (super.toString()+ "  EMPLOYEEID:" +getEmployeeID()+ "  EMPLOYEEJOB" +getEmployeeJob()+
				"  EMPLOYEESALARY:" +getEmployeeSalary());
	}
	
}//end Employee class

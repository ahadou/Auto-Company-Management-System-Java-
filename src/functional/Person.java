package functional;
//Class Person inherits abstract class People
public class Person extends People
{
	private String name;
	private String address;
	private String phoneNum;
	private String DOB;
	
	//Overloaded Constructors
	public Person()
	{
		setName("-");
		setAddress("-");
		setPhoneNumber("-");
		setDOB("-");
	}
	
	public Person(String name, String address, String phoneNum, String DOB)
	{
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNum);
		setDOB(DOB);
	}
	
	//Get and Set Methods
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getPhoneNumber()
	{
		return this.phoneNum;
	}
	
	public void setPhoneNumber(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	
	public void setDOB(String DOB)
	{
		this.DOB = DOB;
	}
	
	public String getDOB()
	{
		return this.DOB;
	}
	
	@Override
	public String toString() 
	{
		return ("\nNAME:" +getName()+ "  ADDRESS:" +getAddress()+ "  PHONE:" +getPhoneNumber()+
				"  DOB:" +getDOB());
	}
	
}//end Person class

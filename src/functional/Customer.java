package functional;

//Child class customer inherits person and nested insurance class
public class Customer extends Person 
{
	//instance variables
	private int customerID;
	private Insurance insure;
	
	//Overloaded constructors
	public Customer()
	{
		super();
		setCustomerID(0);
		insure = new Insurance();
	}
	
	public Customer(String name, String address, String phoneNum, String DOB, int customerID,
			String IC, int noClaimsBonus)
	{
		super(name, address, phoneNum, DOB);
		setCustomerID(customerID);
		insure = new Insurance(IC, noClaimsBonus);
	}
	
	//Methods
	public Customer(String name, String address, String phoneNum, String DOB, int customerID)
	{
		super(name, address, phoneNum, DOB);
		setCustomerID(customerID);
	}
	
	public int getCustomerID()
	{
		return customerID;
	}
	
	public void incrementCustomerID()
	{
	    ++this.customerID;
	}
	
	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}
	
	public void setInsurance(String IC, int noClaimsBonus)
	{
		insure.setInsurance(IC, noClaimsBonus);
	}
	
	public void setInsuranceCompany(String IC)
	{
		insure.setInsuranceCompany(IC);
	}
	
	public String getInsuranceCompany()
	{
		return insure.getInsuranceCompany();
	}
	
	public String toString() 
	{
		return (super.toString()+ "  CUSTOMERID:" +getCustomerID()+ " " +insure.toString());
	}
	
}//end Customer class


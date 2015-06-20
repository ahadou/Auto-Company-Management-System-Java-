package functional;

//transaction class inherits customer and therefore also inherits person
public class Transaction extends Customer
{
	//instance variables
	private int transID;
	private double price;
	private double VAT;
	private char sellOrBuy;
	private Car car;
	
	//overloaded constructors
	public Transaction()
	{
		super();
		car = new Car();
		this.price = 0;
		VAT = 0;
		this.sellOrBuy = '-';
	}
	
	public Transaction(String name, String address, String phoneNum, String DOB, int customerID, String IC, 
			int noClaimsBonus, String carMake, String carModel, String carRegistration, 
			String carColour, String carEngine, double price, double VAT, char sellOrBuy)
	{
		super(name, address, phoneNum, DOB, customerID, IC, noClaimsBonus);
		car = new Car(carMake, carModel, carRegistration, carColour, carEngine);
		this.price = price;
		this.VAT = VAT;
		this.sellOrBuy = sellOrBuy;
	}
	
	public double calcTotalCost(double price, double VAT)
	{
		double totVAT = (price / 100) * VAT;
		double totCost = price + totVAT;
		return totCost;
	}
	
	public void setTransID(int transID)
	{
		this.transID = transID;
	}
	
	public int getTransID()
	{
		return this.transID;
	}
	
	public void setVAT(double VAT)
	{
		this.VAT = VAT;
	}
	
	public double getVAT()
	{
		return this.VAT;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void setSellOrBuyOption(char sellOrBuy)
	{
		this.sellOrBuy = sellOrBuy;
	}
	
	public char getSellOrBuyOption()
	{
		return this.sellOrBuy;
	}
	
	public void setCarDetails(String carMake, String carModel, 
			String carRegistration, String carColour, String carEngine)
	{
		car.setCarDetails(carMake, carModel, carRegistration, carColour, carEngine);
	}
	
	public String toString() 
	{
		return (super.toString() +" "+ car.toString() + "\nPRICE:" +getPrice()+ "  VAT:" +getVAT()+
				"  BUY/SELL(B/S):" +getSellOrBuyOption());
	}
	
}//end Transaction class

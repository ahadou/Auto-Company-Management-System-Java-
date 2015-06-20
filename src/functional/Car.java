package functional;
public class Car 
{
	String carMake;
	String carModel;
	String carRegistration;
	String carColour;
	String carEngine;
	
	public Car()
	{
		setCarMake("-");
		setCarModel("-");
		setCarRegistration("-");
		setCarColour("-");
		setCarEngine("-");
	}
	
	public Car(String carMake, String carModel, String carRegistration, String carColour,
				String carEngine)
	{
		setCarMake(carMake);
		setCarModel(carModel);
		setCarRegistration(carRegistration);
		setCarColour(carColour);
		setCarEngine(carEngine);
	}
	
	public void setCarDetails(String carMake, String carModel, String carRegistration, 
			String carColour, String carEngine)
	{
		setCarMake(carMake);
		setCarModel(carModel);
		setCarRegistration(carRegistration);
		setCarColour(carColour);
		setCarEngine(carEngine);
	}
	
	public String getCarMake()
	{
		return this.carMake;
	}
	
	public void setCarMake(String carMake)
	{
		this.carMake = carMake;
	}
	
	public String getCarModel()
	{
		return this.carModel;
	}
	
	public void setCarModel(String carModel)
	{
		this.carModel = carModel;
	}
	
	public String getCarRegistration()
	{
		return this.carRegistration;
	}
	
	public void setCarRegistration(String carRegistration)
	{
		this.carRegistration = carRegistration;
	}
	
	public String getCarColour()
	{
		return this.carColour;
	}
	
	public void setCarColour(String carColour)
	{
		this.carColour = carColour;
	}
	
	public String getCarEngine()
	{
		return this.carEngine;
	}
	
	public void setCarEngine(String carEngine)
	{
		this.carEngine = carEngine;
	}
	
	public String toString() 
	{
		return (" MAKE:" +getCarMake()+ "  MODEL:" +getCarModel()+ "  REGISTRATION:" 
				+getCarRegistration()+ "  COLOUR:" +getCarColour()+ "  ENGINE:"
				+getCarEngine());
	}
	
}//end Car class

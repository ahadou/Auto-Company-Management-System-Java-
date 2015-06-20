package functional;
public class Planner
{
	//instance variables
	private int planID;
	private String date;
	private String time;
	private String description;
	
	//overloaded constructors
	public Planner()
	{
		this.planID= 0;
		this.date = "-";
		this.time = "-";
		this.description = "-";
	}
	
	public Planner(int planID, String date, String time, String description)
	{
		this.planID = planID;
		this.date = date;
		this.time = time;
		this.description = description;
	}
	
	//methods
	public int getPlanID()
	{
		return this.planID;
	}
	
	public void setPlanID(int planID)
	{
		this.planID = planID;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getTime()
	{
		return this.time;
	}
	
	public void setTime(String time)
	{
		this.time = time;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
}
	

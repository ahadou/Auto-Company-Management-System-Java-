package functional;
public class Insurance 
{
	//instance variables
	private String InsuranceCompany;
	private int noClaimsBonusYears;
	
	//overloaded constructors
	public Insurance()
	{
		InsuranceCompany = "-";
		noClaimsBonusYears = 0;
	}
	
	public Insurance(String IC, int noClaimsBonus)
	{
		setInsuranceCompany(IC);
		setNoClaimsBonus(noClaimsBonus);
	}
	
	//Methods
	public void setInsurance(String IC, int noClaimsBonus)
	{
		setInsuranceCompany(IC);
		setNoClaimsBonus(noClaimsBonus);
	}
	
	public void setInsuranceCompany(String IC)
	{
		this.InsuranceCompany = IC;
	}
	
	public String getInsuranceCompany()
	{
		return this.InsuranceCompany;
	}
	
	public void setNoClaimsBonus(int noClaimsBonus)
	{
		this.noClaimsBonusYears = noClaimsBonus;
	}
	
	public int getNoClaimsBonus()
	{
		return this.noClaimsBonusYears;
	}
	
	public String toString() 
	{
		return ("\nINSURANCECOMPANY:" +getInsuranceCompany()+ "  YEARSNCB(NoClaims):" +getNoClaimsBonus());
	}
	
}//end Insurance class

package com.Admin;

public class SessionBean 
{
	private String userName = "",companyName = "",Address = "",developerAddress = "",userType ="";
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getCompanyName()
	{
		return companyName;
	}
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public String getCompanyAddress()
	{
		return Address;
	}
	public void setCompanyAddress(String address)
	{
		this.Address = address;
	}
	public String getDeveloperAddress()
	{
		return developerAddress;
	}
	public void setDeveloperAddress(String develperAddress)
	{
		this.developerAddress = develperAddress;
	}
	public String getUserType()
	{
		return userType;
	}
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
}

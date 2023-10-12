package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojoClass;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest
{
	UserPojoClass pojobj1;
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String UserID,String UserName,String FirstName,String LastName,String Email,String Password,String Phone)
	{
		pojobj1=new UserPojoClass();
		pojobj1.setId(Integer.parseInt(UserID));
		pojobj1.setUsername(UserName);
		pojobj1.setFirstName(FirstName);
		pojobj1.setLastName(LastName);
		pojobj1.setEmail(Email);
		pojobj1.setPassword(Password);
		pojobj1.setPhone(Phone);
		
		Response response=UserEndPoints.createUser(pojobj1);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testGetUserByName(String UserName)
	{
	
		Response response=UserEndPoints.getUser(UserName);
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String UserName)
	{
	
		Response response=UserEndPoints.deleteUser(UserName);
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}

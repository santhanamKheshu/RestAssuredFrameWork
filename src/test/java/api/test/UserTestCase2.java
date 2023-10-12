package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.UserPojoClass;
import io.restassured.response.Response;

public class UserTestCase2 
{
		Faker faker;
		UserPojoClass pojobj;
		
		public Logger logger;
	 
	   @BeforeClass()
        public void setUpData()
        {
        	faker=new Faker() ;
        	pojobj=new UserPojoClass();
        	 
        	pojobj.setId(faker.idNumber().hashCode());
        	pojobj.setUsername(faker.name().username());
        	pojobj.setFirstName(faker.name().firstName());
        	pojobj.setLastName(faker.name().lastName());
        	pojobj.setEmail(faker.internet().safeEmailAddress());
        	pojobj.setPassword(faker.internet().password(5,10));
        	pojobj.setPhone(faker.phoneNumber().cellPhone());
        	
        	//logs
        	logger=LogManager.getLogger(this.getClass());
        	
        	logger.debug("debugging...........");
        }
	   
	   @Test(priority=1)
	   public void testPostUser()
	   {
		   logger.info("********************Creating User **************************");
		   Response response=UserEndPoints2.createUser(pojobj);
		   response.then().log().all();
		   
		   Assert.assertEquals(response.getStatusCode(), 200);
		   
		   logger.info("********************User is Created **************************");
	   }
	   
	   @Test(priority=2,dependsOnMethods= {"testPostUser"})
	   public void testGetUser()
	   {
		   logger.info("********************Reading User Info **************************");
		   Response response=UserEndPoints2.getUser(this.pojobj.getUsername());
		   response.then().log().all();
		   Assert.assertEquals(response.getStatusCode(), 200);
		   
		   logger.info("********************User info is displayed **************************");
	   }
	   
	   
	   @Test(priority=3,dependsOnMethods= {"testPostUser"})
	   public void testUpdateUser()
	   {
		   logger.info("********************Updating User **************************");	
		   pojobj.setFirstName(faker.name().firstName());
       		pojobj.setLastName(faker.name().lastName());
       		pojobj.setEmail(faker.internet().safeEmailAddress());
       		
       		Response response=UserEndPoints2.updateUser(this.pojobj.getUsername(), pojobj);
       		response.then().log().body();
       		
       		Assert.assertEquals(response.getStatusCode(), 200);
       		
       		Response responseAfterUpdate=UserEndPoints2.getUser(this.pojobj.getUsername());
       		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
       		
       		logger.info("********************User Updated **************************");
       }
	   
	   @Test(priority=4,dependsOnMethods= {"testPostUser"})
	   public void testDeleteUser()
	   {
		   logger.info("********************Deleting User **************************"); 
		   Response response=UserEndPoints2.deleteUser(this.pojobj.getUsername());
		    response.then().log().all();
		    
		    Assert.assertEquals(response.getStatusCode(), 200);
		    
		    logger.info("********************User Deleted **************************");
	   }
        
}

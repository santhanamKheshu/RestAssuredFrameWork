//Using properties file
package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.UserPojoClass;

//created for perform Create, Read, Update, Delete requests the user API
//CRUD methods implementation
public class UserEndPoints2 
{
    //method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle val=ResourceBundle.getBundle("CommonUrls"); //load properties file
	    return val;
	}
	
	
	public static Response createUser(UserPojoClass payload)
        {
        	
		   String post_url=getURL().getString("createuser_url");
		   
		   Response response=given()
        	.contentType(ContentType.JSON)
        	.accept(ContentType.JSON)
        	.body(payload)
        	
        	.when()
        	.post(post_url);
        	
        	return response;
        }
        
        
        public static Response getUser(String userName)
        {
        	String get_url=getURL().getString("getuser_url"); 
        	
        	Response response=given()
        			 .pathParam("username",userName)
        			 
        			 .when()
        			 .get(get_url);
        	 
        	 return response;
        }
        
        
        public static Response updateUser(String userName, UserPojoClass payload)
        {
        	String put_url=getURL().getString("updateuser_url"); 
        	
        	Response response=given()
        	.contentType(ContentType.JSON)
        	.accept(ContentType.JSON)
        	.pathParam("username", userName)
        	.body(payload)
        	
        	.when()
        	.put(put_url);
        	
        	return response;
        }
        
        public static Response deleteUser(String userName)
        {
        	String delete_url=getURL().getString("deleteuser_url"); 
        	Response response=given()
        	 .pathParam("username",userName)
        	 
        	 .when()
        	 .delete(delete_url);
        	 
        	 
        	 return response;
        	 
        }
}

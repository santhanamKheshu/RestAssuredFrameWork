package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import api.payload.UserPojoClass;

//created for perform Create, Read, Update, Delete requests the user API
//CRUD methods implementation
public class UserEndPoints 
{
        public static Response createUser(UserPojoClass payload)
        {
        	Response response=given()
        	.contentType(ContentType.JSON)
        	.accept(ContentType.JSON)
        	.body(payload)
        	
        	.when()
        	.post(Routes.createuser_url);
        	
        	return response;
        }
        
        
        public static Response getUser(String userName)
        {
        	 Response response=given()
        			 .pathParam("username",userName)
        			 
        			 .when()
        			 .get(Routes.getuser_url);
        	 
        	 return response;
        }
        
        
        public static Response updateUser(String userName, UserPojoClass payload)
        {
        	Response response=given()
        	.contentType(ContentType.JSON)
        	.accept(ContentType.JSON)
        	.pathParam("username", userName)
        	.body(payload)
        	
        	.when()
        	.put(Routes.updateuser_url);
        	
        	return response;
        }
        
        public static Response deleteUser(String userName)
        {
        	 Response response=given()
        	 .pathParam("username",userName)
        	 
        	 .when()
        	 .delete(Routes.deleteuser_url);
        	 
        	 
        	 return response;
        	 
        }
}

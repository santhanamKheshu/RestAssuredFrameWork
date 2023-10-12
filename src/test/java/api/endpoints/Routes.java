package api.endpoints;

//contain URL's
public class Routes
{
	public static String base_url="https://petstore.swagger.io/v2";
	
	//https://petstore.swagger.io/v2/user
	public static String createuser_url=base_url+"/user";
	public static String getuser_url=base_url+"/user/{username}";
	public static String updateuser_url=base_url+"/user/{username}";
	public static String deleteuser_url=base_url+"/user/{username}";
			
}

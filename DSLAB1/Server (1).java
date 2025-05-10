import java.rmi.*;
import java.rmi.Naming;

public class Server
{
	public static void main(String[] args)
	{
		try
		{
			Servant s=new Servant();
			Naming.rebind("Server",s);
			System.out.println("Server is Running!!");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

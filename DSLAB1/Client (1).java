import java.rmi.*;
import java.rmi.Naming;
import java.util.Scanner;

public class Client
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Server Address:");
			String server=sc.nextLine();
			ServerInterface si=(ServerInterface) Naming.lookup("rmi://"+server+"/Server");
			System.out.println("Enter First String:");
			String a=sc.nextLine();
			System.out.println("Enter Second String:");
			String b=sc.nextLine();
			String ans=si.concat(a,b);
			System.out.println("Concatinated String is:"+ans);
			sc.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

/*
mayuri@mayuri-VirtualBox:~/DSPractice/DS1$ javac *java
mayuri@mayuri-VirtualBox:~/DSPractice/DS1$ rmic Servant
Warning: generation and use of skeletons and static stubs for JRMP	
is deprecated. Skeletons are unnecessary, and static stubs have	
been superseded by dynamically generated stubs. Users are	
encouraged to migrate away from using rmic to generate skeletons and static	
stubs. See the documentation for java.rmi.server.UnicastRemoteObject.
mayuri@mayuri-VirtualBox:~/DSPractice/DS1$ java Server
Server is Running!!
mayuri@mayuri-VirtualBox:~/DSPractice/DS1$ java Client
Enter Server Address:
localhost
Enter First String:
Hello 
Enter Second String:
Mayuri
Concatinated String is:Hello Mayuri
*/


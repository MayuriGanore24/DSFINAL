import java.util.*;

public class main
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("No of Processes:");
        int n=sc.nextInt();
        System.out.println("Processes are\n");
        for(int i=1;i<=n;i++)
        {
            System.out.print(i+"\t");
        }
        System.out.println("\nEnter Sender:");
        int Sender=sc.nextInt();
        System.out.println("Enter Receiver:");
        int Receiver=sc.nextInt();
        sc.nextLine();
        System.out.println("\nEnter Message:");
        String msg=sc.nextLine();
        System.out.println("Message is "+msg);

        int i=(Sender%n)+1;
        while(true)
        {
            if(i==Receiver)
            {
                System.out.println("Message is delivered to "+Receiver);
                System.out.println("Message Received is "+msg);
                break;
            }
            i=(i%n)+1;
        }
        sc.close();
    }
}

/*
 
No of Processes:
6
Processes are

1	2	3	4	5	6	
Enter Sender:
1
Enter Receiver:
4

Enter Message:
Hello 
Message is Hello 
Message is delivered to 4
Message Received is Hello 

=== Code Execution Successful ===
 */
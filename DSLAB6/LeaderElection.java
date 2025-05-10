import java.util.*;

public class LeaderElection{
    public static void main(String [] args)
    {
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.println("\nEnter the No. of Processes:");
        n=sc.nextInt();
        int isActive[]=new int[n+1];
        Arrays.fill(isActive, 1);

        System.out.println("\nThe Process that is failed is: "+n);
        isActive[n]=0;
        int starter;
        System.out.println("\nEnter Process to start Election");
        starter=sc.nextInt();
        int newLeader=-1;
        for(int i=starter;i<=n;i++)
        {
            if(isActive[i]==1)
            {
                for(int j=i+1;j<=n;j++)
                {
                    if(isActive[j]==1)
                    {
                        System.out.println("\nThe process "+i+" sends election message to "+j);
                        System.out.println("\nThe Process "+j+" replies Ok to "+i);
                        newLeader=Math.max(newLeader,j);
                    }
                }
                if(newLeader<i)
                    newLeader=i;
            }
        }
    System.out.println("\nNew Leader is: "+newLeader);
    sc.close();
    }
}
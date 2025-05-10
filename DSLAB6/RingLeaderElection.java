import java.util.*;
public class RingLeaderElection
{
    public static void main(String[] argc)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("No of Processes:");
        int n=sc.nextInt();
        int isActive[]=new int[n+1];
        int oldLeader=n;
        Arrays.fill(isActive,1);
        isActive[oldLeader]=0;
        System.out.println("Process Failed is "+oldLeader);
        System.out.println("Start Ejection by: ");
        int starter=sc.nextInt();

        int idx=0;
        int msg[]=new int[n+1];
        int i=starter;
        do{
            msg[idx++]=i;
            int next=(i%n)+1;
            while(isActive[next]==0)
                next=(next%n)+1;
            printMessage(msg,idx);
            i=next;
        }
        while(i!=starter);
        int newLeader=0;
        for(int j=0;j<msg.length;j++)
        {
            if(newLeader<msg[j])
                newLeader=msg[j];
        }
        for(int p=0;p<n;p++)
        {
            if(isActive[p]==1)
                System.out.println("\nThe Process "+newLeader+" Sends co-ordinator msg to Process "+p);
        }
        System.out.println("\nNew Leader "+newLeader);
        sc.close();
    }
    static void printMessage(int []arr,int size)
    {
        System.out.print("[ ");
        for(int i=0;i<size;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.print(" ]\n");
    }
}
/*
 No of Processes:
7
Process Failed is 7
Start Ejection by: 
1
[ 1  ]
[ 1 2  ]
[ 1 2 3  ]
[ 1 2 3 4  ]
[ 1 2 3 4 5  ]
[ 1 2 3 4 5 6  ]

The Process 6 Sends co-ordinator msg to Process 0

The Process 6 Sends co-ordinator msg to Process 1

The Process 6 Sends co-ordinator msg to Process 2

The Process 6 Sends co-ordinator msg to Process 3

The Process 6 Sends co-ordinator msg to Process 4

The Process 6 Sends co-ordinator msg to Process 5

The Process 6 Sends co-ordinator msg to Process 6

New Leader 6

=== Code Execution Successful ===
 */
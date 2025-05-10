#include<stdio.h>
#include<mpi.h>
#define N 10
int main(int argc ,char *argv[])
{
	MPI_Init(&argc,&argv);
	int size,rank;
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	int sum=0;
	int a[]={1,2,3,4,5,6,7,8,9,10};
	int b[10];
	int chunk=N/size;
	int num_recv;
	if(rank==0)
	{
		for(int i=1;i<size;i++)
		{
			int start=i*chunk;
			int count=(i==size-1)?(N-start):chunk;
			MPI_Send(&count,1,MPI_INT,i,0,MPI_COMM_WORLD);
			MPI_Send(&a[start],count,MPI_INT,i,0,MPI_COMM_WORLD);
		}
		for(int i=0;i<chunk;i++)
		{
			sum+=a[i];
		}
		printf("\nMaster (Rank %d) Received Sum of %d",rank,sum);
		int temp=0;
		for(int i=1;i<size;i++)
		{
			MPI_Recv(&temp,1,MPI_INT,i,0,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
			sum+=temp;
			printf("\nMaster (Rank %d) Received Sum of %d",i,sum);
		}
		printf("\nTotal Sum: %d",sum);
	}
	else
	{
		MPI_Recv(&num_recv,1,MPI_INT,0,0,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
		MPI_Recv(&b,num_recv,MPI_INT,0,0,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
		int local_sum=0;
		for(int i=0;i<num_recv;i++)
		{
			local_sum+=b[i];
		}
		printf("\nMaster (Rank %d) Received Sum of %d",rank,local_sum);
		MPI_Send(&local_sum,1,MPI_INT,0,0,MPI_COMM_WORLD);
	}
	MPI_Finalize();
}
/*
Master (Rank 3) Received Sum of 34mayuri@mayuri-VirtualBox:~/DSPractice/DS3$ mpicc -o mpi_sum mpi_sum.c
mayuri@mayuri-VirtualBox:~/DSPractice/DS3$ mpirun -np 4 ./mpi_sum

Master (Rank 0) Received Sum of 3
Master (Rank 1) Received Sum of 10
Master (Rank 2) Received Sum of 21
Master (Rank 3) Received Sum of 55
Total Sum: 55
Master (Rank 1) Received Sum of 7
Master (Rank 2) Received Sum of 11
Master (Rank 3) Received Sum of 34
*/

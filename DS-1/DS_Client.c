#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	int flag=1, n, choice, *Y, result_2[2];
	float a,result_1, *result_3;
	int sockfd,portnum;
	struct sockaddr_in serverAddr;
	struct hostent *server;
	
	if (argc < 3) {
   		printf("usage %s hostname port\n", argv[0]);
    		exit(0);
  	}
	
	portnum = atoi(argv[2]);
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) {
		printf("[-]ERROR in connection.\n");
		exit(1);
	}
	
	server = gethostbyname(argv[1]);
	 if (server == NULL) {
    		printf("ERROR, no such host\n");
    		exit(0);
  	}
	printf("[+]Client Socket created successfully.\n");

	bzero((char *) &serverAddr, sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_port = htons(portnum);
	bcopy((char *)server->h_addr,(char *)&serverAddr.sin_addr.s_addr,server->h_length);
	// Establishing connection to the server.
	if (connect(sockfd, (struct sockaddr *)&serverAddr, sizeof(serverAddr)) < 0){
		printf("[-]ERROR in binding.\n");
		exit(1);
	}
	printf("[+]Connected to Server.\n");
	

	do {
    		//menu
		printf("\n\nEnter choice: \n1. Calculate average of Y.\n2. Calculate maximum and minimum of Y.\n3. Calculate a*Y[].\n4. Exit.\n");
	
		// We get rid of the rubbish.
		fflush(stdout);
		printf("\n\nPlease enter choice: ");
    		scanf("%d", &choice);
    		send(sockfd, &choice, sizeof(int), 0);
    		
    		if (choice == 1){
			printf("Give size of Y[]: ");
      			scanf("%d", &n);
      			send(sockfd, &n, sizeof(int), 0);
      			Y = (int *) malloc(n*sizeof(int));

     		 	for(int i=0;i<n;i++){
        			printf("Y[%d] = ", i+1);
        			scanf("%d", &Y[i]);

      			}
      			send(sockfd, Y, n*sizeof(int), 0);
      			recv(sockfd, &result_1, sizeof(float), 0);
      			printf("\n\nAverage of Y[]: %.2f\n\n\n\n",result_1);

    		}

    		else if (choice == 2){

      			printf("\n\n\nGive size of Y[]: ");
      			scanf("%d", &n);
      			send(sockfd, &n, sizeof(int), 0);
      
      			Y = (int *) malloc(n*sizeof(int));

      			for(int i=0;i<n;i++){
        			printf("Y[%d] = ", i+1);
        			scanf("%d", &Y[i]);

      			}
      			send(sockfd, Y, n*sizeof(int), 0);

      			recv(sockfd, result_2, 2*sizeof(int), 0);
      			printf("\n\nMax: %d\nMin: %d.\n\n", result_2[0], result_2[1]);

    		}
    		else if (choice == 3) {

      			printf("Give size of Y[]: ");
      			scanf("%d", &n);
      			send(sockfd, &n, sizeof(int), 0);

      			Y = (int *) malloc(n*sizeof(int));

      			for(int i=0; i<n; i++){
        			printf("Y[%d] = ", i+1);
        			scanf("%d", &Y[i]);

      			}
      		send(sockfd, Y, n*sizeof(int), 0);

      		printf("Give floating number a: ");
      		scanf("%f", &a);
      		send(sockfd, &a, sizeof(float), 0);
      
      		result_3 = (float *) malloc(n*sizeof(float));
      		recv(sockfd, result_3, n*sizeof(float), 0);
      		printf("\n\n");

      		for(int i=0; i<n; i++){
        		printf("a*Y[%d] = %.2f\n\n\n\n", i+1, result_3[i]);
        	}
    }
    else if (choice == 4){
      flag = 0;
	}
    else{
      printf("Wrong input, please try again\n");
    	}


  } while (flag);
  close(sockfd);

  
return 0;
	
}

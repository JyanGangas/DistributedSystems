/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "DSFunc.h"

float *
average_1_svc(Y_arr *argp, struct svc_req *rqstp)
{
	static float  result;

	printf("Function has been called to calculate the AVG.\n");
	// Calculating the avg
	float sum=0;
	// With '->' we assign the value to 'Y_size'.
	for(int i=0; i<argp->Y_size;i++){
		sum+=argp->Y.Y_val[i];
	}
	result = sum/argp->Y_size;

	return &result;
}

max_min *
maxmin_1_svc(Y_arr *argp, struct svc_req *rqstp)
{
	static max_min  result;

	printf("Function has been called to calculate the MAx and Min.\n");
	// Values to compare the array elements to get the maximum and minimum
	result.max=-999999;
	result.min=999999;
	// Using for to calculate max and min.
	for(int i=0;i<argp->Y_size;-i++){
		if(argp->Y.Y_val[i]>result.max){
			result.max=argp->Y.Y_val[i];
		}
	}
	for(int i=0;i<argp->Y_size;i++){
		if(argp->Y.Y_val[i]<result.min){
			result.min=argp->Y.Y_val[i];
		}
	}
	return &result;
}

aY *
product_1_svc(a_mul_Y *argp, struct svc_req *rqstp)
{
	static aY  result;

	printf("Function has been called to calculate the a*Y[].\n");
	
	result.prod.prod_len=argp->Y_size;
	result.prod.prod_val=(float *)malloc(argp->Y_size*sizeof(float));
	
	for(int i=0;i<argp->Y_size;i++){
		result.prod.prod_val[i]=argp->a*argp->Y.Y_val[i];	
	}

	return &result;
}

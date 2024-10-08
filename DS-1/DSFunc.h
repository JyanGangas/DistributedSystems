/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _DSFUNC_H_RPCGEN
#define _DSFUNC_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct Y_arr {
	struct {
		u_int Y_len;
		int *Y_val;
	} Y;
	int Y_size;
};
typedef struct Y_arr Y_arr;

struct max_min {
	int max;
	int min;
};
typedef struct max_min max_min;

struct a_mul_Y {
	struct {
		u_int Y_len;
		int *Y_val;
	} Y;
	int Y_size;
	float a;
};
typedef struct a_mul_Y a_mul_Y;

struct aY {
	struct {
		u_int prod_len;
		float *prod_val;
	} prod;
};
typedef struct aY aY;

#define CONCURRENT_SER_PROG 0x12345601
#define CONCURRENT_SER_VER 1

#if defined(__STDC__) || defined(__cplusplus)
#define average 1
extern  float * average_1(Y_arr *, CLIENT *);
extern  float * average_1_svc(Y_arr *, struct svc_req *);
#define maxmin 2
extern  max_min * maxmin_1(Y_arr *, CLIENT *);
extern  max_min * maxmin_1_svc(Y_arr *, struct svc_req *);
#define product 3
extern  aY * product_1(a_mul_Y *, CLIENT *);
extern  aY * product_1_svc(a_mul_Y *, struct svc_req *);
extern int concurrent_ser_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define average 1
extern  float * average_1();
extern  float * average_1_svc();
#define maxmin 2
extern  max_min * maxmin_1();
extern  max_min * maxmin_1_svc();
#define product 3
extern  aY * product_1();
extern  aY * product_1_svc();
extern int concurrent_ser_prog_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_Y_arr (XDR *, Y_arr*);
extern  bool_t xdr_max_min (XDR *, max_min*);
extern  bool_t xdr_a_mul_Y (XDR *, a_mul_Y*);
extern  bool_t xdr_aY (XDR *, aY*);

#else /* K&R C */
extern bool_t xdr_Y_arr ();
extern bool_t xdr_max_min ();
extern bool_t xdr_a_mul_Y ();
extern bool_t xdr_aY ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_DSFUNC_H_RPCGEN */

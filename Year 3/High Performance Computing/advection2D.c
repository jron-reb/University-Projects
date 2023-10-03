/*******************************************************************************
2D advection example program which advects a Gaussian u(x,y) at a fixed velocity



Outputs: initial.dat - inital values of u(x,y) 
         final.dat   - final values of u(x,y)

         The output files have three columns: x, y, u

         Compile with: gcc -o advection2D -std=c99 advection2D.c -lm

Notes: The time step is calculated using the CFL condition

********************************************************************************/

/*********************************************************************
                     Include header files 
**********************************************************************/

#include <stdio.h>
#include <math.h>

/*********************************************************************
                      Main function
**********************************************************************/

int main(){

  /* Grid properties */
  const int NX=1000;    // Number of x points
  const int NY=1000;    // Number of y points
  const float xmin=0.0; // Minimum x value
  const float xmax=30; // Maximum x value
  const float ymin=0.0; // Minimum y value
  const float ymax=30; // Maximum y value
  
  /* Parameters for the Gaussian initial conditions */
  const float x0=3.0;                    // Centre(x)
  const float y0=15.0;                    // Centre(y)
  const float sigmax=1.0;               // Width(x)
  const float sigmay=5.0;               // Width(y)
  const float sigmax2 = sigmax * sigmax; // Width(x) squared
  const float sigmay2 = sigmay * sigmay; // Width(y) squared

  /* Boundary conditions */
  const float bval_left=0.0;    // Left boudnary value
  const float bval_right=0.0;   // Right boundary value
  const float bval_lower=0.0;   // Lower boundary
  const float bval_upper=0.0;   // Upper bounary
  
  /* Time stepping parameters */
  const float CFL=0.55;   // CFL number 
  const int nsteps=800; // Number of time steps

  /* Velocity */
  const float velx=1.00; // Velocity in x direction
  const float vely=0.00; // Velocity in y direction
 
  /* Arrays to store variables. These have NX+2 elements
     to allow boundary values to be stored at both ends */
  float x[NX+2];          // x-axis values
  float y[NX+2];          // y-axis values
  float u[NX+2][NY+2];    // Array of u values
  float dudt[NX+2][NY+2]; // Rate of change of u

  float x2;   // x squared (used to calculate iniital conditions)
  float y2;   // y squared (used to calculate iniital conditions)
  
  /* Calculate distance between points */
  float dx = (xmax-xmin) / ( (float) NX);
  float dy = (ymax-ymin) / ( (float) NY);
  
  /* Calculate time step using the CFL condition */
  /* The fabs function gives the absolute value in case the velocity is -ve */
  float dt = CFL / ( (fabs(velx) / dx) + (fabs(vely) / dy) );
  
  /*** Report information about the calculation ***/
  printf("Grid spacing dx     = %g\n", dx);
  printf("Grid spacing dy     = %g\n", dy);
  printf("CFL number          = %g\n", CFL);
  printf("Time step           = %g\n", dt);
  printf("No. of time steps   = %d\n", nsteps);
  printf("End time            = %g\n", dt*(float) nsteps);
  printf("Distance advected x = %g\n", velx*dt*(float) nsteps);
  printf("Distance advected y = %g\n", vely*dt*(float) nsteps);

  /*** Place x points in the middle of the cell ***/
  /* LOOP 1 */

  #pragma omp parallel for default(shared)
  for (int i=0; i<NX+2; i++){
   x[i] = ( (float) i - 0.5) * dx;
  }

  /*** Place y points in the middle of the cell ***/
  /* LOOP 2 */

  #pragma omp parallel for default(shared) 
  for (int j=0; j<NY+2; j++){
    y[j] = ( (float) j - 0.5) * dy;
  }

  /*** Set up Gaussian initial conditions ***/
  /* LOOP 3 */

  #pragma omp parallel for collapse(2) private(x2, y2)
  for (int i=0; i<NX+2; i++){
    for (int j=0; j<NY+2; j++){
      x2      = (x[i]-x0) * (x[i]-x0);
      y2      = (y[j]-y0) * (y[j]-y0);
      u[i][j] = exp( -1.0 * ( (x2/(2.0*sigmax2)) + (y2/(2.0*sigmay2)) ) );
    }
  }

  /*** Write array of initial u values out to file ***/
  FILE *initialfile;
  initialfile = fopen("initial.dat", "w");
  /* LOOP 4 */
 
  /* Loop 4 cannot be parallelised because the writes need to write to the file in order, to ensure that the initial.dat is the exact same one produced by the serialisable program. Therefore the loop iterations must take place in order and the loop cannot be parallelised since doing so would mean the order of the writes is different each time.*/

  for (int i=0; i<NX+2; i++){
    for (int j=0; j<NY+2; j++){
      fprintf(initialfile, "%g %g %g\n", x[i], y[j], u[i][j]);
    }
  }
  fclose(initialfile);
  
  /*** Update solution by looping over time steps ***/
  /* LOOP 5 */

  /* Loop 5 cannot be parallelised and this is because to calculate the values for each time step the values of the previous time step must first be calculated. Therefore the time steps must be calculated in order. This means the loop cannot be parallelised as the time steps would no longer be calculated in order if it were parallelised.*/

  for (int m=0; m<nsteps; m++){
    
    /*** Apply boundary conditions at u[0][:] and u[NX+1][:] ***/
    /* LOOP 6 */

    #pragma omp parallel for default(shared)
    for (int j=0; j<NY+2; j++){
      u[0][j]    = bval_left;
      u[NX+1][j] = bval_right;
    }
    /*** Apply boundary conditions at u[:][0] and u[:][NY+1] ***/
    /* LOOP 7 */

    #pragma omp parallel for default(shared)
    for (int i=0; i<NX+2; i++){
      u[i][0]    = bval_lower;
      u[i][NY+1] = bval_upper;
    }
    
    /*** Calculate rate of change of u using leftward difference ***/
    /* Loop over points in the domain but not boundary values */
    /* LOOP 8 */
    float velxlog;

    #pragma omp parallel for collapse(2) private(velxlog)
    for (int i=1; i<NX+1; i++){
      for (int j=1; j<NY+1; j++){
	if (j * dy > 1.0) {
		velxlog = (0.2 / 0.41) * log(j * dy / 1.0);
		 dudt[i][j] = -velxlog * (u[i][j] - u[i-1][j]) / dx
                    - vely * (u[i][j] - u[i][j-1]) / dy;			
	} else {
		dudt[i][j] = - velx * (u[i][j] - u[i-1][j]) / dx
                    - vely * (u[i][j] - u[i][j-1]) / dy;
	}
      }
    }
    
    /*** Update u from t to t+dt ***/
    /* Loop over points in the domain but not boundary values */
    /* LOOP 9 */

    #pragma omp parallel for collapse(2)
    for	(int i=1; i<NX+1; i++){
      for (int j=1; j<NY+1; j++){
	u[i][j] = u[i][j] + dudt[i][j] * dt;
      }
    }
    
  } // time loop
  
  /*** Write array of final u values out to file ***/
  FILE *finalfile;
  finalfile = fopen("final.dat", "w");
  /* LOOP 10 */
  /* Loop 10 cannot be parallelised because the writes need to write to the file in order to ensure that the final.dat file is the exact same as the one produced by the serialisable program. Therefore the loop iterations must take place in order and the loop cannot be parallelised since doing so would mean the order of the writes is different each time. */


 for (int i=0; i<NX+2; i++){
    for (int j=0; j<NY+2; j++){
      fprintf(finalfile, "%g %g %g\n", x[i], y[j], u[i][j]);
    }
  }
  fclose(finalfile);

  /* This section calculates the vertically averaged distribution of u(x,y). It does not include the boundary values (doesn't include the first and last x and y values) */
  FILE *avgfile;
  avgfile = fopen("avg.dat", "w");
  int counter;
  float sum;
  float avg;
  /* The two loops ignore the index 0 and the last value(s) of x and y (i or j) because this is where all the boundary values are stored*/
  for (int i=1; i < NY+1; i ++) {
    counter = 0;
    sum = 0;
    for (int j = 1; j < NX+1; j++) {
      counter++;
      sum += u[i][j];
    }
    avg = sum/counter;
    fprintf(avgfile, "%g %g\n", x[i], avg);
  }

  return 0;
}

/* End of file ******************************************************/

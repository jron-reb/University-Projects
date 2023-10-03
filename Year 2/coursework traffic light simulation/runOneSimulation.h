#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <errno.h>
#include <gsl/gsl_rng.h>
#include <gsl/gsl_randist.h>


struct Car {//Holds information about the car and serve as nodes in the queue waiting behind each traffic light
    int arrivalTime;
    struct Car *nextCar;
};
typedef struct Car CAR;

struct Queue { //holds information about the queue of cars that allows the cars to quickly be searched
    CAR* head;
    CAR* tail;
    int size;
    //access size using queuename->size
};
typedef struct Queue QUEUE;

struct greenLight{
    int side; //0 signifies left and 1 signifies right
    int timeLeft;
    int timeRight;
};
typedef struct greenLight GREENLIGHT;

struct results {
    //need for LHS and RHS
   int numberOfVehiclesLeft;
   int totalWaitingTimeLeft; //isn't unsigned because could be waiting for longer than there are iterations
   //still have to decide if want to add averageWaitingTime now or later
   int maximumWaitingTimeLeft;
   int queueClearingTimeLeft; //since vehicles stop arriving
   //could be useful to store traffic arrival rate and traffic light period
   int numberOfVehiclesRight;
    int totalWaitingTimeRight;
    int maximumWaitingTimeRight;
    int queueClearingTimeRight;
};
typedef struct results RESULTS;

static QUEUE *newQueue();
static void enqueue(int, QUEUE*);
static void dequeue(QUEUE* );
extern void runOneSimulation(int, int, double, double, RESULTS*, gsl_rng* );
static CAR* newCar(int);
static GREENLIGHT* newLight(int, int);
static int checkTrafficLights(GREENLIGHT*, int);
extern RESULTS* newResults();
static void updateMaxAndTotalWT(RESULTS*, QUEUE*, GREENLIGHT*, int, int);





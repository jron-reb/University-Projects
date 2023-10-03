#include <runOneSimulation.h>

struct parameters {
    int trafficLightTimeLeft;
    int trafficLightTimeRight;
    double rateOfArrivalLeft;
    double rateOfArrivalRight;
};
typedef struct parameters PARAMETERS;

static void runSimulations(PARAMETERS*);
static void updateResults(RESULTS*, RESULTS*);
static void resetResults(RESULTS*);
static void averageResults(RESULTS*, int);
static void outputResults(PARAMETERS*, RESULTS*);
static PARAMETERS* newParameters();
static void takeParameters(PARAMETERS*);
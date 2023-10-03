#include <runSimulations.h>

int main () {
    PARAMETERS *parameters = newParameters();
    takeParameters(parameters);

    runSimulations(parameters);

    free(parameters);//freeing allocated memory
    return EXIT_SUCCESS;
}

static void runSimulations(PARAMETERS* parameters) {
    RESULTS *sumOfResults = newResults();
    RESULTS *results = newResults(); //only need to allocate memory for 1 results

    //setup environment for the random number generator
    //seed it here because if with time(0) in each individual simulation it can sometimes seed at the exact same value creating an identical simulations and results
    const gsl_rng_type *generatorType;
    gsl_rng *rng_state;
    gsl_rng_env_setup();
    generatorType = gsl_rng_default;
    rng_state = gsl_rng_alloc(generatorType);

    gsl_rng_set(rng_state, time(0));
    int i;
    for ( i = 0; i<100; i++) {//runs 100 single simulations
        runOneSimulation(parameters->trafficLightTimeLeft, parameters->trafficLightTimeRight, parameters->rateOfArrivalLeft, parameters->rateOfArrivalRight, results, rng_state);
        updateResults(sumOfResults, results);//updates the final results with the individual result of each simulation
        resetResults(results);//resets the results to their initial state for the next individual simulation
    }
    averageResults(sumOfResults, i);//averages the final results
    outputResults(parameters, sumOfResults);//outputs the results to the user
    //freeing allocated memory
    gsl_rng_free(rng_state);
    free(results);
    free(sumOfResults);
}

static PARAMETERS* newParameters(){//Creates a new parameters structure
    PARAMETERS* parameters;
    if (! (parameters = (PARAMETERS *)malloc(sizeof(PARAMETERS)))) {
        printf("Insufficient memory to allocate a parameters object\n");
        fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
        exit(1);
    }
    parameters->trafficLightTimeLeft = 0;
    parameters->trafficLightTimeRight = 0;
    parameters->rateOfArrivalLeft = 0;
    parameters->rateOfArrivalRight = 0;
    return parameters;
}

static void takeParameters(PARAMETERS* parameters) {//Takes in the parameters from the command line during run time
        printf("Please input an integer representing the length of time the left light stays green.\nPlease input an integer the length of time the right light stays green.\nPlease input a double the rate of arrival of vehicles from the left in the range 0-1.\nPlease input a double the rate of arrival of vehicles from the left in the range 0-1.\nIn the format X X X X\n");
        
        if (scanf("%d %d %lf %lf", &parameters->trafficLightTimeLeft, &parameters->trafficLightTimeRight, &parameters->rateOfArrivalLeft, &parameters->rateOfArrivalRight) != 4)
        { //if 4 parameters are not returned then returns an error
            printf("Wrong format of user input please input\n");
            fprintf(stderr, "error: The user did not input parameters in the correct format or of the correct type\n");
            exit(1);
        }
        if ( 0> parameters->trafficLightTimeLeft || 0 > parameters->trafficLightTimeRight || 0> parameters->rateOfArrivalLeft || parameters->rateOfArrivalLeft > 1 || 0> parameters->rateOfArrivalRight || parameters->rateOfArrivalRight> 1) { //checks that the values input are valid, will repeat until they are
            takeParameters(parameters);
        }
    }

static void updateResults(RESULTS* sumOfResults, RESULTS* results) {//updates the total results, called after each individual simulation
        sumOfResults->maximumWaitingTimeLeft += results->maximumWaitingTimeLeft;
        sumOfResults->maximumWaitingTimeRight += results->maximumWaitingTimeRight;
        sumOfResults->numberOfVehiclesLeft += results->numberOfVehiclesLeft;
        sumOfResults->numberOfVehiclesRight += results->numberOfVehiclesRight;
        sumOfResults->totalWaitingTimeLeft += results->totalWaitingTimeLeft/results->numberOfVehiclesLeft;//is called totalwaitingtimeright but represents average waiting time in the final results
        sumOfResults->totalWaitingTimeRight += results->totalWaitingTimeRight/results->numberOfVehiclesRight;//is called totalwaitingtimeleft but represents average waiting time in the final results
        sumOfResults->queueClearingTimeLeft += results->queueClearingTimeLeft;
        sumOfResults->queueClearingTimeRight += results->queueClearingTimeRight;
}

static void resetResults(RESULTS* results) {// resets the results to their intial state
    results->numberOfVehiclesLeft = 0;
    results->totalWaitingTimeLeft = 0;
    results->maximumWaitingTimeLeft = 0;
    results->queueClearingTimeLeft = 0;
    results->numberOfVehiclesRight = 0;
    results->totalWaitingTimeRight= 0;
    results->maximumWaitingTimeRight = 0;
    results->queueClearingTimeRight = 0;
}

static void averageResults(RESULTS* sumOfResults, int numberOfIterations){//doesn't matter that they are ints because the division by 100 will only lead to ints and no decimal points
    sumOfResults->maximumWaitingTimeLeft = sumOfResults->maximumWaitingTimeLeft/numberOfIterations;  
    sumOfResults->maximumWaitingTimeRight = sumOfResults->maximumWaitingTimeRight/numberOfIterations;
    sumOfResults->numberOfVehiclesLeft = sumOfResults->numberOfVehiclesLeft/numberOfIterations;  
    sumOfResults->numberOfVehiclesRight = sumOfResults->numberOfVehiclesRight/numberOfIterations; 
    sumOfResults->totalWaitingTimeLeft = sumOfResults->totalWaitingTimeLeft/numberOfIterations;//is called totalwaitingtimeleft but represents average waiting time in the final results
    sumOfResults->totalWaitingTimeRight = sumOfResults->totalWaitingTimeRight/numberOfIterations;//is called totalwaitingtimeright but represents average waiting time in the final results
    sumOfResults->queueClearingTimeLeft = sumOfResults->queueClearingTimeLeft/numberOfIterations; 
    sumOfResults->queueClearingTimeRight = sumOfResults->queueClearingTimeRight/numberOfIterations; 
}

static void outputResults(PARAMETERS* parameters, RESULTS* sumOfResults) {//output the results to a file using the stdout stream
    FILE *outputFile;
    if ( (outputFile = fopen("results.txt", "a")) == NULL) {//appending to the file to store multiple results
        printf("Cannot open output file with name results.txt\n");
        fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
        exit(1);
    }
    fprintf(outputFile, "Parameter values: \n\tfrom left:\n\t\ttraffic arrival rate: %lf\n\t\ttraffic light period: %d\n\tfrom right:\n\t\ttraffic arrival rate: %lf\n\t\ttraffic light period: %d\n", parameters->rateOfArrivalLeft, parameters->trafficLightTimeLeft, parameters->rateOfArrivalRight, parameters->trafficLightTimeRight);

    fprintf(outputFile, "Results (averaged over 100 runs):\n\tfrom left:\n\t\tnumber of vehicles: %d\n\t\taverage waiting time: %d\n\t\tmaximum waiting time: %d\n\t\tclearance time: %d\n\tfrom right:\n\t\tnumber of vehicles: %d\n\t\taverage waiting time: %d\n\t\tmaximum waiting time: %d\n\t\tclearance time: %d\n", sumOfResults->numberOfVehiclesLeft, sumOfResults->totalWaitingTimeLeft, sumOfResults->maximumWaitingTimeLeft, sumOfResults->queueClearingTimeLeft, sumOfResults->numberOfVehiclesRight, sumOfResults->totalWaitingTimeRight, sumOfResults->maximumWaitingTimeRight, sumOfResults->queueClearingTimeRight);
    
    fclose(outputFile);
}

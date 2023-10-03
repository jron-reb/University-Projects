#include <runOneSimulation.h>

static QUEUE* newQueue(){// creates a new queue structure
    QUEUE *queue;
    if (! (queue = (QUEUE*)malloc(sizeof(QUEUE)))){
        printf("Insufficient memory to allocate a queue\n");
        fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
        exit(1);
    }
    queue->head = queue->tail = NULL;
    queue->size = 0;
    return queue;
}

static void enqueue(int arrivalTime, QUEUE *queue) { //adds a new node to the queue, represents a car arriving at the traffic lights
    
    // Create a new car that is entering the queue
    CAR *tempCar = newCar(arrivalTime);
 
    // If queue is empty, then new car is the tail and the head
    if (queue->tail == NULL) {
    queue->head = tempCar;
    } else {// Otherwise the new car is the tail and the previous tail's nextCar becomes the newcar
        queue->tail->nextCar = tempCar;
    }
    queue->tail = tempCar;
    queue->size++;
}

static void dequeue(QUEUE* queue) { //removes a node from the queue, represents a car driving through the protected section

    if (queue->size == 0) {//if the queue is empty then an error has occured and the program should be exited
        printf("queue is already empty");
        exit(-1);
    }

    CAR *tempCar = queue->head; //store the previous front node

    if (queue->size == 1) {//if the queue has 1 car in it make the head and tail null
        queue->head = queue->tail = NULL;
    } else {//otherwise make the queue head the second car in line
        queue->head = queue->head->nextCar;
    }
    free(tempCar); //this frees up the allocated memory that the car that has been removed from the queue took up
    queue->size--;    
}

extern void runOneSimulation(int timeLeftLight, int timeRightLight, double rateLeftLight, double rateRightLight, RESULTS* results, gsl_rng* rng_state) {
    QUEUE *rightqueue = newQueue();
    QUEUE *leftqueue = newQueue();
    GREENLIGHT *greenLight = newLight(timeLeftLight, timeRightLight);
    int i;
    int trafficLightCounter = 0;
    /* in each iteration
    EITHER
    the traffic lights change colour
    a check should happen whether the traffic lights need to change colour or not

    OR EACH OF THE FOLLOWING HAPPENS in the following order
    0-1 vehicle arrives from the left and joins the LHQ
    0-1 vehicle arrives from the right and joins the RHQ
    0-1 vehicle passes through the protected road section between the 2 sets of traffic lights, either L->R or R->L
        depends on which set of lights are green
    */
    
    for (i = 1; i<=500; i++) {//for 500 iterations
        trafficLightCounter = checkTrafficLights(greenLight, trafficLightCounter); //check if traffic lights need to change colour
        if (trafficLightCounter != 0) {// this means that the traffic lights didnt change colour and the other 3 steps should happen
        //gsl_ran_flat is used to generate a random number between 0 and 1, to determine whether a vehicle should enter the queue
            if (gsl_ran_flat(rng_state,0,1) <= rateLeftLight && rateLeftLight != 0) { //checks if a vehicle joins the left queue
                enqueue(i, leftqueue);
                results->numberOfVehiclesLeft++;
            }
            if (gsl_ran_flat(rng_state,0,1) <= rateRightLight && rateRightLight != 0) { //checks if vehicle joins the right queue
                enqueue(i, rightqueue);
                results->numberOfVehiclesRight++;
            }
            if (greenLight->side == 0 && leftqueue->size != 0) { // Left light is green and queue isn't empty then remove a car from the queue
                updateMaxAndTotalWT(results, leftqueue, greenLight, i, 0);//Update the results with the arrivaltime of the car, from which the waiting time of the car can be deduced
                dequeue(leftqueue);
            }
            else if (greenLight->side == 1 && rightqueue->size != 0) { // Right light is green and queue isnt empty then remove a car from the queue
                updateMaxAndTotalWT(results, rightqueue, greenLight, i, 0);
                dequeue(rightqueue);
            }
        }
    }
    int leftqueueCounter = 0;
    int rightqueueCounter = 0;
    int clearingTimeCounter = 0; //so that can keep track of how many time periods have passed since vehicles stopped arriving
    while (leftqueue->head != NULL|| rightqueue->head != NULL) { //until the queues clear keep the traffic lights changing and allow cars to leave but no additional cars arrive
        if (leftqueue->head != NULL) {//if the leftqueue is not empty then add a timestep to the amount of time it took to clear, this is necessary because the rightqueue may be full and the left queue may already be empty
            leftqueueCounter++;
        }
        if (rightqueue->head != NULL) {//if the rightqueue is not empty then add a timestep to the amount of time it took to clear, this is necessary because the leftqueue may be full and the left queue may already be empty
            rightqueueCounter++;
        }
        //cars are removed by the same process as in the above code within the for loop
        trafficLightCounter = checkTrafficLights(greenLight, trafficLightCounter);
        if (trafficLightCounter != 0) {
            if (greenLight->side == 0 && leftqueue->size != 0) {
                updateMaxAndTotalWT(results,leftqueue, greenLight, i, clearingTimeCounter);
                dequeue(leftqueue);
            }
            else if (greenLight->side == 1 && rightqueue->size != 0) {
                updateMaxAndTotalWT(results,rightqueue, greenLight, i, clearingTimeCounter);
                dequeue(rightqueue);
            }
        }
        clearingTimeCounter++;
    }
    //update the results with the time it took for each queue to clear
    results->queueClearingTimeLeft = leftqueueCounter;
    results->queueClearingTimeRight = rightqueueCounter;
    //free allocated memory
    free(rightqueue);
    free(leftqueue);
    free(greenLight);
}

static CAR* newCar(int arrivalTime) {//create a new car structure
    CAR* tempCar;
    if (! (tempCar = (CAR*)malloc(sizeof(CAR)))){
        printf("Insufficient memory to allocate a car\n");
        fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
        exit(1);
    }
    tempCar->nextCar = NULL;
    tempCar->arrivalTime = arrivalTime;
    return tempCar;
}


static GREENLIGHT* newLight(int timeLeftLight, int timeRightLight) {// create a new greenlight structure
    GREENLIGHT *greenLight;
    if (! (greenLight = (GREENLIGHT*)malloc(sizeof(GREENLIGHT)))) {
        printf("Insufficient memory to allocate the green light\n");
        fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
        exit(1);
    }
    greenLight->side = 0;
    greenLight->timeLeft = timeLeftLight;
    greenLight->timeRight = timeRightLight;
    return greenLight;
}

static int checkTrafficLights(GREENLIGHT *greenLight, int trafficLightCounter) {//check if the traffic lights need to change colour, if they do not then increment the time since they have last changed by 1
    if (greenLight->side == 0 && trafficLightCounter == greenLight->timeLeft) {
            trafficLightCounter = 0;
            greenLight->side++;
        } else if (greenLight->side == 1 && trafficLightCounter == greenLight->timeRight){
            trafficLightCounter = 0;
            greenLight->side--;
        } else {
            trafficLightCounter++; //only increase the counter if the lights did not change, while the lights where changing they were not green and could not let cars through
        }
    return trafficLightCounter;
}

extern RESULTS* newResults(){// create a new results structure
    RESULTS *results;
    if (! (results = (RESULTS*)malloc(sizeof(RESULTS)))) {
        printf("Insufficient memory to allocate a results object\n");
        fprintf(stderr, "error %d: %s\n", errno, strerror(errno));
        exit(1);
    }
    results->numberOfVehiclesLeft = 0;
    results->totalWaitingTimeLeft = 0;
    results->maximumWaitingTimeLeft = 0;
    results->queueClearingTimeLeft = 0;
    results->numberOfVehiclesRight = 0;
    results->totalWaitingTimeRight= 0;
    results->maximumWaitingTimeRight = 0;
    results->queueClearingTimeRight = 0;
    return results;
}

static void updateMaxAndTotalWT(RESULTS *results, QUEUE *queue, GREENLIGHT* greenLight, int iterationNumber, int clearingTimeCounter) {//derive the waiting time from the arrival time of a car and the iteration of the simulation
//update the results total waiting time and if it is a new max waiting time then update this as well
    int waitingtime = iterationNumber - queue->head->arrivalTime + clearingTimeCounter;
    if (greenLight->side == 0) {
        results->totalWaitingTimeLeft += waitingtime;
        if (results->maximumWaitingTimeLeft < waitingtime)
        {
            results->maximumWaitingTimeLeft = waitingtime;
        }
    } else {
        results->totalWaitingTimeRight += waitingtime;
        if (results->maximumWaitingTimeRight < waitingtime)
        {
            results->maximumWaitingTimeRight = waitingtime;
        }
    }
}


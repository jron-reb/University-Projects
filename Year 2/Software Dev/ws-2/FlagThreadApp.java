public class FlagThreadApp
{
	public static void main (String args[]) {
		FlagExample a = new FlagExample();
		FlagExample b = new FlagExample();
		a.start();//thread a starts and calls the run method
	 	b.start();//thread b starts and calls the run method
		System.out.println("Main thread is still running");

		/* note: below we are using the static (class level) sleep method
		* as we do not have an instance to refer to to cause the main thread
 		* to sleep, instead Thread.sleep() causes the currently executing
 		* thread to sleep which here is the main method as we want.
 		*/
 		try {
 			Thread.sleep(1000); // sleep for 1000 milliseconds
			 //will cause both child thread a and child thread b to stop running for 1000 milliseconds
 		} catch (InterruptedException e){
 			// as before, we are not going to respond to an interruption
 		}
 		a.stopThread(); 
 		b.stopThread();
 	}
}
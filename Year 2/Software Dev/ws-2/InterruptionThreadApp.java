public class InterruptionThreadApp
{
	public static void main (String args []) {
 		Thread a = new Thread (new InterruptionExample ()) ;
 		Thread b = new Thread (new InterruptionExample ()) ;
 		a.start();
 		b.start();
 		System.out.println("Main thread is still running");

 		/* note : below we are using the static (class level) sleep method
 		* as we do not have an instance to refer to to cause the main thread
 		* to sleep, instead Thread.sleep() causes the currently executing
 		* thread to sleep - which here is the main method as we want.
 		*/
 		try {
 			Thread.sleep(1000); // sleep for 1000 milliseconds
 		} catch (InterruptedException e ) {
 			// as before, we are not going to respond to an interruption
 		}
 		a.interrupt();
 		b.interrupt();
	}
}

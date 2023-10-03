public class FlagExample extends Thread
{
	private volatile boolean done = false;
	public FlagExample (){}
	public void run (){
		while (!done){
			//this is a flag and will stop once done is set to true
 			// has inherited the getName() method from Thread
 			System.out.println(this.getName() + " : dum diddly dum.... ");
 			try {
 				Thread.sleep(10); /* Sleep thread for 10 milliseconds - note
 				this is different from waiting */
		 		System.out.println (this.getName() + " : finished sleeping ") ;
 			} catch (InterruptedException e) {
 		/* any time a thread is sleeping or waiting, there is a potential for it to
		/* be interrupted – here we won’t do anything as we are using a flag for  
		/* communication, not interruption */
 			}
 	 	}
     	}
 	public void stopThread() {
		 //when this function is called the flag is being set, which means that the next time the method run is called it will not enter the while loop
 		System.out.println(this.getName() + " : flag is being set......") ;
 		this.done = true;
 	}
 }
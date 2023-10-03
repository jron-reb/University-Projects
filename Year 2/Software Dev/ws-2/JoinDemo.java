public class JoinDemo {
   public static void main(String[] args) throws InterruptedException {
   
      Thread thread1 = new Thread(new Runnable() {
            public void run() {
               System.out.println("Thread1 started");
               System.out.println("Thread1 sleeping for 2 seconds");
               try {
                  Thread.sleep(2000);
               } catch (InterruptedException e){}
               System.out.println("Thread1 completed");
            }
      });
      
      Thread thread2 = new Thread(new Runnable() {
            public void run() {
               System.out.println("Thread2 started");
               System.out.println("Thread2 completed");
            }
      });
      
      thread1.start();
      thread1.join();
      thread2.start();
      
   }
}
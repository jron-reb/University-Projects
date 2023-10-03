public class YieldDemo {
   public static void main(String[] args){
      Thread thread1 = new Thread1();
      Thread thread2 = new Thread2();
      
      thread1.setPriority(2);
      thread2.setPriority(1);
       
      thread1.start();
      thread2.start();
   }
}
 
class Thread1 extends Thread {
   public void run() {
      for (int i = 0; i < 5; i++) {
         System.out.println("I am Thread1 : " + i);
         Thread.yield();
      }
   }
}
 
class Thread2 extends Thread {
   public void run() {
      for (int i = 0; i < 5; i++) {
         System.out.println("I am Thread2 : " + i);
         //Thread.yield();
      }
   }
}
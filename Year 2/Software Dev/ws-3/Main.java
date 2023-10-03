import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final PC pc = new PC();
        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();
        // t1 finishes before t2
        t1.join();
        t2.join();
    }

    // This class has a list, producer (adds items to list
    // and consumber (removes items).
    public static class PC {
        // Create a list shared by producer and consumer
        // Size of list is 2.
        // LinkedList<Integer> - it’s Java generics. You will learn Java generics in Week 9. For this exercise, you can go to https:// docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html to check useful methods of LinkedList.
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 2;

        // Function called by producer thread
        public void produce() throws InterruptedException {
            int count = 0;
            for (int i = 0; i < list.size(); i++ ) {
                count++;
            }
            if (count == 2) {
                System.out.println("produce has checked that the list is full " + list);
                
            } else {
                System.out.println("produce has found that the list is not full and is about to produce " + list);
                list.add(5);
                System.out.println("produce has just produced " + list);
            }

        }

        // Function called by consumer thread
        public void consume() throws InterruptedException {
            if (list.isEmpty()) {
                System.out.println("Consume checked that list is empty " + list);
            } else {
                System.out.println("consume is about to remove from list " + list);
                list.remove(0);
                System.out.println("consume just consumed " + list);
            }
        }
    }
}
import java.util.Random;

public class Ugg{
    enum UggRockSize {
        BIGUN(5), QUITE_BIGUN(3), NOT_SO_BIGUN(1);

        private int multiplierValue;
        
        private UggRockSize (int multiplierValue) {
            this.multiplierValue = multiplierValue;
        }

        int getMultiplierValue () {
            return multiplierValue;
        }
    }

    enum UggRockType {
        SPECKILY(7), OUCHY_BLACK(20), FLOAT(2), HOT_HOT_HOT(15);
        
        private int multiplierValue;
        
        private UggRockType (int multiplierValue) {
            this.multiplierValue = multiplierValue;
        }

        int getMultiplierValue () {
            return multiplierValue;
        }
    }

    static class UggRock{
        private UggRockSize rs;
        private UggRockType rt;

        public UggRock (UggRockSize rs, UggRockType rt) {
            this.rs = rs;
            this.rt = rt;
        }

        public UggRockSize getUggRockSize (){
            return rs;
        }
    
        public void setUggRockSize (UggRockSize rs){
            this.rs = rs;
        }
    
        public UggRockType getUggRockType (){
            return rt;
        }
    
        public void setUggRockType (UggRockType rt){
            this.rt = rt;
        }
    
        public String toString () {
            return rs + "," + rt;
        }

        public int rockValue () {
            return this.getUggRockSize().getMultiplierValue() * this.getUggRockType().getMultiplierValue();
        }
    }

    static Random randomNumGen = new Random();

    static UggRock drawRock () {
        double randomSize = randomNumGen.nextDouble();
        double randomType = randomNumGen.nextDouble();

        UggRockType selectType;
        UggRockSize selectSize;


        //use pseudo random number generator to make quite-biguns twice as likely as biguns and not-so-biguns three
        //times as likely as quite-biguns
        if (randomSize <= 1.0/9.0 ) {
            selectSize = UggRockSize.BIGUN;
        } else if (randomSize <= 3.0/9.0 ) {
            selectSize = UggRockSize.QUITE_BIGUN;
        } else {
            selectSize = UggRockSize.NOT_SO_BIGUN;
        }

        if (randomType <= 0.25 ) {
            selectType = UggRockType.FLOAT;
        } else if (randomType <= 0.5) {
            selectType = UggRockType.HOT_HOT_HOT;
        } else if (randomType <= 0.75) {
            selectType = UggRockType.OUCHY_BLACK;
        } else {
            selectType = UggRockType.SPECKILY;
        }

        UggRock randomUggRock = new UggRock (selectSize, selectType);
        return randomUggRock;

    }

    public static void main(String[] args) {
        final Ugg a = new Ugg();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    UggRock placeholder = a.drawRock();
                    System.out.println(Thread.currentThread().getName() + " has drawn the ugg rock: " + placeholder.toString());
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <20; i++) {
                    UggRock placeholder = a.drawRock();
                    System.out.println(Thread.currentThread().getName() + " has drawn the ugg rock: " + placeholder.toString());
                }
            }
        });

        t1.start();
        t2.start();

    }
}
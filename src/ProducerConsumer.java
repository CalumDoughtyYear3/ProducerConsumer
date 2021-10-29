import java.util.Iterator;
import java.util.Vector;

public class ProducerConsumer {

    private static Vector<Object> buffer = new Vector<>();

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }




    public static class Producer extends Thread{
        public Producer(){
            super("Producer"); //set the name
        }

        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(100);
                    System.out.println("Object Produced!");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                buffer.add(new Object());
                if(buffer.size() > 1000){
                    buffer.remove(buffer.size() -1);
                }
            }
        }
    }

    public static class Consumer extends Thread{
        public Consumer(){
            super("Consumer");
        }

        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(100);
                    System.out.println("Object consumed!");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                synchronized (buffer) {
                    Iterator iterator = buffer.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                    }
                }
            }
        }
    }

}

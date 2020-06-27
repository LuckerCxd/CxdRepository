package Day_6;


public class Demo{
    final static Object person =new Object();
    public static class T1 extends Thread{
    	
        public void run(){
            synchronized (person){
                System.out.println(System.currentTimeMillis()+"T1 come");
                try{
                    System.out.println(System.currentTimeMillis()+"T1 wait");
                    person.wait();
                   
                }catch (Exception r){
                    r.getStackTrace();
                }
                System.out.println(System.currentTimeMillis()+"T1 over");
//                person.notify();
            }
            
        }
    }
    public static class T2 extends Thread{
        public void run(){
//        	System.out.println(person);
            synchronized (person){      	
//            	System.out.println(person);
                System.out.println(System.currentTimeMillis()+"T2 come");
                person.notify();
                
                try{
//                    person.wait();
//                	 Thread.currentThread().sleep(2000);
                    System.out.println(System.currentTimeMillis()+"T2 over");
                }catch (Exception r){
                    r.getStackTrace();
                }
 
            }
           
           
        }
    }
 
    public static void main(String args[]){
        try{
            Thread thread1=new T1();
            Thread thread2=new T2();
            thread1.start();
            thread2.start();
        }catch (Exception e){
            e.printStackTrace();
        }
 
    }
}

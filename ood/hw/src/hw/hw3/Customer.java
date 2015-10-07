package hw.hw3;

public class Customer {
   private int arrivalTime;
   private int serviceTime;
   
   public Customer(int t, ItemDistribution id) {
      arrivalTime = t;
      serviceTime = id.getServiceTime();
      System.out.println(serviceTime);
   }
   
   public void elapseOneSecond() {
   		serviceTime --;
   }
   
   public boolean isFinished() {
   		return serviceTime == 0;
   }
   
   public int arrivalTime() {
      return arrivalTime;
   }
}
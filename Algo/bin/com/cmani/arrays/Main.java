package PyaPay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

    // Your code here!
    static HashMap<String, Double> billCoinCollection = new HashMap<String, Double>(){{
       put("PENNY", .01);
       put("NICKEL", .05);
       put("DIME", .10);
       put("QUARTER", .25);
       put("HALF DOLLAR", .50);
       put("ONE", 1.00);  
       put("TWO", 2.00);
       put("FIVE", 5.00);
       put("TEN", 10.00);
       put("TWENTY", 20.00);
       put("FIFTY", 50.00);
       put("ONE HUNDRED", 100.00);
     }};

   public static void main(String[] args) throws Exception {
      
    System.out.println(CalculateChange("12.20;13.0"));


   }

   
   public static String CalculateChange(String purchasePriceCash)
   {
    double purchasedPrice = Double.parseDouble(purchasePriceCash.split(";")[0]);
    double Cash = Double.parseDouble(purchasePriceCash.split(";")[1]);
    List<String> changedCoins = new ArrayList<String>();
    if (Double.compare(Cash, purchasedPrice) <0)
    {
         return "Error";
    }
    else if(Double.compare(purchasedPrice, Cash) == 0)
    {
         
         return "Zero";
    }
    else{
       double getChange = Cash - purchasedPrice;
       for (Entry<String, Double> entry : billCoinCollection.entrySet()) {
           if (entry.getValue().equals(getChange)) {
             return entry.getKey();
           }
       }
       List<String> coinsList  = calculateChangeFromRegister(getChange, changedCoins);
       Collections.sort(coinsList);
       return String.join(",", coinsList);
    }
   }

   static List<String> calculateChangeFromRegister(double change, List<String> changedCoins)
   {
       String registerkey ="";
       //final double EPSILON = 0.0000001;
      // BigDecimal bd1 = new BigDecimal(change);
       for (Entry<String, Double> entry : billCoinCollection.entrySet()) {
          // if (Math.abs(entry.getValue()- change) <  EPSILON) {
    	   
    	   if(new BigDecimal(entry.getValue().doubleValue()).setScale(2, RoundingMode.DOWN).compareTo(new BigDecimal(change).setScale(2, RoundingMode.DOWN)) == 0) {
               registerkey =  entry.getKey();
               break;
           }
       }
       if (!registerkey.isEmpty())
       {
           changedCoins.add(registerkey);
           return changedCoins;
       }
       Map<String,Double> tempCoins = new HashMap<>();
       for (Entry<String, Double> entry : billCoinCollection.entrySet()) {
           if (entry.getValue()<=change) {
               tempCoins.put(entry.getKey(),entry.getValue());
           }
       }
     

       double gotAmount = tempCoins.entrySet().stream().max((entry1,entry2)->entry1.getValue()>entry2.getValue()?1:-1).get().getValue();
       billCoinCollection.forEach((key,value)->{
           if(value.equals(gotAmount)){
               changedCoins.add(key);
           }
       });
      
           double remainingAmount = change -  gotAmount;
           return calculateChangeFromRegister(remainingAmount, changedCoins);
   }
	
}

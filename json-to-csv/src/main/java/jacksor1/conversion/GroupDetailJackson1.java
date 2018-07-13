/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jacksor1.conversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aman
 */
public class GroupDetailJackson1 {
     List<Map<String,Object>> groupDetails=new ArrayList<>();
     Map<String,Object> forwardedBalance=new HashMap<>();
     double creditTotal;
     double debitTotal;
     Map<String,Object> closingBalance=new HashMap<>();
     List<Map<String,Object>> childGroups=new ArrayList<>();
      List<Map<String,Object>> accounts=new ArrayList<>();
      String uniqueName;
      String groupName;
      String category;
      
   public List<Map<String,Object>> getGroupDetails()
   {
       return groupDetails;
   }
   public Map<String,Object> getForwardedBalance()
   {
       return forwardedBalance;
   }
   public double getcreditTotal()
   {
       return creditTotal;
   }
    public double getdebitTotal()
    {
      return debitTotal;  
   }
   public Map<String,Object> getClosingBalance()
   {
      return closingBalance; 
   }
   public String getName(){
       return uniqueName;
   }
   public String getGroupName(){
       return groupName;
   }
   public String getCategory(){
       return category;
   }
   
    
}

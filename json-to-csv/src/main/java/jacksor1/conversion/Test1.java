/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jacksor1.conversion;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import json.to.csv.JsonToCsvApi;
import okhttp3.Response;
import json.to.csv.Test;

/**
 *
 * @author aman
 */
public class Test1 {
    public static void main(String[] args)
    {
        String response_string=RestTemplateResponse.RestTemplateConversion();
        //Response response= JsonToCsvApi.getResponse();
        //String response_string="";
        String s="";
         //try{
        //InputStream instream=response.body().byteStream();
        //response_string+=Test.convertStreamToString(instream);
     //System.out.println(response_string);
     s+= "{" +response_string.substring(response_string.indexOf("forwardedBalance")-1,response_string.lastIndexOf(']')+1)+"}";
     System.out.println(s);
     //instream.close();
      //}
         //catch(IOException e)
         //{
             //System.out.println(e);
         //}

         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        FileWriter writer = null;
         FileWriter account_writer = null;
          try{
               //objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        GroupDetailJackson1 gdj=objectMapper.readValue(s ,GroupDetailJackson1.class);
        writer = (new FileWriter("/home/aman/aman2.csv"));
        account_writer = (new FileWriter("/home/aman/aman3.csv"));
        writer.append("Name,Category,GroupsName,OpeningBalance,CreditTotal,DebitTotal,ClosingBalance\n");
        account_writer.append("Name,UniqueName,OpeningBalance,CreditTotal,DebitTotal,ClosingBalance\n");
        for(int i=0; i<gdj.getGroupDetails().size(); i++)
        {
            Map<String,Object> map=gdj.getGroupDetails().get(i);
            List<Map<String,Object>> child_array=((List<Map<String,Object>>)map.get("childGroups"));
            
            double total_credit=0;
            double total_debit=0;
            double total_forwarded_balance=0;
            double total_close_balance=0;
            String total_forwarded_type="";
            String total_close_type="";
            
            for (int j=0; j<child_array.size(); j++)
            {
              Map<String,Object> child_map=child_array.get(j);
              try{
                  total_credit+=(int)child_map.get("creditTotal");
              }
              catch(ClassCastException e)
              {
                 total_credit+=(double)child_map.get("creditTotal");
              }
              try{
                 total_debit+=(int)child_map.get("debitTotal"); 
              }
              catch(ClassCastException e)
              {
                 total_debit+=(double)child_map.get("debitTotal");
              }
              try{
                total_forwarded_balance+=(int) ((Map<String,Object>)child_map.get("forwardedBalance")).get("amount");
              }
              catch(ClassCastException e){
                total_forwarded_balance+=(double) ((Map<String,Object>)child_map.get("forwardedBalance")).get("amount");
              }
              
              try{
                total_close_balance+=(int) ((Map<String,Object>)child_map.get("closingBalance")).get("amount");
              }
              catch(ClassCastException e){
                total_close_balance+=(double) ((Map<String,Object>)child_map.get("closingBalance")).get("amount");
              }
              total_forwarded_type=((Map<String,Object>)child_map.get("forwardedBalance")).get("type").toString();
              total_close_type=((Map<String,Object>)child_map.get("closingBalance")).get("type").toString();
               
               List<Map<String,Object>> account_array=(List<Map<String,Object>>)child_map.get("account");
               for (int l=0; l<account_array.size(); l++)
               {
                 Map<String,Object> account_map=account_array.get(l);
                 String account_name=account_map.get("name").toString();
                 String account_unique_name=account_map.get("uniquename").toString();
                 String account_forwarded_amount=(((Map<String,Object>)account_map.get("openingBalance")).get("amount")).toString();
                 String account_forwarded_type=(((Map<String,Object>)account_map.get("openingBalance")).get("type")).toString();
                 String account_close_amount=(((Map<String,Object>)account_map.get("closingBalance")).get("amount")).toString();
                 String account_close_type=(((Map<String,Object>)account_map.get("closingBalance")).get("type")).toString();
                 String account_credit_total=account_map.get("creditTotal").toString();
                 String account_debit_total=account_map.get("debitTotal").toString();
                 
              account_writer.append(account_name+","+account_unique_name+","+account_forwarded_amount+" ("+account_forwarded_type+")"+","+account_credit_total+","+account_debit_total+","+account_close_amount+" ("+account_close_type+")"+"\n");
               }
              account_writer.append("Total"+","+","+ total_forwarded_balance+ "("+total_forwarded_type+")"+","+total_credit+","+total_debit+","+total_close_balance+"("+total_close_type+")"+"\n");
            }
            
           String name=map.get("uniqueName").toString();
           String category=map.get("category").toString();
           String groupName=map.get("groupName").toString();
           String forwarded_amount=(((Map<String,Object>)map.get("forwardedBalance")).get("amount")).toString();
           String forwarded_type=(((Map<String,Object>)map.get("forwardedBalance")).get("type")).toString();    
           String close_amount=(((Map<String,Object>)map.get("closingBalance")).get("amount")).toString();
           String close_type=(((Map<String,Object>)map.get("closingBalance")).get("type")).toString();
           String credit_total=map.get("creditTotal").toString();    
           String debit_total=map.get("debitTotal").toString(); 
           
           writer.append(name+","+ category +","+groupName+","+forwarded_amount+" "+"("+forwarded_type+")"+","+credit_total+","+debit_total+","+close_amount+" "+"("+close_type+")"+"\n");
         }
        writer.append("Total"+","+","+","+","+gdj.getForwardedBalance().get("amount").toString()+" "+"("+gdj.getForwardedBalance().get("type").toString()+")"+","+gdj.getcreditTotal()+","+gdj.getdebitTotal()+","+gdj.getClosingBalance().get("amount").toString()+" "+"("+gdj.getClosingBalance().get("type").toString()+")"+"\n" );                                      
        }
        catch(JsonParseException e)
        {
            e.printStackTrace();
        }
        catch(JsonMappingException e)
        {
            e.printStackTrace();
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
          {
             Test.close(writer); 
          }
      }
}
         
         
         
         
         
         
         
         
         
         
         
         
         
         
    
    


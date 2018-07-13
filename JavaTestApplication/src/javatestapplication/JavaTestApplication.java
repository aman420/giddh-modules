/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatestapplication;

/**
 *
 * @author Akash
 */
public class JavaTestApplication {
    
     String s= "{\n" +
"   \"sleep\":{\n" +
"      \"userInfo\":{\n" +
"         \"firstName\":\"Hans\",\n" +
"         \"lastName\":\"Schmidt\",\n" +
"         \"country\":\"Germany\",\n" +
"         \"city\":\"Berlin\"\n" +
"      },\n" +
"      \"settings\":{\n" +
"         \"setting2\":\"none\",\n" +
"         \"setting1\":\"y\"\n" +
"      },\n" +
"      \"program\":{\n" +
"         \"appVersion\":\"1.7\",\n" +
"         \"serverVersion\":19,\n" +
"         \"appName\":\"name\"\n" +
"      },\n" +
"      \"userType\":\"new\"\n" +
"   }\n" +
"}";  
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            JSONArray jsonArray = new JSONArray(json);
            flatJson = parse(jsonArray);
        } catch (Exception e) {
            throw new Exception("Json might be malformed");
        }
        
    }
   
    
    
    
}

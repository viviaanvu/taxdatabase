/*****************************************************************************************************************************
* Message Module
******************************************************************************************************************************
* Receives error codes from task modules and returns the appropriate message
******************************************************************************************************************************
* Input: Language Code, Message Code
* Output: returned Message
******************************************************************************************************************************
* Austin Escobedo
* 5/12/20
* CMSC 355 Team 05
******************************************************************************************************************************
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Message {
    public static String messages(String langCode, String messageCode)throws FileNotFoundException{
        String finalMessage = null;
        int ReturnCode;
        String Language = null;
        boolean langFlag = false;
        boolean messageFlag = false;
        Scanner LangScan = new Scanner(new FileInputStream("D:\\message.txt"));
        while(langFlag == false && LangScan.hasNext()){
            String line = LangScan.nextLine();
            String[] Langline = line.split(",");
            if(line.indexOf(langCode) != 1){
                langFlag = true;
                Language = Langline[1];
            }
        }
        
        if(langFlag){
            Scanner MessageScan = new Scanner(new FileInputStream(Language));
            while(messageFlag == false && MessageScan.hasNext()){
                String line = MessageScan.nextLine();
                String[] messageLine = line.split(",");
                if(line.indexOf(messageCode) != 1){
                    messageFlag = true;
                    finalMessage = messageLine[1];
                    ReturnCode = 0;
                    return finalMessage;
                }
            }
            if(messageFlag == false){
                ReturnCode = 4;
                finalMessage = "NOT Found";
                return finalMessage;
            }
        }
        else{
            ReturnCode = 4;
            finalMessage = "NOT Found";
            return finalMessage;
        }
        return finalMessage;
    }

    //public static void main(String[] args)throws FileNotFoundException{
        //System.out.println("UNIT TEST passing MSGENG and 404 into messages");                      
        //System.out.println(messages("MSGENG", "404"));
        //System.out.println("UNIT TEST passing MSGENG and 903 into messages");
        //System.out.println(messages("MSGENG", "903"));
        //System.out.println("UNIT TEST passing MSGGER and 703 into messages");
        //System.out.println(messages("MSGGER", "703"));
        //System.out.println("UNIT TEST passing MSGGER and 805 into messages");
        //System.out.println(messages("MSGENG", "805"));
        //System.out.println("UNIT TEST passing MSGENG and 403 into messages");                       //passing code (403) that isn't in text file
        //System.out.println(messages("MSGENG", "403"));
        //System.out.println("UNIT TEST passing MSGITAL and 703 into messages");                      //passing language (italian) that isn't in text file
        //System.out.println(messages("MSGITAL", "703"));

    //}
}

 /**************************************************************************************
  *    Translate Module
  *
  * Component: Task Module
  ***************************************************************************************
  * Function:
  *   Translates given word from English to given language
  *----------------------------------------------------------------------------------------------------------------------------------------
  *    Input:
  *          Parameters - string langCode - desired language
  *                       string word- word to be translated
  *    Output:
  *          Return – Translated word
  *----------------------------------------------------------------------------------------------------------------------------------------
  *    Author Kimberly Pham
  *    Version 05/2/2021   CMCS 355 		UPDATED 5/12/2021
  **************************************************************************************/ 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Translator extends Message{
   static String translate(String langCode, String word)throws FileNotFoundException{
      String newWord = null;
      String path = null;
      boolean flag1 = false;
      boolean flag = false;
      int ReturnCode;
     
      Scanner sc1 = new Scanner(new FileInputStream("D:\\translate.txt"));
      while(flag1 == false && sc1.hasNext()) {
         String line = sc1.nextLine();
		 String[] transWords = line.split(",");
         if(line.indexOf(langCode)!=-1) {
            flag1 = true;
	    path = transWords[1];
         }
      }
     
      if(flag1){
 	     Scanner sc2 = new Scanner(new FileInputStream(path));
 	     while(flag == false && sc2.hasNext()) {
        	  String line = sc2.nextLine();
			 String[] words = line.split(",");
    	   	  if(line.indexOf(word)!=-1) {
        	    	flag = true;
			newWord = words[1];
                        return newWord;
	          }
      	     }

             if(flag == false){
                  ReturnCode = 4;
		  newWord = Message(MSGGER,813);
		  return newWord;
             }
      }

      else{
             ReturnCode = 4;
	     newWord = Message(MSGGER,805);
             return newWord;
      }
	return ("ERROR NOT FOUND");
   }
/*********************************************************************************************


// 					** UNIT TEST ** 						//


   public static void main(String[] args)throws FileNotFoundException{
	   System.out.println("UNIT TEST passing German and dog to translate mondule: ");
	   System.out.println(translate("German","dog")+"\n");						//prints "hund" as expected

	   System.out.println("UNIT TEST passing French and dog to translate mondule: ");
	   System.out.println(translate("French","dog")+"\n");						//prints "chien" as expected

	   System.out.println("UNIT TEST passing Klingon and dog to translate mondule: ");
	   System.out.println(translate("Klingon","dog")+"\n");						//prints "Ha'DlbaH" as expected

	   System.out.println("UNIT TEST passing Spanish and dog to translate mondule: ");
	   System.out.println(translate("Spanish","dog")+"\n");						//prints "perro" as expected

	   System.out.println("UNIT TEST passing Vietnamese and dog to translate mondule: ");
	   System.out.println(translate("Vietnamese","dog")+"\n");					//prints "Language Not Found" as expected

	   System.out.println("UNIT TEST passing Spanish and lion to translate mondule: ");
	   System.out.println(translate("Spanish","lion")+"\n");					//prints "Word Not Found" as expected


   }
  **************************************************************************************/ 
}

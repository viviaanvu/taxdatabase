 /**************************************************************************************
  * Orchestration Module
  *
  * Component: Service Broker Module
  ***************************************************************************************
  * Function:Services that provide non-functional support for the application; 
  * Allows for the integration of services(tasks).
  *   
  *----------------------------------------------------------------------------------------
  *    Input:
  *          Service Code, Parameter List for service
  *    Output:
  *          Return Code, Data returned from service module
  *----------------------------------------------------------------------------------------
  *    Author Lannah Davis
  *    Version 05/2/2021 UPDATE: 5/8/2021  CMCS 355 
  **************************************************************************************/ 

import java.io.*;
import java.util.Scanner;

public class ServiceBroker extends Message{

	static String servicebroker(String ServiceCode, String params) throws IOException {
		File ServeCode = new File("Service.txt");
		//Expecting records in Service.txt to look like:
		//    trans,trans.java
		//    tax,tax.tax.java
		//    message,message.java
		Scanner sc = new Scanner(ServeCode);
		String [] ServeArg;
		String ReturnData;
		String msg;
		String msgCode;
		String record;
		int ReturnCode;
		boolean foundFlag = false;
		sc.next();
		do{
		 record = sc.nextLine();
		 ServeArg = record.split(",");
		 if(ServiceCode == ServeArg[0]) {
			foundFlag = true;
		 }
		 else {
			 sc.hasNext();
		 }
		}while(foundFlag == false || sc.hasNextLine());
		
		if(foundFlag == true) {
			//-------------------------------------------------------------------------------------------------------------------
			//  code to call indpendent modules like translate.java, Tax.java,. etc should look something like the following
			//-------------------------------------------------------------------------------------------------------------------
			Process proc2 = null;
                        proc2 = Runtime.getRuntime().exec("java " + ServeArg[1] + " " + params);  //java tax.java 2020S 60000
		        BufferedReader in = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
			String line; 
			line = in.readLine();
			ReturnData = line;
			ReturnCode = 0;
		}
		else {
			msg = ("ServiceCode");
			msgCode = ("404");
			ReturnData = messages(msg, msgCode);
			ReturnCode = 4;
		}
		return ReturnData;
		
	}
	//public static void main(String [] args) throws IOException {
		//System.out.println("UNIT TEST passing Trans and trans params);
		//System.out.println(servicebroker("Trans","German Dog")+"\n");
		//System.out.println("UNIT TEST passing Tax and tax params");
		//System.out.println(servicebroker("Tax","2020S 65000")+"\n");
		//System.out.println("UNIT TEST passing Message and message params");
		//System.out.println(servicebroker("Message","German 404")+"\n");
	
	//}

}

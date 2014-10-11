import java.io.*; 
import java.net.*; 

class UDPServer { 
	public static void main(String args[]) throws Exception 
	{

		DatagramSocket serverSocket = new DatagramSocket(9876); 

		byte[] receiveData = new byte[1024]; 
		byte[] sendData= new byte[1024]; 
		char opCode;
		int num1, num2;
		String str1, str2, str3;
		
		String capitalizedSentence = "";
		
		

		while(true) 
		{ 
			DatagramPacket receivePacket = 
				new DatagramPacket(receiveData, receiveData.length); 

			serverSocket.receive(receivePacket); 

			String sentence = new String(receivePacket.getData());
			
			//split string
			String[] words = sentence.split(" ");
			
			str1 = words[0];
			str2 = words[1];
			str3 = words[2];
			//split string end
			
			opCode = str1.charAt(0);
			num1 = Integer.parseInt(str2.trim());
			num2 = Integer.parseInt(str3.trim());


			InetAddress IPAddress = receivePacket.getAddress(); 

			int port = receivePacket.getPort(); 
				
				if (opCode == '+')
					capitalizedSentence = num1 + " " + opCode + " " + num2 + " = " + (num1+num2) + "\n";
				else if(opCode == '-')
					capitalizedSentence = num1 + " " + opCode + " " + num2 + " = " + (num1-num2) + "\n";
				else if(opCode == '*')
					capitalizedSentence = num1 + " " + opCode + " " + num2 + " = " + (num1*num2) + "\n";
				else if(opCode == '/')
					capitalizedSentence = num1 + " " + opCode + " " + num2 + " = " + (num1/num2) + "\n";
				else if(opCode == '%')
					capitalizedSentence = num1 + " " + opCode + " " + num2 + " = " + (num1%num2) + "\n";
				else if(opCode == '^')
					capitalizedSentence = num1 + " " + opCode + " " + num2 + " = " + (Math.pow(num1, num2)) + "\n";
					
					

			
			sendData = capitalizedSentence.getBytes(); 

			DatagramPacket sendPacket = 
				new DatagramPacket(sendData, sendData.length, IPAddress, port); 

			serverSocket.send(sendPacket);
		}
	}
}

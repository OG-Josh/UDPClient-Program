import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
*  UDP Client Program
*  This client program will connect to an open UDP Server and take in multiple sentences until
*  the user enters "Goodbye" to quit the program
*  @author: Joshua Yang
*     email: joyang@chapman.edu
*     date: 2/25/2019
*  @version: 3.1
*/


class MyUdpClient {
  public static void main(String[] args) throws Exception {

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

    DatagramSocket clientSocket = new DatagramSocket();

    InetAddress ipAddress = InetAddress.getByName("localhost");

    //Declared variables
    //String modifiedSentence;
    String sentence;

    do {
      System.out.println("Type a Sentence");
      sentence = inFromUser.readLine();
      
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9876);
      
      clientSocket.send(sendPacket);
      
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      
      clientSocket.receive(receivePacket);
      
      //modifiedSentence = new String(receivePacket.getData());
      String modifiedSentence = new String(receivePacket.getData());
      
      System.out.println("FROM SERVER:" + modifiedSentence);
    
    } while (!sentence.equals("Goodbye"));

    clientSocket.close();
  
  }
}

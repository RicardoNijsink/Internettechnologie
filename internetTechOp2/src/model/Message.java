package model;

import java.util.ArrayList;

/**
 * Class voor het bijhouden van de gegevens van een e-mailbericht
 * @author Ricardo
 *
 */
public class Message {
	private String date;
	private String from;
	private String subject;
	private String to;
	private ArrayList<MessagePart> message;
	private int octets=0;
	private boolean deleted;
	
	/**
	 * Constructor van een e-mailbericht
	 * @param message De inhoud van het bericht
	 * @param from De afzender van het bericht
	 * @param to De ontvangen van het bericht
	 * @param subject Het onderwerp van het bericht
	 * @param date De datum waarop het bericht is aangemaakt
	 */
	public Message(ArrayList<MessagePart> message, String from, String to, String subject, String date) {
		this.from=from;
		this.to=to;
		this.subject=subject;
		this.date=date;
		this.message = message;
		for (MessagePart m : message){
			this.octets+=m.getMessage().getBytes().length;
		}
//		this.octets = message.getBytes().length;
	}

	public String getMessage() {
		 String returnString ="\nMIME-Version: 1.0\nContent-Type: multipart/mixed;\n	boundary=\"boundaryEmailElements\"\n\n";
				for (MessagePart m : message){
					returnString+=m;
				}
				
//				+"--boundaryEmailElements\nContent-Type: text/plain\n\n"
//				+message+"\n\n"+
//				"--boundaryEmailElements\n"
//				+"Content-Type: text/plain;\n"
//				+"Content-Disposition: attachment;\n"
//        		+"	filename= \"test.txt\"\n\n"
//
//				+ "this is the attachment text\n\n"

				returnString+="--boundaryEmailElements--";
				return returnString;
	}

	public int getLength() {
		return octets;
	}
	
	/**
	 * Methode voor het weergeven van een e-mailbericht in POP3-formaat
	 * @return Het string-obejct van het e-mailbericht in POP3-formaat 
	 */
	public String getMessageHeader (){
		return "Date: "+date+"\nFrom: "+from+"\nSubject: "+subject+"\nTo: "+to+"\nMIME-Version: 1.0\nContent-Type: multipart/mixed;\n	boundary=\"boundaryEmailElements\"\n\n";
	}
	
	/**
	 * Methode voor het markeren van een bericht voor verwijdering
	 */
	public void delete(){
		deleted = true;
	}
	
	/**
	 * Methode voor het ongedaan maken van het markeren van een bericht voor verwijdering
	 */
	public void unMark(){
		deleted = false;
	}
	
	public boolean isDeleted(){
		return deleted;
	}

}

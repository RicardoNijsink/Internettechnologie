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
	private int octets = 0;
	private boolean deleted;
	
	/**
	 * Constructor van een bericht
	 * @param message De arraylist met de berichtdelen
	 * @param from De verzender van het bericht
	 * @param to De ontvanger van het bericht
	 * @param subject Het onderwerp van het bericht
	 * @param date De datum van het bericht
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
	}

	/**
	 * Methode voor het teruggeven van het bericht
	 * @return Het bericht in String-formaat
	 */
	public String getMessage() {
		 String returnString ="\nMIME-Version: 1.0\nContent-Type: multipart/mixed;\n	boundary=\"boundaryEmailElements\"\n\n";
				for (MessagePart m : message){
					returnString+=m;
				}

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

package model;

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
	private String message;
	private int octets;
	private boolean deleted;
	
	/**
	 * Constructor van een e-mailbericht
	 * @param message De inhoud van het bericht
	 * @param from De afzender van het bericht
	 * @param to De ontvangen van het bericht
	 * @param subject Het onderwerp van het bericht
	 * @param date De datum waarop het bericht is aangemaakt
	 */
	public Message(String message, String from, String to, String subject, String date) {
		this.from=from;
		this.to=to;
		this.subject=subject;
		this.date=date;
		this.message = message;
		this.octets = message.getBytes().length;
	}

	public String getMessage() {
		return message;
	}

	public int getLength() {
		return octets;
	}
	
	/**
	 * Methode voor het weergeven van een e-mailbericht in POP3-formaat
	 * @return Het string-obejct van het e-mailbericht in POP3-formaat 
	 */
	public String getMessageHeader (){
		return "Date: "+date+"\nFrom: "+from+"\nSubject: "+subject+"\nTo: "+to+"\nContent-Type: text/plain";
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

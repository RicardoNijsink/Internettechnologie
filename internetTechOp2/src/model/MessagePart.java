package model;

/**
 * Class voor het bijhouden van informatie over een berichtsdeel
 * Een berichtsdeel is bijvoorbeeld een plain text bericht of een bijlage
 * @author Ricardo
 *
 */
public class MessagePart {
	public final static String PLAINTEXT="text/plain";
	private String message;
	private String contentType;
	private String filename;
	
	/**
	 * Constructor van een berichtsdeel
	 * @param message Het bericht 
	 * @param filename De naam van het bericht
	 * @param contentType Het type van het bericht
	 */
	public MessagePart(String message, String filename, String contentType) {
		this.message = message;
		this.filename= filename;
		this.contentType = contentType;
	}
	
	/**
	 * Constructor van een plain text berichtsdeel
	 * @param message
	 */
	public MessagePart(String message) {
		this.message = message;
		contentType=PLAINTEXT;
	}
	
	/**
	 * ToString van een MessagePart
	 * Voegt attachmentheader toe als het een bijlage is.
	 * Anders alle headers voor de content type
	 */
	@Override
	public String toString() {
		String attachmentHeaders="";
		if (filename!=null&&filename.length()>0){
			attachmentHeaders= "Content-Disposition: attachment;\n"
					+"	filename= \"test.txt\"\n";
		}
		
		
		return "--boundaryEmailElements\nContent-Type: "+contentType+"\n"
				+attachmentHeaders
				+"\n"+message+"\n\n";
	}

	public String getMessage() {
		return message;
	}
	
	
	
	
	
}

package model;

public class MessagePart {

	private String message;
	public final static String PLAINTEXT="text/plain";
	private String contentType;
	private String filename;
	
	
	
	public MessagePart(String message, String filename, String contentType) {
		this.message = message;
		this.filename= filename;
		this.contentType = contentType;
	}
	
	public MessagePart(String message) {
		this.message = message;
		contentType=PLAINTEXT;
	}
	
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

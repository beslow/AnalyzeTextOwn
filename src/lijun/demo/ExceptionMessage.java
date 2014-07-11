package lijun.demo;

public class ExceptionMessage extends Exception {
	private String message;
	private int lineNo;
	public ExceptionMessage(String message,int lineNo) {
		// TODO Auto-generated constructor stub
		super(message);
		this.lineNo = lineNo + 1;
		this.message = message;
	}
	public int getLineNo() {
		return lineNo;
	}
	
	public String getMessage() {
		return "line " + this.lineNo+ " " + this.message;
	}
}

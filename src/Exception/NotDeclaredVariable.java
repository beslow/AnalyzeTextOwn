package Exception;

public class NotDeclaredVariable extends Exception {
	String errorMessage;
	
	public NotDeclaredVariable( String errorMessage ) {
		this.errorMessage = errorMessage;
	}
	
	public String toString()
    {
         return errorMessage;
    }
	
	public String getMessage()
    {
         return errorMessage;
    }
}

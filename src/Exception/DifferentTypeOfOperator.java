package Exception;

public class DifferentTypeOfOperator extends Exception {
	String errorMessage;
	
	public DifferentTypeOfOperator( String errorMessage ) {
		this.errorMessage = errorMessage + "  the two operators are not the same type ";
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

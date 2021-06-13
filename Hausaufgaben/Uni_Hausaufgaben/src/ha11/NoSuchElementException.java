package ha11;

public class NoSuchElementException extends RuntimeException{
	
	public NoSuchElementException(String s) {
		super(s);
	}
	
	public NoSuchElementException() {
		super("The element you were looking for could not be found");
	}
}

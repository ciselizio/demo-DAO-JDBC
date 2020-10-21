package db;

@SuppressWarnings("serial")
public class DbIntegrityException extends RuntimeException{
	
	public DbIntegrityException (String sMG) {
		super(sMG);
	}

}

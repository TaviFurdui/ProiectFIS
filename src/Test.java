import static org.junit.Assert.*;

import proiect.ErrorLogin;
import proiect.Login;

class Test {
	
	private Login log;
	private ErrorLogin err;
	
	@org.junit.jupiter.api.Test

	  public void test() {
	    log = new Login();
	    log.open();
	    System.out.println(log.getTxtEmail().getText());
	    assertEquals("ion@gmail.com",log.getTxtEmail().getText());
		
	  }

}

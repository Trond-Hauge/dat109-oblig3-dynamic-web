package admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import utils.BCrypt;

@TestInstance(Lifecycle.PER_CLASS)
public class AdminTest {
	
	private Admin admin;
	private final String PASSWORD = "12345";
	
	@BeforeAll
	public void setup() {
		String username = "username";
		admin = new Admin(username,PASSWORD);
	}
	
	@Test
	public void adminHashedPasswordTest() {
		Assertions.assertTrue(BCrypt.checkpw(PASSWORD, admin.getHashedPassword()));
	}

}

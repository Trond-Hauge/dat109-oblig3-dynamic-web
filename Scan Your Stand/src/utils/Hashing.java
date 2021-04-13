package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Hashing {
	
	public static String hashPhone(String phone) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(phone.getBytes(StandardCharsets.UTF_8));
			String hash = new String(hashBytes,"UTF-8");
			return hash;
		}
		catch(Exception e) {
			return null;
		}
	}

}

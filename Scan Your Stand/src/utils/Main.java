package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Class for very simple testing. Clear everything once finished
 *
 */
public class Main {

	public static void main(String[] args) {
		
		String phone = "88888888";
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] bytes = digest.digest(phone.getBytes(StandardCharsets.UTF_8));
			String s = new String(bytes,"UTF-8");
			System.out.println(s);
			System.out.println(s.equals(new String(digest.digest(phone.getBytes(StandardCharsets.UTF_8)), "UTF-8")));
			System.out.println(s.length());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

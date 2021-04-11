package utils;

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
		
		String s = "Item2";
		
		List<String> list = Arrays.asList("Item1", s, "Item2");
		System.out.println(list);
		list.remove(s);
		System.out.println(list);
		
	}
}

package randomCodes;

import java.util.HashMap;
import java.util.Map;

public class JavaProblems {

	public static void main(String[] args) {
		// charcterCountInString("Ayush Ranjan");
		characterOccurenceInStringWithHashMap("automation automation");
	}

	static void charcterCountInString(String givenString) {
		givenString.toLowerCase();
		int initalCount = 0;

		for (int i = 0; i < givenString.length(); i++) {
			for (int j = 0; j < givenString.length(); j++) {
				if (givenString.charAt(i) == givenString.charAt(j)) {
					initalCount++;
				}
			}
			System.out.println("Count for " + givenString.charAt(i) + " is: " + initalCount);
			initalCount = 0;
		}
	}

	static void characterOccurenceInStringWithHashMap(String givenString) {
		givenString.toLowerCase();
		Map<Character, Integer> stringHashMap = new HashMap<Character, Integer>();

		for (char character : givenString.toCharArray()) {
			stringHashMap.put(character, stringHashMap.getOrDefault(character, 0));

			if (stringHashMap.containsKey(character)) {
				stringHashMap.put(character, stringHashMap.get(character) + 1);
			}
//			else {
//				
//				stringHashMap.put(character, 1);
//			}
		}

		System.out.println(stringHashMap);
	}

}

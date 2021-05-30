import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);

        String decodeChars = sc.nextLine();
        String encodeChars = sc.nextLine();

        String strForEncode = sc.nextLine();
        String strForDecode = sc.nextLine();

        Map<Character, Character> decodeMap = new HashMap<>();
        for (int i = 0; i < decodeChars.length(); i++) {
            decodeMap.put(encodeChars.charAt(i), decodeChars.charAt(i));
        }

        Map<Character, Character> encodeMap = new HashMap<>();
        for (int i = 0; i < decodeChars.length(); i++) {
            encodeMap.put(decodeChars.charAt(i), encodeChars.charAt(i));
        }

        System.out.println(encode(strForEncode, encodeMap));
        System.out.println(decode(strForDecode, decodeMap));
    }

    private static String encode(String val, Map<Character, Character> encodeMap) {
        StringBuilder sb = new StringBuilder();
        for (char ch : val.toCharArray()) {
            sb.append(encodeMap.get(ch));
        }
        return sb.toString();
    }

    private static String decode(String str, Map<Character, Character> decodeMap) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            sb.append(decodeMap.get(ch));
        }
        return sb.toString();
    }
}
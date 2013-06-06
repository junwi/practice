package test;

import java.util.Arrays;

public class BinaryCode {

	public static String[] decode(String message) {
		String[] result = {"0","1"};
		for (int i = 0; i < result.length; ++i) {
			for (int j = 0; j < message.length() - 1; ++j) {
				int current = message.charAt(j) - '0';
				int previous = result[i].charAt(j) - '0';
				if (j > 0) {
					previous += result[i].charAt(j - 1) - '0';
				}
				int next = current - previous;
				if (next < 0 || next > 1) {
					result[i] = "none";
					break;
				}
				result[i] += next;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(decode("123210120")));
	}
}

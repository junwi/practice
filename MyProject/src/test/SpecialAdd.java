package test;

public class SpecialAdd {

	public static int add(int a, int b) {
		if (b == 0) {
			return a;
		}
		return add((a ^ b), (a & b) << 1);
	}
}

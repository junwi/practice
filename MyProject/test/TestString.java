import static org.junit.Assert.*;
import org.junit.Test;
public class TestString {

	@Test
	public void test1() {
		String a = "a1";
		String b = "a" + 1;
		assertTrue(a == b);
	}
	@Test
	public void test2() {
		String a = "ab";
		String bb = "b";
		String b = "a" + bb;
		assertFalse(a == b);
	}
	@Test
	public void test3() {
		String a = "ab";
		final String bb = "b";
		String b = "a" + bb;
		assertTrue(a == b);
	}
	@Test
	public void test4() {
		String a = "ab";
		final String bb = getBB();
		String b = "a" + bb;
		assertFalse(a == b);
	}
	String getBB() {
		return "b";
	}
	
	String ab = "ab";
	@Test
	public void test5() {
		String s1 = "a";
		String s2 = "b";
		String s = s1 + s2;
		assertFalse(s == ab);
		assertTrue(s.intern() == ab);
	}
	
	String ac = new String("ab");
	@Test
	public void test6() {
		String s1 = "a";
		String s2 = "b";
		String s = s1 + s2;
		assertFalse(s == ac);
		assertFalse(s.intern() == ac);
		assertTrue(s.intern() == ac.intern());
	}
}

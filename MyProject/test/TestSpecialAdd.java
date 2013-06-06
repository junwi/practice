import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import test.SpecialAdd;


public class TestSpecialAdd {

	@Test
	public void testAdd() {
		Random random = new Random();
		for (int i = 0; i < 100; ++i) {
			int a = random.nextInt();
			int b = random.nextInt();
			assertEquals(a + b, SpecialAdd.add(a, b));
		}
	}
}

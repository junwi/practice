import java.util.Random;

import org.junit.Test;

import test.CountUnfairs;
import static org.junit.Assert.*;

public class TestCountUnfairs {

	public int[] makeArray1() {
		int[] array = new int[100000];
		Random random = new Random();
		for (int i = 0; i < array.length; ++i) {
			array[i] = random.nextInt(1000);
		}
		return array;
	}
	
	public int[] makeArray2() {
		int[] array = {727, 46, 975, 741, 815, 7, 502, 946, 423, 767};
		return array;
	}

	@Test
	public void testCount() {
		int[] array = makeArray1();
		long time = System.currentTimeMillis();
		int normal = CountUnfairs.countNormal(array, 0, array.length - 1);
		long time1 = System.currentTimeMillis();
		System.out.println(time1 - time);
		int literary = CountUnfairs.count(array, 0, array.length - 1);
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time);
		assertEquals(normal, literary);
	}
}

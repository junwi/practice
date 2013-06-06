package net.newsmth.junwi.wordcounter;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestWordCounter {

	@Test
	public void testCount() {
		String[] words = { "1", "234", "3456" };
		List<String> list = Arrays.asList(words);
		BitSetWordCounter wordCounter = new BitSetWordCounter(list);
		wordCounter.count("1234567890");
		Map<String, Integer> result = wordCounter.getResult();
		System.out.println(result);
		assertEquals(3, result.size());
	}
}

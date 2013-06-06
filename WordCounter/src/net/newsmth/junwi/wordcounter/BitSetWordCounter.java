package net.newsmth.junwi.wordcounter;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BitSetWordCounter {

	private List<String> words;

	private Map<Integer, BitSet> lengthMap = new HashMap<>();
	private Map<Integer, Map<Character, BitSet>> positionMap = new HashMap<>();

	private List<Counter> counters = new LinkedList<>();

	private Map<String, Integer> result = new HashMap<>();

	public BitSetWordCounter(List<String> words) {
		this.words = words;

		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);

			BitSet lengthBitSet = lengthMap.get(word.length());
			if (lengthBitSet == null) {
				lengthBitSet = new BitSet(words.size());
				lengthMap.put(word.length(), lengthBitSet);
			}
			lengthBitSet.set(i);

			char[] chars = word.toCharArray();
			for (int j = 0; j < chars.length; j++) {
				Map<Character, BitSet> characterMap = positionMap.get(j);
				if (characterMap == null) {
					characterMap = new HashMap<>();
					positionMap.put(j, characterMap);
				}
				char c = chars[j];
				BitSet characterBitSet = characterMap.get(c);
				if (characterBitSet == null) {
					characterBitSet = new BitSet(words.size());
					characterMap.put(c, characterBitSet);
				}
				characterBitSet.set(i);
			}
		}
	}

	public void count(String str) {
		for (int i = 0; i < str.length(); i++) {
			counters.add(createZeroCounter());

			char c = str.charAt(i);
			for (Iterator<Counter> it = counters.iterator(); it.hasNext();) {
				Counter counter = it.next();
				Map<Character, BitSet> characterMap = positionMap.get(counter
						.getPosition());
				if (characterMap == null) {
					it.remove();
					continue;
				}
				BitSet characterBitSet = characterMap.get(c);
				if (characterBitSet == null) {
					it.remove();
					continue;
				}
				BitSet bitSet = counter.getBitSet();
				bitSet.and(characterBitSet);
				if (bitSet.isEmpty()) {
					it.remove();
					continue;
				}
				counter.step();
				BitSet lengthBitSet = lengthMap.get(counter.getPosition());
				if (lengthBitSet == null) {
					continue;
				}

				BitSet resultBitSet = (BitSet) bitSet.clone();
				resultBitSet.and(lengthBitSet);
				if (resultBitSet.isEmpty()) {
					continue;
				}
				bitSet.andNot(lengthBitSet);
				for (int j = resultBitSet.nextSetBit(0); j >= 0; j = resultBitSet
						.nextSetBit(j + 1)) {
					String word = words.get(j);
					Integer count = result.get(word);
					if (count == null) {
						result.put(word, Integer.valueOf(1));
						continue;
					}
					count = Integer.valueOf(count.intValue() + 1);
					result.put(word, count);
				}
			}
		}
	}

	public Map<String, Integer> getResult() {
		return result;
	}

	private Counter createZeroCounter() {
		BitSet bitSet = new BitSet(words.size());
		bitSet.set(0, words.size());

		Counter counter = new Counter();
		counter.setPosition(0);
		counter.setBitSet(bitSet);

		return counter;
	}

	private static class Counter {

		private BitSet bitSet;
		private int position;

		public BitSet getBitSet() {
			return bitSet;
		}

		public void setBitSet(BitSet bitSet) {
			this.bitSet = bitSet;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public void step() {
			position++;
		}
	}
}

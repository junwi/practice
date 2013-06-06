package net.newsmth.junwi.wordcounter;

import java.util.List;
import java.util.Map;

public class TrieWordCounter {

	private TrieNode root;

	public TrieWordCounter(List<String> words) {
		String a = "";
		switch (a) {
			
		}
	}
	
	private static class TrieNode {
		private String word;
		private Map<Character, TrieNode> next;
	}
}

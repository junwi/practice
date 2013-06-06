package net.newsmth.junwi.algorithm.wordcounter;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-1-20
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class ACWordCounter implements WordCounter {
    private Node root = new Node();
    private Multiset<String> result = HashMultiset.create();

    public ACWordCounter(List<String> words) {
        buildTrieTree(words);
        fillFailNodes();
    }

    private void fillFailNodes() {
        root.fail = null;
        Queue<Node> queue = new LinkedList<>();
        for (Node node : root.getChildren()) {
            node.fail = root;
            queue.add(node);
        }
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            for (Node next : node.getChildren()) {
                char c = next.c;
                Node fail = node.fail;
                while (fail != null && fail.getChild(c) == null) {
                    fail = fail.fail;
                }
                if (fail == null) {
                    fail = root;
                } else {
                    fail = fail.getChild(c);
                }
                next.fail = fail;
                queue.add(next);
            }
        }

    }

    private void buildTrieTree(List<String> words) {
        for (String word : words) {
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node node = current.getChild(c);
                if (node == null) {
                    node = new Node();
                    node.c = c;
                    node.father = current;
                    current.addChild(c, node);
                }
                current = node;
            }
            current.word = word;
        }
    }

    @Override
    public void count(String str) {
        Node current = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            while (current != root && current.getChild(c) == null) {
                current = current.fail;
            }
            current = current.getChild(c);
            if (current == null) {
                current = root;
            }
            Node node = current;
            while (node != root) {
                if (node.word != null) {
                    result.add(node.word);
                }
                node = node.fail;
            }
        }
    }

    @Override
    public Multiset<String> getResult() {
        return result;
    }
    
    private void printTree() {
    	printNode(root);
    }
    
    private void printNode(Node node) {
    	System.out.println(node + ":" + node.fail);
    	for (Node child : node.getChildren()) {
    		printNode(child);
    	}
    }

    private static class Node {
        private String word;
        private char c;
        private Node father;
        private Node fail;
        private Map<Character, Node> children = new HashMap<>();

        private Node getChild(char c) {
            return children.get(c);
        }

        private void addChild(char c, Node node) {
            children.put(c, node);
        }

        private Collection<Node> getChildren() {
            return children.values();
        }
        
        public String toString() {
        	if (father == null) {
        		return "root";
        	}
        	return father.toString() + "->" + c;
        }
    }
    
    public static void main(String[] args) {
        String[] words = {"1", "234", "456", "3456789", "57", "06"};
        String str = "11234567890";
        List<String> list = Arrays.asList(words);
        ACWordCounter wordCounter = new ACWordCounter(list);
        wordCounter.printTree();
        wordCounter.count(str);
        System.out.println(wordCounter.getResult());
    }
}

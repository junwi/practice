package test;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

	public static void sort(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int left = start - 1, right = end + 1, middle = array[(start + end) >>> 1];
		while (true) {
			while (array[++left] < middle);
			while (array[--right] > middle);
			if (left >= right) {
				break;
			}
			swap(array, left, right);
		}
		System.out.println(start + " " + end);
		System.out.println(left + " " + right);
		System.out.println(Arrays.toString(array));
		sort(array, start, left - 1);
		sort(array, right + 1, end);
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		int[] array = makeArray1();
		System.out.println(Arrays.toString(array));
		sort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}

	public static int[] makeArray1() {
		int[] array = new int[10];
		Random random = new Random();
		for (int i = 0; i < array.length; ++i) {
			array[i] = random.nextInt(1000);
		}
		return array;
	}
}

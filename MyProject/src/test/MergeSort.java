package test;

import java.util.Arrays;

public class MergeSort {

	public static void sort(int[] array, int start, int end) {
		if (start == end) {
			return;
		}
		int[] temp = new int[end - start + 1];
		System.arraycopy(array, start, temp, 0, temp.length);
		int left = 0;
		int tempEnd = temp.length - 1;
		int mid = tempEnd >>> 1;
		int right = mid + 1;
		sort(temp, left, mid);
		sort(temp, right, tempEnd);
		while (left <= mid && right <= tempEnd) {
			if (temp[left] <= temp[right]) {
				array[start++] = temp[left++];
			} else {
				array[start++] = temp[right++];
			}
		}
		while (left <= mid) {
			array[start++] = temp[left++];
		}
		while (right <= tempEnd) {
			array[start++] = temp[right++];
		}
	}

	public static void main(String[] args) {
		int[] array = {946, 502, 7, 767, 423, 1};
		int[] temp = new int[array.length];
		System.arraycopy(array, 0, temp, 0, array.length);
		sort(array, temp, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}

	private static void sort(int[] array, int[] temp, int from, int to) {
		if (from == to) {
			return;
		}
		System.arraycopy(array, from, temp, from, to - from + 1);
		int middle = (from + to) >>> 1;
		sort(temp, array, from, middle);
		sort(temp, array, middle + 1, to);
		merge(temp, array, from, middle, to);
	}

	private static void merge(int[] src, int[] dest, int from, int middle,
			int to) {
		int left = from;
		int right = middle + 1;
		while (left <= middle && right <= to) {
			if (src[left] <= src[right]) {
				dest[from++] = src[left++];
			} else {
				dest[from++] = src[right++];
			}
		}
		while (left <= middle) {
			dest[from++] = src[left++];
		}
		while (right <= to) {
			dest[from++] = src[right++];
//			System.out.println(Arrays.toString(dest));
		}
	}
}

package test;

public class CountUnfairs {

	public static int count(int[] array, int start, int end) {
		if (end == start) {
			return 0;
		}
		int[] temp = new int[end - start + 1];
		System.arraycopy(array, start, temp, 0, temp.length);
		int tempEnd = temp.length - 1;
		int mid = tempEnd >>> 1;
		int left = 0, right = mid + 1;
		int result = count(temp, left, mid) + count(temp, right, tempEnd);

		while (left <= mid && right <= tempEnd) {
			if (temp[left] <= temp[right]) {
				array[start++] = temp[left++];
			} else {
				result += mid - left + 1;
				array[start++] = temp[right++];
			}
		}
		while (left <= mid) {
			array[start++] = temp[left++];
		}
		while (right <= tempEnd) {
			array[start++] = temp[right++];
		}
		return result;
	}

	public static int countNormal(int[] array, int start, int end) {
		int result = 0;
		for (; start <= end; start++) {
			int value = array[start];
			for (int i = start + 1; i <= end; i++) {
				if (array[i] < value) {
					result++;
				}
			}
		}
		return result;
	}
}

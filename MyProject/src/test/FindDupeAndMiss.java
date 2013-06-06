package test;

public class FindDupeAndMiss {
    
    public static void findDupeAndMiss(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            if (value == i) {
                continue;
            }
            while (value != i && array[value] != value) {
            	int temp = array[value];
            	array[value] = value;
            	array[i] = temp;
            	value = temp;
            }
        }
        for (int i = 0; i < array.length; i++) {
        	if (array[i] != i) {
        		System.out.printf("miss:%d, duplicate:%d.\n", i, array[i]);
        	}
        }
    }
    
    public static void main(String[] args) {
        int[] array = { 0, 1, 2, 3, 3, 4, 5, 6, 7, 9 };
        findDupeAndMiss(array);
    }
}

package ctci.Ch10;

public class Sort {
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /** O(n^2) time, O(1) space */
    public static void bubbleSort(int[] array) {
        for (int n = 0; n < array.length; n++) {
            boolean sorted = true;
            for (int i = 1; i < array.length - n; i++) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    sorted = false;
                }
            }
            if (sorted)
                return;
        }
    }

    /** O(n^2) time, O(1) space */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i ++) {
            int smallestIx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[smallestIx]) {
                    smallestIx = j;
                }
            }
            if (smallestIx != i) {
                swap(array, i, smallestIx);
            }
        }
    }

    /** O(n log(n)) time, O(n) space (helper) */
    public static void mergeSort(int[] array) {
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
    }

    public static void mergeSort(int[] array, int[] helper, int start, int end) {
        if (start < end) {
            int middle = (start + end)/2;
            mergeSort(array, helper, start, middle);
            mergeSort(array, helper, middle + 1, end);
            // merge left and right sorted arrays
            mergeHelper(array, helper, start, middle, end);
        }
    }

    public static void mergeHelper(int[] array, int[] helper, int start, int middle, int end){
        for (int i = start; i <= end; i++) {
            helper[i] = array[i];
        }

        int arrayIx = start;
        int helperLeftIx = start;
        int helperRightIx = middle + 1;

        while (helperLeftIx <= middle && helperRightIx <= end) {
            if (helper[helperLeftIx] <= helper[helperRightIx]) {
                array[arrayIx] = helper[helperLeftIx];
                helperLeftIx++;
            }
            else {
                array[arrayIx] = helper[helperRightIx];
                helperRightIx++;
            }
            arrayIx++;
        }

        // copy over anything left over on the left side. Anything left on the right side will be in place
        while (helperLeftIx <= middle) {
            array[arrayIx] = helper[helperLeftIx];
            helperLeftIx++;
            arrayIx++;
        }
    }

    /** O(n log(n)) time on average, O(n^2) worse case if the pivot is always at the end, O(log(n)) space */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int left, int right) {
        int pivot = partition(array, left, right);
        if (left < pivot - 1)
            quickSort(array, left, pivot - 1);
        if (right > pivot)
            quickSort(array, pivot, right);
    }

    public static int partition(int[] array, int left, int right) {
        int pivot = (left + right) / 2;

        while (left <= right) {
            while (array[left] < array[pivot]) {
                left++;
            }

            while (array[right] > array[pivot]) {
                right--;
            }

            if (left <= right) {
                swap(array, left, right);
                left++;
                right++;
            }
        }

        return left;
    }
}

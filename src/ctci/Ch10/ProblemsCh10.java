package ctci.Ch10;

import CodeLibrary.HashMapList;
import java.util.*;

public class ProblemsCh10 {
    public static void main(String[] args) {
        /** Sorting Algos */
        sort_test();

        /** Problem 10.1 */
        p10_1_test();

        /** Problem 10.2 */
        p10_2_test();

        /** Problem 10.3 */
        p10_3_test();

        /** Problem 10.4 */
        p10_4_test();

        /** Problem 10.5 */
        p10_5_test();

        /** Problem 10.9 */
        p10_9_test();

        /** Problem 10.10 */
        p10_10_test();

        /** Problem 10.11 */
        p10_11_test();
    }

    public static void printIntArray(int[] array) {
        for (int i = 0; i < array.length; i ++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printStrArray(String[] array) {
        for (int i = 0; i < array.length; i ++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void sort_test() {
        System.out.println("Sorting: ");

        int[] array1 = {3, 5, 1, 0, 2, 7, 4, 8, 6, 9};
        Sort.bubbleSort(array1);
        System.out.print("bubbleSort: ");
        printIntArray(array1);

        int[] array2 = {3, 5, 1, 0, 2, 7, 4, 8, 6, 9};
        Sort.selectionSort(array2);
        System.out.print("selectionSort: ");
        printIntArray(array2);

        int[] array3 = {3, 5, 1, 0, 2, 7, 4, 8, 6, 9};
        Sort.mergeSort(array3);
        System.out.print("mergeSort: ");
        printIntArray(array3);

        int[] array4 = {3, 5, 1, 0, 2, 7, 4, 8, 6, 9};
        Sort.quickSort(array4);
        System.out.print("quickSort: ");
        printIntArray(array4);

        System.out.println();
    }

    public static void p10_1(int[] a, int numA, int[] b, int numB) {
        int ix = a.length - 1;
        int aIx = numA - 1;
        int bIx = numB - 1;

        while (bIx >= 0) {
            if (aIx >= 0 && a[aIx] >= b[bIx]) {
                a[ix] = a[aIx];
                aIx--;
            }
            else {
                a[ix] = b[bIx];
                bIx--;
            }
            ix--;
        }
    }

    public static void p10_1_test() {
        System.out.print("p10_1: ");
        int[] a = {0, 1, 2, 3, 5, 7, 0, 0, 0, 0};
        int numA = 6;
        int[] b = {4, 6, 8, 9};
        int numB = 4;

        p10_1(a, numA, b, numB);
        printIntArray(a);
    }

    public static void p10_2(String[] array) {
        HashMapList<String, String> hashMap = new HashMapList<>();

        for (String str: array) {
            String key = sortChars(str);
            hashMap.put(key, str);
        }

        int ix = 0;
        for (String key: hashMap.keySet()) {
            ArrayList<String> strings = hashMap.get(key);
            for (String str: strings) {
                array[ix] = str;
                ix++;
            }
        }
    }

    public static String sortChars(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public static void p10_2_test() {
        System.out.print("p10_2: ");

        String[] strs = {"race", "god", "test", "acre", "care", "dog"};
        p10_2(strs);
        printStrArray(strs);
        System.out.println();
    }

    public static int p10_3(int val, int[] array) {
        return rotatedSearch(val, array, 0, array.length - 1);
    }

    public static int rotatedSearch(int val, int[] array, int left, int right) {
        if (right < left)
            return -1;

        int mid = (left + right) / 2;
        if (array[mid] == val)
            return mid;

        // left normally order
        if (array[mid] > array[left]) {
            if (array[left] <= val && val < array[mid])
                return rotatedSearch(val, array, left, mid - 1);
            else
                return rotatedSearch(val, array, mid + 1, right);
        }
        // right normally ordered
        else if (array[mid] < array[left]){
            if (array[right] >= val && val > array[mid])
                return rotatedSearch(val, array, mid + 1, right);
            else
                return rotatedSearch(val, array, left, mid - 1);
        }
        else if (array[mid] == array[left]) {
            // case where everything on the left is the same
            if (array[mid] != array[right]) {
                return rotatedSearch(val, array, mid + 1, right);
            }
            /* You don't know if you have repeated values on the left or right side
             * {1, 1, 1, 5, 0} or {5, 0, 1, 1, 1}
             */
            else {
                int found = rotatedSearch(val, array, left, mid - 1);
                if (found == -1)
                    return rotatedSearch(val, array, mid + 1, right);
                return found;
            }
        }

        return -1;
    }

    public static void p10_3_test() {
        int[] array1 = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        System.out.print("p10_3: " + p10_3(100, array1) + " ");
        for (int i = 0; i < array1.length; i++) {
            System.out.print(p10_3(array1[i], array1) + " ");
        }
        System.out.println();

        int[] array2 = {15, 15, 15, 9, 13};
        System.out.print("p10_3: " + p10_3(100, array2) + " ");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(p10_3(array2[i], array2) + " ");
        }
        System.out.println();

        int[] array3 = {15, 15, 15, 15, 15, 9, 13, 14, 15};
        System.out.print("p10_3: " + p10_3(100, array3) + " ");
        for (int i = 0; i < array3.length; i++) {
            System.out.print(p10_3(array3[i], array3) + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static class Listy {
        int[] array;

        public Listy(int[] array) {
            this.array = new int[array.length];
            for (int i = 0; i < this.array.length; i++) {
                this.array[i] = array[i];
            }
        }

        public int elementAt(int i) {
            if (i >= 0 && i < array.length)
                return array[i];
            return -1;
        }
    }

    public static int p10_4(Listy array, int val) {
        int end = 1;
        while (array.elementAt(end) != -1 && array.elementAt(end) < val) {
            end *= 2;
        }

        return binarySearchListy(array, val, 0, end);
    }

    public static int binarySearchListy(Listy array, int val, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) / 2;

        if (array.elementAt(mid) == val)
            return mid;

        if (array.elementAt(mid) == -1 || array.elementAt(mid) > val) {
            return binarySearchListy(array, val, start, mid - 1);
        }
        else {
            return binarySearchListy(array, val, mid + 1, end);
        }
    }

    public static void p10_4_test() {
        int[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17};
        Listy listy = new Listy(array);

        System.out.print("p10_4: " + p10_4(listy, 0) + " " +
                p10_4(listy, 4) + " " +
                p10_4(listy, 20) + " ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(p10_4(listy, array[i]) + " ");
        }
        System.out.println();
    }

    public static int p10_5(String[] array, String target) {
        return sparseBinarySearch(array, target, 0, array.length - 1);
    }

    public static int sparseBinarySearch(String[] array, String target, int start, int end) {
        int mid = (start + end) / 2;

        if (array[mid].isEmpty()) {
            int leftMid = mid - 1;
            int rightMid = mid + 1;
            while (true) {
                if (leftMid < start && rightMid > end)
                    return -1;
                else if (leftMid >= start && !array[leftMid].isEmpty()) {
                    mid = leftMid;
                    break;
                }
                else if (rightMid <= end && !array[rightMid].isEmpty()) {
                    mid = rightMid;
                    break;
                }
                leftMid--;
                rightMid++;
            }
        }

        if (array[mid].equals(target))
            return mid;
        else if (array[mid].compareTo(target) > 0)
            return sparseBinarySearch(array, target, start, mid - 1);
        else {
            return sparseBinarySearch(array, target, mid + 1, end);
        }
    }

    public static void p10_5_test() {
        String[] array = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};

        System.out.println("p10_5: " + p10_5(array, "test") +
                " " + p10_5(array, "at") +
                " " + p10_5(array, "ball") +
                " " + p10_5(array, "car") +
                " " + p10_5(array, "dad"));
        System.out.println();

        String[] test = {"3", "30", "34", "5", "9"};
        Arrays.sort(test);
        printStrArray(test);
    }

    public static Coordinate p10_9(int[][] matrix, int val) {
        return findElement(matrix, val, new Coordinate(0, 0), new Coordinate(matrix.length - 1, matrix[0].length - 1));
    }

    public static Coordinate findElement(int[][] matrix, int val, Coordinate origin, Coordinate dest) {
        if (!origin.isInbounds(matrix) || !dest.isInbounds(matrix))
            return null;

        if (origin.getElement(matrix) == val)
            return origin;
        else if (dest.getElement(matrix) == val)
            return dest;
        else if (!origin.isBefore(dest))
            return null;

        //get diagonal (since we can have a rectangular matrix
        int diagDist = Math.min(dest.row - origin.row, dest.col - origin.col);
        Coordinate destDiag = new Coordinate(origin.row + diagDist, origin.col + diagDist);
        Coordinate originDiag = origin.clone();

        //binary search diagonal until you find the first element greater than x (when origin > dest)
        Coordinate midCoor = new Coordinate(0, 0);
        while (originDiag.isBefore(destDiag)) {
            midCoor.setToAvg(originDiag, destDiag);
            if (val <= midCoor.getElement(matrix)) {
                destDiag.row = midCoor.row - 1;
                destDiag.col = midCoor.col - 1;
            }
            else {
                originDiag.row = midCoor.row + 1;
                originDiag.col = midCoor.col + 1;
            }
        }


        // partition and search lower left and upper right
        return partitionAndSearch(matrix, val, origin, dest, originDiag);
    }

    public static Coordinate partitionAndSearch(int[][] matrix, int val, Coordinate origin, Coordinate dest, Coordinate pivot) {
        Coordinate leftLowerOrigin = new Coordinate(pivot.row, origin.col);
        Coordinate leftLowerDest = new Coordinate(dest.row, pivot.col - 1);
        Coordinate rightUpperOrigin = new Coordinate(origin.row, pivot.col);
        Coordinate rightUpperDest = new Coordinate(pivot.row - 1, dest.col);

        Coordinate found = findElement(matrix, val, leftLowerOrigin, leftLowerDest);
        if (found != null)
            return found;
        return findElement(matrix, val, rightUpperOrigin, rightUpperDest);
    }


    public static void p10_9_test() {
        int[][] matrix = {{5, 10, 15, 40},
                {12, 16, 20, 50},
                {16, 25, 30, 60},
                {55, 70, 80, 100}};
        System.out.println("p10_9:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(p10_9(matrix, matrix[i][j]) + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void p10_10_test() {
        System.out.println("p10_10: ");

        RankingBST bst = new RankingBST();
        int[] stream = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        for (int i: stream) {
            bst.insert(i);
        }

        bst.printInOrder();
        System.out.println();

        for (int i = 0; i < 16; i++) {
            System.out.println(i + ": " + bst.getRank(i));
        }
        System.out.println();
    }

    public static void p10_11(int[] array) {
        if (array.length <= 1)
            return;

        for (int i = 1; i < array.length - 1; i+=2) {
            int maxIndex = maxIndex(array, i - 1, i, i + 1);
            if (maxIndex != i) {
                swap(array, i, maxIndex);
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int maxIndex(int[] array, int i, int j, int k) {
        int len = array.length;

        int iVal = (i >= 0 && i < len) ? array[i]:Integer.MIN_VALUE;
        int jVal = (j >= 0 && j < len) ? array[j]:Integer.MIN_VALUE;
        int kVal = (k >= 0 && k < len) ? array[k]:Integer.MIN_VALUE;

        int maxVal = Math.max(iVal, Math.max(jVal, kVal));

        if (maxVal == iVal) return i;
        else if (maxVal == jVal) return j;
        return k;
    }

    public static void p10_11_test() {
        int[] array = {9, 1, 0, 4, 8, 7};
        printIntArray(array);

        p10_11(array);
        printIntArray(array);
        System.out.println();
    }
}

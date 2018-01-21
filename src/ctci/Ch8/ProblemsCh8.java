package ctci.Ch8;

import java.util.*;

public class ProblemsCh8 {
    public static void main(String[] args) {
        /** Fibonacci */
        fibonacci_test();

        /** Problem 8.1 */
        p8_1_test();

        /** Problem 8.2 */
        p8_2_test();

        /** Problem 8.3 */
        p8_3_test();

        /** Problem 8.4 */
        p8_4_test();
    }

    public static int fibonacci_1(int n) {
        if (n == 0)
            return 0;

        int a = 0;
        int b = 1;

        for (int i = 2; i < n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return a + b;
    }

    public static int fibonacci_2(int n) {
        return fibonacci_2(n, new int[n + 1]);
    }

    public static int fibonacci_2(int i, int[] memo) {
        if (i == 0 || i == 1)
            return i;

        if (memo[i] == 0)
            memo[i] = fibonacci_2(i - 1, memo) + fibonacci_2(i - 2, memo);

        return memo[i];
    }

    public static void fibonacci_test() {
        System.out.println("fibonacci_1: " + fibonacci_1(10));
        System.out.println("fibonacci_2: " + fibonacci_2(10));
        System.out.println();
    }

    public static int p8_1(int steps) {
        int[] memo = new int[steps + 1];
        Arrays.fill(memo, -1);
        return countSteps(steps, memo);
    }

    public static int countSteps(int steps, int[] memo) {
        if (steps < 0)
            return 0;
        if (steps == 0)
            return 1;

        if (memo[steps] < 0)
            memo[steps] = countSteps(steps - 1, memo) + countSteps(steps - 2, memo) + countSteps(steps - 3, memo);

        return memo[steps];
    }

    public static void p8_1_test() {
        System.out.println("p8_1: " + p8_1(4));
        System.out.println("p8_1: " + p8_1(5));
        System.out.println();
    }

    public static ArrayList<Point> p8_2(boolean[][] maze) {
        ArrayList<Point> path = new ArrayList();
        HashSet<Point> failedPoints = new HashSet();
        findPath(maze, maze[0].length - 1, maze.length - 1, path, failedPoints);
        return path;
    }

    public static boolean findPath(boolean[][] maze, int x, int y, ArrayList<Point> path, HashSet<Point> failedPoints) {
        if (x < 0 || y < 0 || maze[x][y]) {
            return false;
        }

        Point p = new Point(x, y);

        if (failedPoints.contains(p))
            return false;

        boolean isOrigin = x == 0 && y == 0;

        if (isOrigin || findPath(maze, x - 1, y, path, failedPoints) || findPath(maze, x, y - 1, path, failedPoints)) {
            path.add(p);
            return true;
        }

        failedPoints.add(p);
        return false;
    }

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
        }
    }

    public static void printMaze(boolean[][] maze, ArrayList<Point> path) {
        int numRows = maze.length;
        int numCols = maze[0].length;

        boolean[][] mazePath = new boolean[numRows][numCols];
        for (Point p: path) {
            mazePath[p.x][p.y] = true;
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (mazePath[i][j])
                    System.out.print('P');
                else
                    System.out.print((maze[i][j] ? 'X':'O'));
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static void p8_2_test() {
        System.out.println("p8_2:");
        boolean[][] maze = new boolean[5][5];
        maze[1][0] = true;
        maze[1][2] = true;
        maze[2][4] = true;
        maze[2][0] = true;
        maze[3][2] = true;
        maze[1][4] = true;
        maze[0][3] = true;
        maze[3][4] = true;
        maze[4][2] = true;

        ArrayList<Point> path = p8_2(maze);
        printMaze(maze, path);
        System.out.println();
    }

    /** get magicIndex for distinct, sorted array */
    public static int p8_3_1(int[] array) {
        return getMagicIxDistinct(array, 0, array.length - 1);
    }

    public static int getMagicIxDistinct(int[] array, int start, int end) {
        if (start > end)
            return -1;

        int midIx = (start + end)/2;
        int midVal = array[midIx];

        if (midIx == midVal)
            return midIx;
        else if (midVal < midIx)
            return getMagicIxDistinct(array, midIx + 1, end);
        else
            return getMagicIxDistinct(array, start, midIx - 1);
    }

    public static int p8_3_2(int[] array) {
        return getMagicIx(array, 0, array.length - 1);
    }

    /** get magicIx of array with non-distinct elements. */
    public static int getMagicIx(int[] array, int start, int end) {
        if (start > end)
            return -1;

        int midIx = (start + end)/2;
        int midVal = array[midIx];

        if (midIx == midVal)
            return midIx;

        int leftIx = Math.min(midVal, midIx - 1);
        int left = getMagicIx(array, start, leftIx);
        if (left > 0)
            return left;

        int rightIx = Math.max(midVal, midIx + 1);
        int right = getMagicIx(array, rightIx, end);
        return right;
    }

    public static void p8_3_test() {
        int[] array0 = {-100, -7, 0, 1, 2, 6, 8, 11, 12};
        int[] array1 = {-100, -7, 0, 1, 2, 5, 8, 11, 12};
        int[] array2 = {1, 3, 5, 5, 5, 5, 8, 11, 12};

        System.out.println("p8_3_1: " + p8_3_1(array0));
        System.out.println("p8_3_1: " + p8_3_1(array1));
        System.out.println("p8_3_1: " + p8_3_1(array2));

        System.out.println("p8_3_2: " + p8_3_2(array0));
        System.out.println("p8_3_2: " + p8_3_2(array1));
        System.out.println("p8_3_2: " + p8_3_2(array2));

        System.out.println();
    }

    public static ArrayList<ArrayList<Integer>> p8_4_1(ArrayList<Integer> nums) {
        return getSubsets(nums, 0);
    }

    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> nums, int ix) {
        ArrayList<ArrayList<Integer>> subsets = new ArrayList();

        // base case
        if (ix == nums.size()) {
            subsets.add(new ArrayList<Integer>());
            return subsets;
        }

        int newNum = nums.get(ix);
        subsets = getSubsets(nums, ix + 1);

        ArrayList<ArrayList<Integer>> newSubsets = new ArrayList();
        for (ArrayList<Integer> set: subsets) {
            ArrayList<Integer> newSubset = new ArrayList();
            newSubset.addAll(set);
            newSubset.add(newNum);
            newSubsets.add(newSubset);
        }

        subsets.addAll(newSubsets);
        return subsets;
    }

    public static ArrayList<ArrayList<Integer>> p8_4_2(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> subsets = new ArrayList();
        int max = 1 << nums.size();

        for (int i = 0; i < max; i ++) {
            subsets.add(convertIntToSet(nums, i));
        }

        return subsets;
    }

    public static ArrayList<Integer> convertIntToSet(ArrayList<Integer> set, int num) {
        ArrayList<Integer> subset = new ArrayList();
        int ix = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                subset.add(set.get(ix));
            }
            ix++;
            num >>= 1;
        }
        return subset;
    }

    public static void printSets(ArrayList<ArrayList<Integer>> sets) {
        for (int i = 0; i < sets.size(); i++) {
            System.out.print(sets.get(i));
            if (i % 4 == 3)
                System.out.println();
        }
    }

    public static void p8_4_test() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println("p8_4_1:");
        ArrayList<ArrayList<Integer>> subsets1 = p8_4_1(nums);
        printSets(subsets1);

        System.out.println("p8_4_2:");
        ArrayList<ArrayList<Integer>> subsets2 = p8_4_2(nums);
        printSets(subsets2);
    }
}

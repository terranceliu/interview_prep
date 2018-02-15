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

        /** Problem 8.5 */
        p8_5_test();

        /** Problem 8.6 */
        p8_6_test();

        /** Problem 8.7 */
        p8_7_test();

        /** Problem 8.8 */
        p8_8_test();

        /** Problem 8.9 */
        p8_9_test();

        /** Problem 8.10 */
        p8_10_test();

        /** Problem 8.11 */
        p8_11_test();

        /** Problem 8.12 */
        p8_12_test();

        /** Problem 8.13 */
        p8_13_test();

        /** Problem 8.14 */
        p8_14_test();
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

    public static int p8_5(int a, int b) {
        int smaller = a < b ? a:b;
        int bigger = a< b ? b:a;
        return productHelper(smaller, bigger);
    }

    public static int productHelper(int smaller, int bigger) {
        if (smaller == 0)
            return 0;
        if (smaller == 1)
            return bigger;

        // divide smaller by 2
        int halfProd = productHelper((smaller >> 1), bigger);

        if (smaller % 2 == 0)
            return halfProd + halfProd;
        else
            return halfProd + halfProd + bigger;
    }

    public static void p8_5_test() {
        System.out.println("p8_5:" + p8_5(12, 3));
        System.out.println("p8_5:" + p8_5(15, 4));
        System.out.println("p8_5:" + p8_5(6, 3));
        System.out.println("p8_5:" + p8_5(10, 10));
        System.out.println();
    }

    public static void p8_6_test() {
        System.out.println("p8_6:");
        Tower t0 = new Tower(0);
        Tower t1 = new Tower(1);
        Tower t2 = new Tower(2);

        int[] list = {3, 2, 1};
        for (int i = 0; i < list.length; i++) {
            t0.add(list[i]);
        }

        t0.moveDisks(list.length, t2, t1, t0, t1, t2);
    }

    public static ArrayList<String> p8_7_1(String str) {
        return getStrPermutations1(str, 0);
    }

    public static ArrayList<String> getStrPermutations1(String str, int i) {
        ArrayList<String> results = new ArrayList();
        if (i == str.length()) {
            results.add("");
            return results;
        }

        ArrayList<String> foundPermutations = getStrPermutations1(str, i + 1);
        for (String permutation: foundPermutations) {
            ArrayList<String> newPermutations = getStrPermutations1(permutation, str.charAt(i));
            results.addAll(newPermutations);
        }

        return results;
    }

    public static ArrayList<String> getStrPermutations1(String str, char c) {
        ArrayList<String> permutations = new ArrayList();

        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i <= str.length(); i++) {
            sb.insert(i, c);
            permutations.add(sb.toString());
            sb.deleteCharAt(i);
        }

        return permutations;
    }



    public static ArrayList<String> p8_7_2(String str) {
        ArrayList<String> results = new ArrayList();
        if (str.length() == 0) {
            results.add("");
            return results;
        }

        for (int i = 0; i < str.length(); i++) {
            String part1 = str.substring(0, i);
            String part2 = str.substring(i + 1);
            ArrayList<String> newPermutations = p8_7_2(part1 + part2);

            for (String permutation: newPermutations) {
                results.add(str.charAt(i) + permutation);
            }
        }

        return results;
    }

    public static ArrayList<String> p8_7_3(String str) {
        ArrayList<String> results = new ArrayList();
        getPermutations3("", str, results);
        return results;
    }

    public static void getPermutations3(String prefix, String remainder, ArrayList<String> results) {
        if (remainder.length() == 0)
            results.add(prefix);

        for (int i = 0; i < remainder.length(); i++) {
            String part1 = remainder.substring(0, i);
            String part2 = remainder.substring(i + 1);
            char c = remainder.charAt(i);
            getPermutations3(prefix + c, part1 + part2, results);
        }
    }

    public static void p8_7_test() {
        System.out.println("p8_7_1:" + p8_7_1("123"));
        System.out.println("p8_7_2:" + p8_7_2("123"));
        System.out.println("p8_7_3:" + p8_7_3("123"));
        System.out.println();
    }

    /** non unique characters */
    public static ArrayList<String> p8_8(String str) {
        ArrayList<String> results = new ArrayList();

        HashMap<Character, Integer> charFreq = new HashMap();
        for (char c: str.toCharArray()) {
            if (!charFreq.containsKey(c)) {
                charFreq.put(c, 0);
            }
            charFreq.put(c, charFreq.get(c) + 1);
        }

        getPermutationsNU(charFreq, "", str.length(), results);

        return results;
    }

    public static void getPermutationsNU(HashMap<Character, Integer> charFreq,
                                         String prefix, int remaining, ArrayList<String> results) {
        if (remaining == 0) {
            results.add(prefix);
            return;
        }

        for (char c: charFreq.keySet()) {
            int count = charFreq.get(c);
            if (count > 0) {
                charFreq.put(c, count - 1);
                getPermutationsNU(charFreq, prefix + c, remaining - 1, results);
                charFreq.put(c, count);
            }
        }
    }

    public static void p8_8_test() {
        System.out.println("p8_8:" + p8_8("123"));
        System.out.println("p8_8:" + p8_8("122"));
        System.out.println("p8_8:" + p8_8("111"));
        System.out.println();
    }

    public static ArrayList<String> p8_9(int n) {
        ArrayList<String> results = new ArrayList();
        char[] str = new char[n * 2];
        getParens(results, 0, n, n, str);
        return results;
    }

    public static void getParens(ArrayList<String> results, int index, int numLeft, int numRight, char[] str) {
        // invalid
        if (numLeft < 0|| numLeft > numRight)
            return;

        if (numLeft == 0 && numRight == 0)
            results.add(String.copyValueOf(str));
        else {
            str[index] = '(';
            getParens(results, index + 1, numLeft - 1, numRight, str);

            str[index] = ')';
            getParens(results, index + 1, numLeft, numRight - 1, str);
        }
    }

    public static void p8_9_test() {
        System.out.println("p8_9:" + p8_9(0));
        System.out.println("p8_9:" + p8_9(1));
        System.out.println("p8_9:" + p8_9(2));
        System.out.println("p8_9:" + p8_9(3));
        System.out.println();
    }

    public static void p8_10(char[][] screen, int row, int col, char newColor) {
        char oldColor = screen[row][col];
        if (oldColor != newColor) {
            paintFill(screen, row, col, newColor, oldColor);
        }
    }

    public static void paintFill(char[][] screen, int row, int col, char newColor, char oldColor) {
        // stop if you hit edges of the screen
        if (row < 0 || row >= screen.length || col < 0 || col >= screen[0].length) {
            return;
        }

        if (screen[row][col] == oldColor) {
            screen[row][col] = newColor;
            paintFill(screen, row - 1, col, newColor, oldColor);
            paintFill(screen, row + 1, col, newColor, oldColor);
            paintFill(screen, row, col - 1, newColor, oldColor);
            paintFill(screen, row, col + 1, newColor, oldColor);
        }
    }

    public static void printScreen(char[][] screen) {
        int numRows = screen.length;
        int numCols = screen[0].length;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(screen[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void p8_10_test() {
        System.out.println("p8_10:");
        char[][] screen = {{'O', 'O', 'R', 'O', 'R'},
                           {'O', 'R', 'R', 'R', 'O'},
                           {'R', 'R', 'R', 'O', 'R'},
                           {'O', 'O', 'R', 'R', 'R'}};
        printScreen(screen);
        System.out.println();

        p8_10(screen, 1, 1, 'R');
        printScreen(screen);
        System.out.println();

        p8_10(screen, 1, 1, 'X');
        printScreen(screen);
        System.out.println();
    }

    public static int p8_11(int amount) {
        /**
         * Our code depend on pennies being at the end, because we assume that when we reach the last denomIx,
         * there is exactly one possibility. If you did not end with pennies, then you have to make sure
         * that you can create the amount with the last denomination (for example, you can't make 7 cents with just nickels)
         *
         * Going in descending order is more efficient.
         * if we had amount = 100:
         * starting with quarters would only produce 4 + 1 branches
         * starting with nickels would produce 20 + 1 branches
         *
         */

        int[] denoms = {25, 10, 5, 1};
        int[][] memo = new int[amount + 1][denoms.length];
        return makeChange(denoms, amount, 0, memo);
    }

    public static int makeChange(int[] denoms, int amount, int denomIx, int[][] memo) {
        /**
         * suppose we already counted the ways to make 20 cents with dimes (+ smaller denominations),
         * then we just retrieve this value from the memo table.
         * memo[20][1] means ways to get 20 cents when we only have denomination #1 left (dimes)
         */
        if (memo[amount][denomIx] > 0)
            return memo[amount][denomIx];

        // when left with just pennies, there is just 1 way to make change
        if (denomIx == denoms.length - 1)
            return 1;

        int ways = 0;
        int denomAmt = denoms[denomIx];
        for (int i = 0; i * denomAmt <= amount; i++) {
            int newAmt = amount - i * denomAmt;
            ways += makeChange(denoms, newAmt, denomIx + 1, memo);
        }
        memo[amount][denomIx] = ways;
        return ways;
    }

    public static void p8_11_test() {
        System.out.println("p8_11: " + p8_11(15));
        System.out.println();
    }


    public static ArrayList<int[]> p8_12() {
        ArrayList<int[]> results = new ArrayList();
        int[] columns = new int[8];
        placeQueens(0, columns, results);
        return results;
    }

    // iterate through each row and pick a column to put the queen in
    public static void placeQueens(int row, int[] columns, ArrayList<int[]> results) {
        int GRID_SIZE = columns.length;

        // if we reach the last row, then we're done
        if (row == GRID_SIZE)
            results.add(columns.clone());
        else {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (checkValid(columns, row, col)) {
                    columns[row] = col;
                    placeQueens(row + 1, columns, results);
                }
            }
        }

    }

    public static boolean checkValid(int[] columns, int row, int col) {
        for (int rowCheck = 0; rowCheck < row; rowCheck++) {
            int colCheck = columns[rowCheck];

            // 1) check same row: not needed since we're adding one row at a time

            // 2) check same column
            if (col == colCheck)
                return false;

            // 3) check diagonal: this means horizontal and vertical coordinate diff are the same
            int colDistance = Math.abs(col - colCheck);
            int rowDistance = row - rowCheck; // don't need abs since row > rowCheck

            if (colDistance == rowDistance)
                return false;
        }

        return true;
    }

    public static void p8_12_test() {
        ArrayList<int[]> results = p8_12();
        for (int[] result: results) {
            for (int row = 0; row < result.length; row++) {
                for (int col = 0; col < result.length; col++) {
                    if (col == result[row])
                        System.out.print("X");
                    else
                        System.out.print("_");
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static int p8_13_1(ArrayList<Box> boxes) {
        int[] memo = new int[boxes.size()];

        /**
         * sort the boxes by one dimension to guarantee that you don't need to check previous boxes
         */
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        // test the height of every stack where the bottom is box i
        for (int i = 0; i < boxes.size(); i++) {
            int checkHeight = createStack1(boxes, i, memo);
            maxHeight = Math.max(maxHeight, checkHeight);
        }

        return maxHeight;
    }

    /** get max height given that the bottom is box #bottomIndex */
    public static int createStack1(ArrayList<Box> boxes, int bottomIndex, int[] memo) {
        if(memo[bottomIndex] > 0)
            return memo[bottomIndex];

        Box bottomBox = boxes.get(bottomIndex);

        // get best stack with subsequent boxes
        int maxHeight = 0;
        for (int i = bottomIndex+1; i < boxes.size(); i++) {
            Box checkBox = boxes.get(i);
            if (checkBox.canBeAbove(bottomBox)) {
                int checkHeight = createStack1(boxes, i, memo);
                maxHeight = Math.max(maxHeight, checkHeight);
            }
        }

        // add height of current box
        maxHeight += bottomBox.h;
        memo[bottomIndex] = maxHeight;
        return maxHeight;
    }

    public static class BoxComparator implements Comparator<Box> {
        @Override
        public int compare(Box x, Box y) {
            return y.h - x.h;
        }
    }

    public static int p8_13_2(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        int[] memo = new int[boxes.size()];
        return createStack2(boxes, 0 , null, memo);
    }

    public static int createStack2(ArrayList<Box> boxes, int index, Box bottom, int[] memo) {
        if (index >= boxes.size())
            return 0;

        Box newBottom = boxes.get(index);
        int heightNewBottom = 0;
        if (bottom == null || newBottom.canBeAbove(bottom)) {
            // check if we've got the max height for the new bottom before
            if (memo[index] == 0) {
                memo[index] += newBottom.h;
                memo[index] += createStack2(boxes, index + 1, newBottom, memo);
            }
            heightNewBottom = memo[index];
        }

        int heightOrigBottom = createStack2(boxes, index + 1, bottom, memo);

        return Math.max(heightNewBottom, heightOrigBottom);
    }
    
    public static void p8_13_test() {
        ArrayList<Box> boxes = new ArrayList();
        boxes.add(new Box(3, 3, 6));
        boxes.add(new Box(5, 9, 8));
        boxes.add(new Box(10, 6, 10));
        boxes.add(new Box(4, 3, 7));
        boxes.add(new Box(1, 1, 1));

        System.out.println("p8_13_1: " + p8_13_1(boxes));
        System.out.println("p8_13_2: " + p8_13_2(boxes));
        System.out.println();
    }


    public static int p8_14(String str, boolean result) {
        HashMap<String, int[]> memo = new HashMap();

        int[] counts = countBoolEvals(str, memo);
        if (result)
            return counts[1];
        else
            return counts[0];
    }

    public static int[] countBoolEvals(String str, HashMap<String, int[]> memo) {
        int[] counts = new int[2];
        if (str.length() == 0)
            return counts;
        if (str.length() == 1) {
            if (parseBoolChar(str.charAt(0)))
                counts[1]++;
            else
                counts[0]++;
        }

        int numTrue = 0;
        int numFalse = 0;

        for (int i = 1; i < str.length(); i += 2) {
            String leftStr = str.substring(0, i);
            if (!memo.containsKey(leftStr))
                memo.put(leftStr, countBoolEvals(leftStr, memo));
            int[] left = memo.get(leftStr);

            String rightStr = str.substring(i + 1);
            if (!memo.containsKey(rightStr))
                memo.put(rightStr, countBoolEvals(rightStr, memo));
            int[] right = memo.get(rightStr);

            int numBothFalse = left[0] * right[0];
            int numBothTrue = left[1] * right[1];
            int numOneOfEach = left[0] * right[1] + left[1] * right[0];

            char operator = str.charAt(i);
            if (operator == '&') {
                numTrue += numBothTrue;
                numFalse += numBothFalse + numOneOfEach;
            }
            else if (operator == '|') {
                numTrue += numBothTrue + numOneOfEach;
                numFalse += numBothFalse;
            }
            else if (operator == '^') {
                numTrue += numOneOfEach;
                numFalse += numBothTrue + numBothFalse;
            }
        }

        counts[0] += numFalse;
        counts[1] += numTrue;

        return counts;
    }

    public static boolean parseBoolChar(char c) {
        return c == '0' ? false:true;
    }

    public static void p8_14_test() {
        System.out.println("p8_14:" + p8_14("1", true));
        System.out.println("p8_14:" + p8_14("1", false));
        System.out.println("p8_14:" + p8_14("0", true));
        System.out.println("p8_14:" + p8_14("0", false));

        System.out.println("p8_14:" + p8_14("1^0|0|1", false));
        System.out.println("p8_14:" + p8_14("0&0&0&1^1|0", true));
        System.out.println();
    }
}

package ctci.Ch1;

public class ProblemsCh1 {
    public static void main (String[] args) {
        System.out.println("Strings and Arrays\n");

        /** Problem 1.1 */
        System.out.println("p1_1: " + p1_1("abcde123"));
        System.out.println("p1_1: " + p1_1("abcde123a45"));
        System.out.println("");

        /** Problem 1.2 */
        System.out.println("p1_2_1: " + p1_2_1("abcd", "acdb"));
        System.out.println("p1_2_1: " + p1_2_1("abcd", "acdba"));
        System.out.println("p1_2_1: " + p1_2_1("abcd", "acde"));
        System.out.println("p1_2_2: " + p1_2_2("abcd", "acdb"));
        System.out.println("p1_2_2: " + p1_2_2("abcd", "acdba"));
        System.out.println("p1_2_2: " + p1_2_2("abcd", "acde"));

        /** Problem 1.3 */
        char[] urlify = "Mr John Smith           ".toCharArray();
        p1_3(urlify, 13);
        System.out.println("p1_3: " + new String(urlify));
        System.out.println("");

        /** Problem 1.4 */
        System.out.println("p1_4_1: " + p1_4_1("Taco Cat"));
        System.out.println("p1_4_1: " + p1_4_1("RACECAR"));
        System.out.println("p1_4_1: " + p1_4_1("Taco"));
        System.out.println("p1_4_2: " + p1_4_2("Taco Cat"));
        System.out.println("p1_4_2: " + p1_4_2("RACECAR"));
        System.out.println("p1_4_2: " + p1_4_2("Taco"));
        System.out.println("");

        /** Problem 1.5 */
        System.out.println("p1_5: " + p1_5("pale", "ple"));
        System.out.println("p1_5: " + p1_5("pales", "pale"));
        System.out.println("p1_5: " + p1_5("pale", "bale"));
        System.out.println("p1_5: " + p1_5("pale", "bake"));
        System.out.println("p1_5: " + p1_5("pale", "ple"));
        System.out.println("p1_5: " + p1_5("pales", "pale"));
        System.out.println("p1_5: " + p1_5("pale", "bale"));
        System.out.println("p1_5: " + p1_5("pale", "bake"));
        System.out.println("");

        /** Problem 1.6 */
        System.out.println("p1_6: " + p1_6(""));
        System.out.println("p1_6: " + p1_6("a"));
        System.out.println("p1_6: " + p1_6("aa"));
        System.out.println("p1_6: " + p1_6("abc"));
        System.out.println("p1_6: " + p1_6("aabcccccaaa"));
        System.out.println("");

        /** Problem 1.7 */
        System.out.println("p1_7: 4x4");
        int[][] matrix = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        p1_7(matrix);
        System.out.println("p1_7: 5x5");
        matrix = new int[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        p1_7(matrix);
        System.out.println();

        /** Problem 1.8 */
        System.out.println("p1_8: 4x4");
        matrix = new int[][] {
                {1, 0, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 0, 12},
                {13, 14, 15, 16}
        };
        p1_8(matrix);
        System.out.println();

        /** Problem 1.9 */
        System.out.println("p1_9: " + p1_9("waterbottle", "erbottlewat"));
        System.out.println("p1_9: " + p1_9("waterbottle", "erbottlewaa"));
        System.out.println("p1_9: " + p1_9("waterbottle", "erbottlewa"));
    }

    /** Check if a string has unique characters Assume ASCII (128 chars) */
    public static boolean p1_1(String str) {
        if (str.length() > 128)
            return false;

        boolean[] charsUsed = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int ix = str.charAt(i);
            if (charsUsed[ix])
                return false;
            charsUsed[ix] = true;
        }

        return true;
    }

    /** Check if one string is a permutation of another. Assume ASCII (128 chars) */
    public static boolean p1_2_1(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        int[] charCounter = new int[128];

        for (int i = 0; i < str1.length(); i++) {
            int ix1 = str1.charAt(i);
            charCounter[ix1]++;

            int ix2 = str2.charAt(i);
            charCounter[ix2]--;
        }

        for (int i = 0; i < charCounter.length; i++) {
            if (charCounter[i] != 0)
                return false;
        }

        return true;
    }

    public static boolean p1_2_2(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        return sort(str1).equals(sort(str2));
    }

    public static String sort(String str) {
        char[] content = str.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    /** replace spaces with '%20' */
    public static void p1_3(char[] str, int trueLength) {
        int newLength = trueLength;
        for (int i = 0; i < trueLength; i++) {
            if (str[i] == ' ')
                newLength += 2;
        }
        int ix = newLength - 1;

        for (int i = trueLength - 1; i >= 0; i--) {
            char c = str[i];
            if (c == ' ') {
                str[ix--] = '0';
                str[ix--] = '2';
                str[ix--] = '%';
            }
            else
                str[ix--] = c;
        }
    }

    /** Check if the string is permutation of a palindrome */
    public static boolean p1_4_1(String str) {
        int numAlpha = Character.getNumericValue('z') - Character.getNumericValue('a') + 1;
        boolean[] oddNumChar = new boolean[numAlpha];

        char[] charArray = str.toCharArray();
        for (char c: charArray) {
            int ix = getCharNum(c);
            if (ix != -1)
                oddNumChar[ix] ^= true;
        }

        int numOdd = 0;
        for (int i = 0; i< oddNumChar.length; i++) {
            if (oddNumChar[i]) {
                if (numOdd > 0)
                    return false;
                numOdd++;
            }
        }
        return true;
    }

    /** processes characters to index */
    public static int getCharNum(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('c');
        int caseConversion = a - Character.getNumericValue('A');

        int val = Character.getNumericValue(c);
        if (val < a) /** converts from upper to lower case */
            val += caseConversion;

        if (a <= val && val <= z)
            return val - a; /** convert to index */
        return -1; /** if char is invalid */
    }

    /** bit array solution */
    public static boolean p1_4_2(String str) {
        int bitVector = createBitVector(str);
        return bitVector == 0 || checkForOneBit(bitVector);
    }

    public static int createBitVector(String str) {
        int bitVector = 0;
        for (char c: str.toCharArray()) {
            int bitIndex = getCharNum(c);
            bitVector = toggle(bitVector, bitIndex);
        }
        return bitVector;
    }

    /** toggles each bit */
    public static int toggle(int bitVector, int bitIndex) {
        /** from getCharNum, bitIndex < 1 means it is an invalid char */
        if (bitIndex < 0)
            return bitVector;

        /** 
         * Shifts 1 over 'bitIndex' times. 8-bit ex:
         * 1 << 5 = 00000001 << 5 = 00100000
         */
        int mask = 1 << bitIndex;
        /** if the bitVector is set to 0 at bitIndex */
        if ((bitVector & mask) == 0) {
            /** set to 1 at bitIndex */
            bitVector |= mask;
        }
        else {
            /** 
             * set to 0 at bitIndex
             * ~mask = 11011111
             * */
            bitVector &= ~mask;
        }

        return bitVector;
    }

    /**
     * if there is only one bit set to 1:
     * bitVector = 0010000
     * bitVector - 1 = 0001111
     * bitVector and (bitVector - 1) will have no overlapping bits set to 1, so
     * bitVector & (bitVector - 1) = 0
     *
     * if there are multiple, we have
     * 0010100 & 0010011 != 0
     */
    public static boolean checkForOneBit(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    
    public static boolean p1_5(String str1, String str2) {
        if (Math.abs(str1.length() - str2.length()) > 1)
            return false;
        else if (str1.length() == str2.length()) {
            return oneAway(str1, str2);
        }
        else {
            if (str1.length() > str2.length())
                return oneAwayInsert(str1, str2);
            else
                return oneAwayInsert(str2, str1);
        }
    }

    public static boolean oneAway(String str1, String str2) {
        int numDifferences = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (numDifferences == 1)
                    return false;
                numDifferences += 1;
            }
        }
        return true;
    }

    public static boolean oneAwayInsert(String longer, String shorter) {
        int ixLonger = 0;
        int ixShorter = 0;
        while (ixLonger < longer.length() && ixShorter < shorter.length()) {
            if (longer.charAt(ixLonger) == shorter.charAt(ixShorter)) {
                ixLonger++;
                ixShorter++;
            }
            else {
                if (ixLonger != ixShorter)
                    return false;
                ixLonger++;
            }
        }
        return true;
    }


    public static String p1_6(String str) {
        if (str.length() <= 1)
            return str;

        StringBuilder result = new StringBuilder();
        int ix1 = 0;
        int ix2 = 1;

        while (ix2 < str.length()) {
            if (str.charAt(ix1) != str.charAt(ix2)) {
                int numRepeating = ix2 - ix1;
                result.append(str.charAt(ix1)).append(String.valueOf(numRepeating));
                ix1 = ix2;
            }
            ix2++;
        }
        if (ix2 == str.length()) {
            int numRepeating = ix2 - ix1;
            result.append(str.charAt(ix1)).append(String.valueOf(numRepeating));
        }

        return result.toString();
    }

    public static void p1_7(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        System.out.println("Original:");
        printMatrix(matrix);
        if (numRows == numCols)
            rotateHelper(matrix, 0, 0, numRows - 1, numCols - 1);
        System.out.println("Rotated:");
        printMatrix(matrix);
    }

    public static void rotateHelper(int[][] matrix, int ixRowUpperLeft, int ixColUpperLeft, int ixRowLowerRight, int ixColLowerRight) {
        if (ixRowUpperLeft >= ixRowLowerRight)
            return;

        for (int i = ixRowUpperLeft; i < ixRowLowerRight; i++) {
            int temp = matrix[ixRowUpperLeft + i][ixColLowerRight];
            matrix[ixRowUpperLeft + i][ixColLowerRight] = matrix[ixRowUpperLeft][ixColUpperLeft + i];
            matrix[ixRowUpperLeft][ixColUpperLeft + i] = matrix[ixRowLowerRight - i][ixColUpperLeft];
            matrix[ixRowLowerRight - i][ixColUpperLeft] = matrix[ixRowLowerRight][ixColLowerRight - i];
            matrix[ixRowLowerRight][ixColLowerRight - i] = temp;
        }

        rotateHelper(matrix, ixRowUpperLeft + 1, ixColUpperLeft + 1, ixRowLowerRight - 1, ixColLowerRight - 1);
    }

    public static void printMatrix(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }


    public static void p1_8(int[][] matrix) {
        System.out.println("Before:");
        printMatrix(matrix);
        int numCols = matrix[0].length;
        int numRows = matrix.length;

        boolean firstRowHasZero = checkFirstRow(matrix);
        boolean firstColHasZero = checkFirstCol(matrix);

        for (int row = 1; row < numRows; row++) {
            for (int col = 1; col < numCols; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int row = 1; row < numRows; row++) {
            if (matrix[row][0] == 0)
                nullifyRow(matrix, row);
        }
        for (int col = 1; col < numRows; col++) {
            if (matrix[0][col] == 0)
                nullifyCol(matrix, col);
        }

        if (firstRowHasZero)
            nullifyRow(matrix, 0);
        if (firstColHasZero)
            nullifyCol(matrix, 0);

        System.out.println("After:");
        printMatrix(matrix);
    }

    public static boolean checkFirstRow(int[][] matrix) {
        int numCols = matrix[0].length;
        for (int col = 0; col < numCols; col++) {
            if (matrix[0][col] == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkFirstCol(int[][] matrix) {
        int numRows = matrix.length;
        for (int row = 0; row < numRows; row++) {
            if (matrix[row][0] == 0) {
                return true;
            }
        }
        return false;
    }

    public static void nullifyRow(int[][] matrix, int row) {
        int numCols = matrix[0].length;
        for (int col = 0; col < numCols; col++) {
            matrix[row][col] = 0;
        }
    }

    public static void nullifyCol(int[][] matrix, int col) {
        int numRows = matrix.length;
        for (int row = 0; row < numRows; row++) {
            matrix[row][col] = 0;
        }
    }


    public static boolean p1_9(String str1, String str2) {
        if (str1.length() > 0 && str1.length()==str2.length()) {
            String str1_2 = str2 + str2;
            return str1_2.contains(str1);
        }
        return false;
    }
}

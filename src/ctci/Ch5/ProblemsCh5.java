package ctci.Ch5;

import ctci.Ch5.BitOperations.*;

public class ProblemsCh5 {
    public static void main(String[] args) {
        /** Problem 5.1 */
        p5_1_test();

        /** Problem 5.2 */
        p5_2_test();

        /** Problem 5.3 */
        p5_3_test();

        /** Problem 5.4 */
        p5_4_test();

        /** Problem 5.6 */
        p5_6_test();

        /** Problem 5.7 */
        p5_7_test();
    }

    public static int p5_1(int n, int m, int i, int j) {
        // first clear bits i through j
        int mask = 1 << (j - i + 2);
        mask -= 1;
        mask <<= i;
        mask = ~mask;
        int result = n & mask;

        // shift m over and add it
        result += (m << i);

        return result;
    }

    public static void p5_1_test() {
        int result = p5_1(BitOperations.setBit(0, 10), BitOperations.bitsToInt("10011"), 2, 6);
        System.out.println("p5_1: " + Integer.toBinaryString(result));
        System.out.println();
    }

    public static String p5_2_1(double num) {
        if (num >= 1 || num <= 0)
            return "Error";

        StringBuilder sb = new StringBuilder();
        sb.append(".");
        while (num > 0) {
            if (sb.length() > 32)
                return "Error";

            double r = num*2;
            if (r >= 1) {
                sb.append("1");
                num = r - 1;
            }
            else {
                sb.append("0");
                num = r;
            }
        }

        return sb.toString();
    }

    public static void p5_2_test() {
        System.out.println("p5_2_1: " + p5_2_1(0.7234));
        System.out.println();
    }

    public static int p5_3(int num) {
        int currLength = 0;
        int prevLength = 0;
        int maxLength = 0;

        while (num > 0) {
            // check rightmost bit
            if ((num & 1) == 1)
                currLength++;
            else {
                prevLength = ((num & 2) == 0) ? 0:currLength;
                currLength = 0;
            }

            maxLength = Math.max(maxLength, currLength + prevLength + 1);
            num >>>= 1;
        }

        return maxLength;
    }

    public static void p5_3_test() {
        System.out.println("p5_3: " + p5_3(BitOperations.bitsToInt("111011001111")));
        System.out.println();
    }

    public static int p5_4_1(int num) {
        int numNext = getNext(num);
        int numPrev = getPrev(num);

        System.out.println(Integer.toBinaryString(num) + ": " + num);
        System.out.println(Integer.toBinaryString(numNext) + ": " + numNext);
        System.out.println(Integer.toBinaryString(numPrev) + ": " + numPrev);

        return (numNext - num < num - numPrev) ? numNext:numPrev;
    }

    public static int getNext(int num) {
        int numCopy = num;

        // c0 = number of trailing zeros
        int c0 = 0;
        while ((numCopy & 1) == 0 && (numCopy != 0)) {
            numCopy >>= 1;
            c0++;
        }

        // c1 = number of consecutive 1s after trailing zeros
        int c1 = 0;
        while ((numCopy & 1) != 0) {
            numCopy >>= 1;
            c1++;
        }

        // if you have 11...100...0 or 00000000, then there is no greater number with the same number of 1s
        if (c0 + c1 == 31 || c0 + c1 == 0)
            return -1;

        int p = c0 + c1;
        int mask = (1 << p);

        //set p from 0 to 1
        num |= mask;

        //clear all bits before p
        mask -= 1;
        mask = ~mask;
        num &= mask;

        //fill with rightmost (c1 - 1) 1s
        mask = (1 << (c1 - 1)) - 1;
        num |= mask;

        return num;
    }

    public static int getPrev(int num) {
        int numCopy = num;

        int c1 = 0;
        while ((numCopy & 1) != 0) {
            numCopy >>= 1;
            c1++;
        }

        int c0 = 0;
        while ((numCopy & 1) == 0 && (numCopy != 0)) {
            numCopy >>= 1;
            c0++;
        }

        int p = c1 + c0;
        //clear right most p bits
        int mask = (1 << (p + 1));
        mask -= 1;
        mask = ~mask;
        num &= mask;

        // get (c1 + 1) 1s
        mask = (1 << (c1 + 1)) - 1;
        mask <<= (c0 - 1);
        num |= mask;

        return num;
    }

    public static int p5_4_2(int num) {
        int numNext = getNextArith(num);
        int numPrev = getPrevArith(num);

        System.out.println(Integer.toBinaryString(num) + ": " + num);
        System.out.println(Integer.toBinaryString(numNext) + ": " + numNext);
        System.out.println(Integer.toBinaryString(numPrev) + ": " + numPrev);

        return (numNext - num < num - numPrev) ? numNext:numPrev;
    }

    public static int getNextArith(int num) {
        int numCopy = num;

        // c0 = number of trailing zeros
        int c0 = 0;
        while ((numCopy & 1) == 0 && (numCopy != 0)) {
            numCopy >>= 1;
            c0++;
        }

        // c1 = number of consecutive 1s after trailing zeros
        int c1 = 0;
        while ((numCopy & 1) != 0) {
            numCopy >>= 1;
            c1++;
        }

        // if you have 11...100...0 or 00000000, then there is no greater number with the same number of 1s
        if (c0 + c1 == 31 || c0 + c1 == 0)
            return -1;

        // set p+1 to 1 and rest to 0s by first setting trailing 0s to 1s and then adding 1
        num += (1 << c0) - 1;
        num += 1;

        // set first c1 - 1
        num += (1 << c1 - 1) - 1;

        return num;
    }

    public static int getPrevArith(int num) {
        int numCopy = num;

        int c1 = 0;
        while ((numCopy & 1) != 0) {
            numCopy >>= 1;
            c1++;
        }

        int c0 = 0;
        while ((numCopy & 1) == 0 && (numCopy != 0)) {
            numCopy >>= 1;
            c0++;
        }

        // clear the trailing 1s
        num -= (1 << c1) - 1;

        // turn the trailing 0s to 1s
        num -= 1;

        // turn last c0 - 1 to 0s
        num -= (1 << c0 - 1) - 1;

        return num;
    }

    public static void p5_4_test() {
        int test = BitOperations.bitsToInt("10011100");
        System.out.println("p5_4_1: " + p5_4_1(test));
        System.out.println("p5_4_2: " + p5_4_2(test));
        System.out.println();
    }

    /**
     * P5_5: ((n & (n-1)) == 0)
     *
     * A & B == 0 means that they do not share any 1s
     * This is only true for n and (n-1) if we have something in the form of 00...0100.0
     * Thus, this checks for a power of 2
     */

    public static int p5_6(int a, int b) {
        int count = 0;
        // XOR gives you a bit array with 1s when the bits are not equal
        // c & (c - 1) clears the least significant 1, so we count how many 1's need to be cleared before we reach 0 to count the 1s in c
        for (int c = a ^ b; c != 0; c = c & (c - 1)) {
            count++;
        }

        return count;
    }

    public static void p5_6_test() {
        int a = BitOperations.bitsToInt("10011100");
        int b = BitOperations.bitsToInt("10011100");
        System.out.println("p5_6: " + p5_6(a, b));
        b = BitOperations.bitsToInt("11001001"); //4
        System.out.println("p5_6: " + p5_6(a, b));
        System.out.println();
    }

    public static int p5_7(int num) {
        System.out.println(Integer.toBinaryString(num));

        int odd = (num & 0xaaaaaaaa) >>> 1; //logical shift since we want the left (sign) bit to be 0
        int even = (num & 0x55555555) << 1;
        int result = odd | even;

        System.out.println(Integer.toBinaryString(result));
        return result;
    }

    public static void p5_7_test() {
        int num = BitOperations.bitsToInt("11010110");
        System.out.println("p5_7: " + p5_7(num));
        System.out.println();
    }

    void p5_8(byte[] screen, int width, int x1, int x2, int y) {
        int startOffset = x1 % 8;
        int endOffset = x2 % 8;

        int startByteIx = x1/8;
        if (startOffset != 0) {
            startByteIx++;
        }

        int endByteIx = x2/8;
        if (endOffset != 7) {
            endByteIx--;
        }

        int rowStart = (width/8) * y;
        // fill full bytes
        for (int b = startByteIx; b <= endByteIx; b++) {
            screen[rowStart + b] = (byte) 0xFF;
        }

        byte startMask = (byte) (0xFF >> startByteIx);
        byte endMask = (byte) ~(0XFF >> 1 + endByteIx);

        // if x1 and x2 are in the same byte
        if (x1/8 == x2/8) {
            byte mask = (byte) (startMask & endMask);
            screen[rowStart + x1/8] |= mask;
        }
        else {
            if (startOffset != 0) {
                screen[rowStart + startByteIx - 1] |= startMask;
            }
            if (endOffset != 7) {
                screen[rowStart + endByteIx + 1] |= endMask;
            }
        }
    }
}

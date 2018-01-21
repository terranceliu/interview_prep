package ctci.Ch5;

public class BitOperations {
    public static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    public static int setBit(int num, int i) {
        return num | (1 << i);
    }

    public static int clearBit(int num, int i) {
        return num & ~(1 << i);
    }

    public static int clearMostSigI(int num, int i) {
        return num & ((1 << i) - 1);
    }

    public static int clearI0(int num, int i) {
        return num & (-1 << (i+1));
    }

    public static int updateBit(int num, int i, boolean bit) {
        num = clearBit(num, i);
        int val = (bit) ? 1:0;
        return num | (val << i);
    }

    public static int bitsToInt(String bits) {
        char[] bitArray = bits.toCharArray();
        int num = 0;
        int power = 0;
        for (int i = bitArray.length - 1; i >= 0; i--) {
            char bit = bitArray[i];
            if (bit == '1')
                num += (int) Math.pow(2, power);

            power++;
        }

        return num;
    }
}

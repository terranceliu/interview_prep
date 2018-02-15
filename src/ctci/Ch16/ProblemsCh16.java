package ctci.Ch16;

import java.util.*;
import ctci.Ch16.Line.Point;

public class ProblemsCh16 {
    public static void main(String[] args) {
        /** Problem 16.1 */
        p16_1_test();

        /** Problem 16.3 */
        p16_3_test();

        /** Problem 16.5 */
        p16_5_test();

        /** Problem 16.6 */
        p16_6_test();

        /** Problem 16.7 */
        p16_7_test();

        /** Problem 16.10 */
        p16_10_test();

        /** Problem 16.11 */
        p16_11_test();
    }

    public static void p16_1_1(int a, int b) {
        System.out.println("p16_1_1:");
        System.out.println("a: " + a + ", b: " + b);

        a = a - b; // a = a - b
        b = b + a; // b = b + (a - b) = a
        a = b - a; // a - (a - b) = b

        System.out.println("a: " + a + ", b: " + b);
    }

    /** using bits (nice because it works with other data types) */
    public static void p16_1_2(int a, int b) {
        System.out.println("p16_1_2:");
        System.out.println("a: " + a + ", b: " + b);

        /**
         * a^b gives you a bit array in which each position is 1 if the a and b differ at that position.
         * 101 ^ 110 = 011
         */
        a = a ^ b;

        /**
         * a currently gives you the positions where a and b are different
         * XOR with 1 always flips the bit, and XOR with 0 leaves it the same
         * a^b will then flip bits where b is different from a, so that b becomes a
         */
        b = b ^ a;

        /**
         * same as above, except now b has been swapped with a, so b^a gives you b
         */
        a = b ^ a;

        System.out.println("a: " + a + ", b: " + b);
    }

    public static void p16_1_test() {
        p16_1_1(1, 2);
        p16_1_2(1, 2);
        System.out.println();
    }

    public static Point findIntersection(Point start1, Point end1, Point start2, Point end2) {
        // TODO: check if either a vertical line

        /** "before" means has smaller x_coor **/
        // make start before end
        if (end1.x > start1.x) Point.swap(start1, end1);
        if (end2.x > start2.x) Point.swap(start2, end2);
        // make start1 before start2
        if (start2.x < start1.x) {
            Point.swap(start1, start2);
            Point.swap(end1, end2);
        }

        Line l1 = new Line(start1, end1);
        Line l2 = new Line(start2, end2);

        //check if they are the same line
        if (l1.slope == l2.slope) {
            if (l1.yIntercept == l2.yIntercept && start2.isBetween(start1, end1))
                return start2;
            else
                return null;
        }

        double interceptX = -(l2.yIntercept - l1.yIntercept) / (l2.slope - l1.slope);
        double interceptY = l1.slope * interceptX + l1.yIntercept;
        Point intersection = new Point(interceptX, interceptY);

        if (intersection.isBetween(start1, end1) && intersection.isBetween(start2, end2))
            return intersection;
        else
            return null;
    }

    public static void p16_3_test() {
        Point intersection1 = findIntersection(new Point(1, 5), new Point(3.5, 10),
                new Point(0, -12), new Point(5, 20.4));
        System.out.println("p16_3: " + intersection1);


        Point intersection2 = findIntersection(new Point(1, 5), new Point(3.5, 10),
                new Point(0, -12), new Point(0.5, -5));
        System.out.println("p16_3: " + intersection2);

        System.out.println();
    }

    //TODO: 16_4 tic-tac-toe

    public static int p16_5(int n) {
        if (n < 0) return -1;

        int countZeros = 0;
        for (int i = 5; i <= n; i*=5) {
            countZeros += n / i;
        }

        return countZeros;
    }

    public static void p16_5_test() {
        System.out.println("p16_5: " + p16_5(11)); // 2
        System.out.println("p16_5: " + p16_5(26)); // 6
        System.out.println("p16_5: " + p16_5(127)); // 31
        System.out.println();
    }

    public static int p16_6(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int ixA = 0;
        int ixB = 0;

        int ixABest = -1;
        int ixBBest = -1;
        int diffBest = Integer.MAX_VALUE;

        while (ixA < a.length && ixB < b.length) {
            int diff = Math.abs(a[ixA] - b[ixB]);
            if (diff < diffBest) {
                ixABest = ixA;
                ixBBest = ixB;
                diffBest = diff;
            }

            if (a[ixA] < b[ixB])
                ixA++;
            else
                ixB++;
        }

        System.out.println("p16_6: " + ixABest + ", " + ixBBest + ": " + diffBest);
        return diffBest;
    }

    public static void p16_6_test() {
        int[] a = {1, 2, 11, 15};
        int[] b = {4, 12, 19, 23, 127, 235};

        p16_6(a, b);
        System.out.println();
    }

    public static int p16_7_1(int a, int b) {
        int k = sign(a - b);
        int q = k ^ 1;

        return a * k + b * q;
    }

    /** deals with overflow (if a - b > INTEGER_MAX) */
    public static int p16_7_2(int a, int b) {
        int c = a - b;

        int signA = sign(a);
        int signB = sign(b);
        int signC = sign(c);

        /**
         * Case 1: a and b have different signs
         * a > 0 & b < 0 => a is max => k (in 16_7_1) = 1
         * a < 0 & b > 0 => b is max => k = 0
         * Thus, k = sign(a)
         *
         * Case 2: a and b have the same sign (no overflow)
         * Same logic as in 16_7_1: k = sign(a - b);
         */

        // check if a & b have different signs
        int isDiffSign = signA ^ signB; // = 1 if they have different signs

        // if a & b have different signs, use signA. Else, use signC
        int k = isDiffSign * signA + flip(isDiffSign) * signC;
        int q = flip(k);

        return a * k + b * q;

    }

    // 1 if positive, else 0
    public static int sign(int num) {
        // checks if the sign bit = 1
        // if num is negative
        return flip((num >> 31) & 1);
    }

    public static int flip(int bit) {
        return bit ^ 1;
    }

    public static void p16_7_test() {
        System.out.println("p16_7_1: " + p16_7_1(75, 123));
        System.out.println("p16_7_1: " + p16_7_1(123, 75));
        System.out.println("p16_7_1(err): " + p16_7_1(Integer.MAX_VALUE - 2, -15));
        System.out.println("p16_7_1(err): " + p16_7_1(-15, Integer.MAX_VALUE - 2));

        System.out.println("p16_7_2: " + p16_7_2(75, 123));
        System.out.println("p16_7_2: " + p16_7_2(123, 75));
        System.out.println("p16_7_2: " + p16_7_2(Integer.MAX_VALUE - 2, -15));
        System.out.println("p16_7_2: " + p16_7_2(-15, Integer.MAX_VALUE - 2));

        System.out.println();
    }

    //TODO: 16_8

    //TODO: 16_9

    /** less optimal, sort first */
    public static int p16_10_1(Person[] people) {
        int[] births = getSortedYears(people, true);
        int[] deaths = getSortedYears(people, false);

        int ixBirth = 0;
        int ixDeath = 0;
        int numAlive = 0;
        int maxAlive = 0;
        int maxAliveYear = births[0];

        while (ixBirth < births.length) {
            if (births[ixBirth] <= deaths[ixDeath]) {
                numAlive++;
                if (numAlive > maxAlive) {
                    maxAlive = numAlive;
                    maxAliveYear = births[ixBirth];
                }
                ixBirth++;
            }
            else {
                numAlive--;
                ixDeath++;
            }
        }

        System.out.println("maxAlive: " + maxAlive);
        return maxAliveYear;
    }

    public static class Person {
        int birth;
        int death;
        public Person(int birth, int death) {
            this.birth = birth;
            this.death = death;
        }
    }

    public static int[] getSortedYears(Person[] people, boolean getBirthYears) {
        int[] years = new int [people.length];
        for (int i = 0; i < people.length; i++) {
            years[i] = getBirthYears ? people[i].birth : people[i].death;
        }
        Arrays.sort(years);
        return years;
    }


    public static int p16_10_2(Person[] people, int minYear, int maxYear) {
        int[] deltasByYear = getDeltasByYear(people, minYear, maxYear);

        int numAlive = 0;
        int maxNumAlive = 0;
        int maxNumAliveYear = minYear;

        for (int i = 0; i < deltasByYear.length; i++) {
            numAlive += deltasByYear[i];
            if (maxNumAlive < numAlive) {
                maxNumAlive = numAlive;
                maxNumAliveYear = i;
            }
        }

        System.out.println("maxAlive: " + maxNumAlive);
        return maxNumAliveYear + minYear;
    }

    public static int[] getDeltasByYear(Person[] people, int minYear, int maxYear) {
        //if someone dies, we shouldn't update the delta until the year after, so we need to increase the size by 1
        int[] deltasByYear = new int[maxYear - minYear + 1 + 1];
        for (Person p: people) {
            int birthIx = p.birth - minYear;
            int deathIx = p.death - minYear;

            deltasByYear[birthIx]++;
            deltasByYear[deathIx + 1]--;
        }

        return deltasByYear;
    }

    public static void p16_10_test() {
        int[] pBirth = {12, 20, 10, 1, 10, 23, 13, 90, 83, 75};
        int[] pDeath = {15, 90, 98, 72, 98, 82, 98, 98, 98, 94};

        Person[] people = new Person[pBirth.length];
        for (int i = 0; i < people.length; i++) {
            people[i] = new Person(pBirth[i], pDeath[i]);
        }

        System.out.println("p16_10_1: " + p16_10_1(people));
        System.out.println("p16_10_2: " + p16_10_2(people, 0, 100));

        System.out.println();
    }

    public static HashSet<Integer> p16_11(int k, int shorter, int longer) {
        HashSet<Integer> lengths = new HashSet();

        for (int i = 1; i <= k; i++) {
            lengths.add(i * shorter + (k - i) * longer);
        }

        return lengths;
    }

    public static void p16_11_test() {
        HashSet<Integer> lengths = p16_11(5, 1, 3);
        System.out.print("p16_11:");
        for (int length: lengths) {
            System.out.print(" " + length);
        }
        System.out.println();

        ArrayList<Integer> array = new ArrayList();
        HashMap<Integer, Integer> set = new HashMap();

        set.

    }
}

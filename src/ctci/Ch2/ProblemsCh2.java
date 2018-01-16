package ctci.Ch2;

import java.util.*;

public class ProblemsCh2 {
    public static void main (String[] args) {
        /** Problem 2.1 */
        p2_1_test();

        /** Problem 2.2 */
        p2_2_test();

        /** Problem 2.3 */
        p2_3_test();

        /** Problem 2.4 */
        p2_4_test();

        /** Problem 2.5 */
        p2_5_test();

        /** Problem 2.6 */
        p2_6_test();

        /** Problem 2.7 */
        p2_7_test();

        /** Problem 2.8 */
        p2_8_test();
    }


    /** remove duplicates */
    public static LinkedListNode p2_1_1(LinkedListNode head) {
        HashSet<Integer> found = new HashSet<>();
        found.add(head.data);
        int d;

        LinkedListNode prev = head;
        LinkedListNode curr = prev.next;
        while (curr != null) {
            d = curr.data;
            if (found.contains(d)) {
                prev.next = curr.next;
            }
            else {
                found.add(d);
                prev = prev.next;
            }
            curr = prev.next;
        }

        return head;
    }

    /** Without a buffer */
    public static LinkedListNode p2_1_2(LinkedListNode head) {
        LinkedListNode n = head;
        int check = n.data;

        while (n.next != null) {
            LinkedListNode prev = n;
            LinkedListNode curr = prev.next;

            while (curr != null) {
                if (curr.data == check) {
                    prev.next = curr.next;
                }
                else {
                    prev = prev.next;
                }
                curr = prev.next;
            }

            n = n.next;
        }

        return head;
    }

    public static void p2_1_test() {
        LinkedListNode node1 = new LinkedListNode();
        int[] list1 = {1, 2, 3, 4, 5};
        node1.appendListToTail(list1);

        int[] list2 = {0, 0, 1, 2, 3, 4, 4, 5, 5, 6, 7, 7};
        LinkedListNode node2 = new LinkedListNode();
        node2.appendListToTail(list2);

        System.out.println("p2_1_1: " + p2_1_1(node1));
        System.out.println("p2_1_1: " + p2_1_1(node2));
        System.out.println("p2_1_2: " + p2_1_2(node1));
        System.out.println("p2_1_2: " + p2_1_2(node2));
        System.out.println();
    }


    /** find kth to last */
    public static int p2_2(LinkedListNode head, int k) {
        LinkedListNode n = head;
        LinkedListNode k_ahead = n;

        for (int i = 0; i < k; i++) {
            k_ahead = k_ahead.next;
        }

        while (k_ahead.next != null) {
            n = n.next;
            k_ahead = k_ahead.next;
        }

        return n.data;
    }

    public static void p2_2_test() {
        LinkedListNode node = new LinkedListNode();
        int[] list = {0, 0, 1, 2, 3, 4, 4, 5, 5, 6, 7, 7};
        node.appendListToTail(list);

        System.out.println("p2_2: " + p2_2(node, 0));
        System.out.println("p2_2: " + p2_2(node, 1));
        System.out.println("p2_2: " + p2_2(node, 2));
        System.out.println("p2_2: " + p2_2(node, 3));
        System.out.println("p2_2: " + p2_2(node, 4));
        System.out.println();
    }


    /** delete a middle node */
    public static void p2_3(LinkedListNode del) {
        del.data = del.next.data;
        del.next = del.next.next;
    }

    public static void p2_3_test() {
        LinkedListNode node = new LinkedListNode();
        int[] list = {1, 2, 3, 4, 5};
        node.appendListToTail(list);

        LinkedListNode del = node;
        for (int i = 0; i < 3; i++) {
            del = del.next;
        }
        System.out.println("p2_3: deleting " + del.data);
        System.out.println("Before: " + node);
        p2_3(del);
        System.out.println("After: " + node);
        System.out.println();
    }


    /** partition */
    public static LinkedListNode p2_4_1(LinkedListNode node, int val) {
        LinkedListNode headSmaller = null;
        LinkedListNode currSmaller = null;
        LinkedListNode headLarger = null;
        LinkedListNode currLarger = null;

        LinkedListNode n = node;
        while(n != null) {
            int d = n.data;
            if (d < val) {
                if (headSmaller == null) {
                    headSmaller = new LinkedListNode(d);
                    currSmaller = headSmaller;
                }
                else {
                    currSmaller.next = new LinkedListNode(d);
                    currSmaller = currSmaller.next;
                }
            }
            else {
                if (headLarger == null) {
                    headLarger = new LinkedListNode(d);
                    currLarger = headLarger;
                }
                else {
                    currLarger.next = new LinkedListNode(d);
                    currLarger = currLarger.next;
                }
            }

            n = n.next;
        }

        if (headSmaller != null) {
            currSmaller.next = headLarger;
            return headSmaller;
        }
        else if (headLarger != null) {
            return headLarger;
        }
        else
            return null;
    }

    public static LinkedListNode p2_4_2(LinkedListNode node, int val) {
        LinkedListNode head = node;
        LinkedListNode tail = node;

        LinkedListNode n = node;
        while (n != null) {
            LinkedListNode next = n.next;
            if (n.data < val) {
                n.next = head;
                head = n;
            }
            else {
                tail.next = n;
                tail = n;
            }
            n = next;
        }
        tail.next = null;
        return head;
    }

    public static void p2_4_test() {
        LinkedListNode node = new LinkedListNode();
        int[] list = {3, 5, 8, 5, 10, 2, 1};
        node.appendListToTail(list);

        System.out.println("p2_4_1: " + p2_4_1(node, 5));
        System.out.println("p2_4_2: " + p2_4_2(node, 5));
        System.out.println();
    }


    /** add (numbers are in reverse order) */
    public static LinkedListNode p2_5_1(LinkedListNode num1, LinkedListNode num2) {
        LinkedListNode result = new LinkedListNode();
        int carry = 0;

        while (num1 != null && num2 != null) {
            int digit = num1.data + num2.data + carry;
            if (digit >= 10) {
                carry = 1;
                digit = digit % 10;
            }
            else {
                carry = 0;
            }
            result.appendToTail(digit);

            num1 = num1.next;
            num2 = num2.next;
        }

        if (num1 != null) {
            while (num1 != null) {
                result.appendToTail(num1.data + carry);
                carry = 0;

                num1 = num1.next;
            }
        }

        if (num2 != null) {
            while (num2 != null) {
                result.appendToTail(num2.data + carry);
                carry = 0;

                num2 = num2.next;
            }
        }

        if (carry != 0)
            result.appendToTail(carry);

        return result.next;
    }

    /** Recursive */
    public static LinkedListNode p2_5_2(LinkedListNode num1, LinkedListNode num2, int carry) {
        if (num1 == null && num2 == null && carry == 0)
            return null;

        int val = carry;
        if (num1 != null)
            val += num1.data;
        if (num2 != null)
            val += num2.data;

        int digit = val % 10;
        LinkedListNode result = new LinkedListNode(digit);

        LinkedListNode nextNum1 = (num1 == null ? null : num1.next);
        LinkedListNode nextNum2 = (num1 == null ? null : num2.next);
        int nextCarry = (val >= 10 ? 1 : 0);

        LinkedListNode next = p2_5_2(nextNum1, nextNum2, nextCarry);
        result.next = next;

        return result;
    }

    /** add (numbers are in regular order) */
    public static LinkedListNode p2_5_3(LinkedListNode num1, LinkedListNode num2, int carry) {
        //TODO
        return null;
    }

    public static void p2_5_test() {
        LinkedListNode num1 = new LinkedListNode(7);
        int[] list1 = {1, 6};
        num1.appendListToTail(list1);

        LinkedListNode num2 = new LinkedListNode(5);
        int[] list2 = {9, 2};
        num2.appendListToTail(list2);

        LinkedListNode num3 = new LinkedListNode(9);
        int[] list3 = {9, 9};
        num3.appendListToTail(list3);

        LinkedListNode num4 = new LinkedListNode(4);
        int[] list4 = {2, 3, 2};
        num4.appendListToTail(list4);


        System.out.println("p2_5_1: " + p2_5_1(num1, num2)); // 617 + 295 = 912
        System.out.println("p2_5_1: " + p2_5_1(num1, num3)); // 617 + 999 = 1616
        System.out.println("p2_5_1: " + p2_5_1(num1, num4)); // 617 + 2324 = 2941
        System.out.println("p2_5_2: " + p2_5_2(num1, num2, 0));
        System.out.println("p2_5_2: " + p2_5_2(num1, num3, 0));
        System.out.println("p2_5_2: " + p2_5_2(num1, num4, 0));

        System.out.println();
    }


    /** check for palindrome */
    public static boolean p2_6_1(LinkedListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        LinkedListNode slow = head;
        LinkedListNode fast = head;
        LinkedListNode firstHalfReversed = new LinkedListNode(slow.data);

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            LinkedListNode temp = firstHalfReversed;
            firstHalfReversed = new LinkedListNode(slow.data);
            firstHalfReversed.next = temp;
        }
        slow = slow.next;

        /** Case where the list has odd # of nodes */
        if (fast.next == null)
            firstHalfReversed = firstHalfReversed.next;

        while (firstHalfReversed != null) {
            if (slow.data != firstHalfReversed.data)
                return false;
            slow = slow.next;
            firstHalfReversed = firstHalfReversed.next;
        }

        return true;
    }

    /** Recursive */
    public static boolean p2_6_2(LinkedListNode head) {
        // TODO
        return true;
    }


    public static void p2_6_test() {
        LinkedListNode node1 = new LinkedListNode(1);
        int[] list1 = {2, 2, 1};
        node1.appendListToTail(list1);

        LinkedListNode node2 = new LinkedListNode(1);
        int[] list2 = {2, 3, 2, 1};
        node2.appendListToTail(list2);

        LinkedListNode node3 = new LinkedListNode(1);
        int[] list3 = {2, 2, 2};
        node3.appendListToTail(list3);

        LinkedListNode node4 = new LinkedListNode(1);
        int[] list4 = {2, 3, 2, 5};
        node4.appendListToTail(list4);


        System.out.println("p2_6_1: " + p2_6_1(node1));
        System.out.println("p2_6_1: " + p2_6_1(node2));
        System.out.println("p2_6_1: " + p2_6_1(node3));
        System.out.println("p2_6_1: " + p2_6_1(node4));
        System.out.println();
    }


    /** Find intersection */
    public static LinkedListNode p2_7(LinkedListNode node1, LinkedListNode node2) {
        LinkedListNode n1 = node1;
        int n1Length = 1;
        while (n1.next != null) {
            n1Length++;
            n1 = n1.next;
        }

        LinkedListNode n2 = node2;
        int n2Length = 1;
        while (n2.next != null) {
            n2Length++;
            n2 = n2.next;
        }

        if (n1 != n2)
            return null;

        n1 = node1;
        n2 = node2;
        int diff;
        if (n1Length > n2Length) {
            diff = n1Length - n2Length;
            for (int i = 0; i < diff; i++) {
                n1 = n1.next;
            }
        }
        else {
            diff = n2Length - n1Length;
            for (int i = 0; i < diff; i++) {
                n2 = n2.next;
            }
        }

        while (n1 != null) {
            if (n1 == n2)
                return n1;
            n1 = n1.next;
            n2 = n2.next;
        }
        return null;
    }

    public static void p2_7_test() {
        LinkedListNode intersection = new LinkedListNode(-1);
        int[] list_intersection = {0, 0, 0};
        intersection.appendListToTail(list_intersection);

        LinkedListNode node1 = new LinkedListNode(1);
        int[] list1 = {2, 3};
        node1.appendListToTail(list1);
        node1.appendToTail(intersection);

        LinkedListNode node2 = new LinkedListNode(1);
        int[] list2 = {2, 3, 4, 5, 6, 7};
        node2.appendListToTail(list2);
        node2.appendToTail(intersection);

        System.out.println("p2_7: " + p2_7(node1, node2));
        System.out.println("p2_7: " + p2_7(node2, node1));
        System.out.println();
    }


    /**
     * find cycle
     *
     * fast = 2x slow. When slow first enter the loop after k steps, fast is k steps ahead or LOOP_SIZE - k steps behind
     * In another LOOP_SIZE - k moves, fast catches up to slow since fast catches up by 1 every move
     * Now, slow is LOOP_SIZE - k steps into the loop, so after k more steps, slow will be at the start
     * Thus, if you have another runner at the start moving at the same speed as slow, they will meet at the start of the loop
     *
     * */
    public static LinkedListNode p2_8(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow)
                break;
        }

        if (fast == null || fast.next == null) {
            System.out.println("returning null");
            return null;
        }

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static void p2_8_test() {
        LinkedListNode head = new LinkedListNode(0);
        int[] list_intersection = {0, 0, 0};
        head.appendListToTail(list_intersection);

        LinkedListNode loopStart = new LinkedListNode (1);
        head.appendToTail(loopStart);
        head.appendListToTail(list_intersection);

        LinkedListNode n = head;
        while (n.next != null) {
            n = n.next;
        }
        n.next = loopStart;


        LinkedListNode result = p2_8(head);

        System.out.println("p2_8: " + Integer.toString(result.data));
        System.out.println();

    }
}

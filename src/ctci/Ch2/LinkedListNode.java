package ctci.Ch2;

public class LinkedListNode {
    LinkedListNode next = null;
    int data;

    public LinkedListNode() {
        data = 0;
    }

    public LinkedListNode (int val) {
        data = val;
    }

    public void appendToTail(int d) {
        LinkedListNode end = new LinkedListNode(d);
        LinkedListNode n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    public void appendToTail(LinkedListNode tail) {
        LinkedListNode n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = tail;
    }

    public void appendListToTail(int[] list) {
        LinkedListNode n = this;
        for (int i = 0; i < list.length; i++) {
            n.appendToTail(list[i]);
        }
    }

    public String toString() {
        LinkedListNode n = this;
        StringBuilder result  = new StringBuilder();
        result.append(Integer.toString(n.data));

        while (n.next != null) {
            n = n.next;
            result.append(" ").append(Integer.toString(n.data));
        }

        return result.toString();
    }

    public static LinkedListNode deleteLinkedListNode(LinkedListNode head, int d) {
        LinkedListNode n = head;

        /** if first Ch2.LinkedListNode contains d */
        if (n.data == d) {
            return head.next;
        }

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                return head;
            }
            n = n.next;
        }
        return head;
    }

}



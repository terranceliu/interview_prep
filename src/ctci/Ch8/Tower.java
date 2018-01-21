package ctci.Ch8;

import java.util.*;

public class Tower {
    private Stack<Integer> disks;
    private int index;

    public Tower(int i) {
        disks = new Stack<Integer>();
        index = i;
    }

    public int index() {
        return index;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
        }
        else {
            disks.push(d);
        }
    }

    public void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
    }

    // orig0-2 is to print out the state of the 3 towers in order
    public void moveDisks(int n, Tower destination, Tower buffer, Tower orig0, Tower orig1, Tower orig2) {
        if (n > 0) {
            moveDisks(n - 1, buffer, destination, orig0, orig1, orig2);

            moveTopTo(destination);

            System.out.println(orig0);
            System.out.println(orig1);
            System.out.println(orig2);
            System.out.println();

            buffer.moveDisks(n - 1, destination, this, orig0, orig1, orig2);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tower ").append(index).append(":");

        for (int i = 0; i < disks.size(); i ++) {
            sb.append(" ").append(disks.toArray()[i]);
        }

        return sb.toString();
    }
}


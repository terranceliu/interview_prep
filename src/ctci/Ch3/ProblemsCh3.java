package ctci.Ch3;

public class ProblemsCh3 {
    public static void main (String[] args) {
        /** Problem 3.4 */
        p3_4_test();

        /** Problem 3.5 */
        p3_5_test();

        /** Problem 3.6 */
        p3_6_test();
    }

    /**
     * P3_1_2: Use a single array to implement 3 stacks
     *
     * Keep an array for values. Offset = arraySize/3 * (stackNum)
     * Keep an array (of size 3) that tracks the number of values in each stack
     */

    /**
     * P3_1_2: More flexible size
     *
     * TODO
     */

    /**
     *
     * P3_2: Keep track of min in a stack
     *
     * Solution 1:
     * Have each node store a min value of all elements added so far
     *
     * Solution 2 (more space efficient)
     * Have a separate stack that pushes new mins
     *
     */

    /**
     *
     * P3_3: Set of stacks
     *
     * ArrayList of stacks
     *
     * popAt(index):
     * use recursive function: leftShift(index, bool removeTop)
     * First you remove from the top. Then you remove from the bottom of subsequent stacks.
     * This solution requires each Stack to allow bottom node retrieval
     *
     */

    /** Implement a queue with two stacks */
    public static void p3_4_test() {
        System.out.print("p3_4:");
        MyQueue queue = new MyQueue();

        queue.add(1);
        queue.add(2);
        System.out.print(" " + Integer.toString(queue.remove()));
        queue.add(3);
        queue.add(4);
        System.out.print(" " + Integer.toString(queue.peek()));
        while (!queue.isEmpty()) {
            System.out.print(" " + Integer.toString(queue.remove()));
        }
        System.out.println();
    }


    /** Sort stack with only one buffer stack */
    public static void p3_5(StackTL stack) {
        System.out.print("p3_5:");
        StackTL sorted = new StackTL();
        sorted.push(stack.pop());
        int next_val;

        while (!stack.isEmpty()) {
            next_val = stack.pop();
            while (!sorted.isEmpty() && next_val < sorted.peek()) {
                stack.push(sorted.pop());
            }

            sorted.push(next_val);
        }

        while (!sorted.isEmpty()) {
            stack.push(sorted.pop());
        }
    }

    public static void p3_5_test() {
        StackTL stack = new StackTL();
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(0);
        stack.push(5);
        stack.push(3);
        p3_5(stack);

        while (!stack.isEmpty()) {
            System.out.print(" " + Integer.toString(stack.pop()));
        }
        System.out.println();
    }


    public static void p3_6_test() {
        System.out.print("p3_6:");
        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(shelter.DOG);
        shelter.enqueue(shelter.CAT);
        shelter.enqueue(shelter.CAT);
        shelter.enqueue(shelter.DOG);

        System.out.print(" " + Integer.toString(shelter.dequeueDog()));
        System.out.print(" " + Integer.toString(shelter.dequeueAny()));
        System.out.print(" " + Integer.toString(shelter.dequeueCat()));
        shelter.enqueue(shelter.CAT);
        System.out.print(" " + Integer.toString(shelter.dequeueCat()));
        System.out.print(" " + Integer.toString(shelter.dequeueAny()));
        System.out.println();
    }
}

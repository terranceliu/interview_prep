package ctci.Ch3;

import java.util.*;

public class AnimalShelter {
    int DOG = 0;
    int CAT = 1;

    QueueTL queueDog;
    QueueTL queueCat;
    LinkedList<Integer> history = new LinkedList();

    public AnimalShelter() {
        queueDog = new QueueTL();
        queueCat = new QueueTL();
        history = new LinkedList();
    }

    public void enqueue(int animal) {
        history.add(animal);
        if (animal == DOG)
            queueDog.add(animal);
        else
            queueCat.add(animal);
    }

    public int dequeueAny() {
        int animalType = history.removeFirst();
        if (animalType == DOG)
            return queueDog.remove();
        else
            return queueCat.remove();
    }

    public int dequeueDog() {
        history.remove(Integer.valueOf(DOG));
        return queueDog.remove();
    }

    public int dequeueCat() {
        history.remove(Integer.valueOf(CAT));
        return queueCat.remove();
    }
}

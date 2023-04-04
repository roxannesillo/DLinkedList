package main;

import dlinkedlist.DLinkedList;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        List<Number> numbers = new DLinkedList<>();
        // add method test
        numbers.add(6);
        numbers.add(8);
        numbers.add(6);
        numbers.add(11);
        numbers.add(7);
        System.out.println(numbers);

        System.out.println("-------------------");

        // Iterator test
        Iterator<Number> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("-------------------");

        //IndexOf test
        System.out.println(numbers.indexOf(11));
        System.out.println(numbers.indexOf(8));

        System.out.println("-------------------");

        //LastIndexOf test
        System.out.println(numbers.lastIndexOf(6));
        System.out.println(numbers.lastIndexOf(7));

        System.out.println("-------------------");

        //GetNode test
        System.out.println(numbers.get(0));
        System.out.println(numbers.get(1));
        System.out.println(numbers.get(2));
        System.out.println(numbers.get(3));
        System.out.println(numbers.get(4));

        System.out.println("-------------------");

        //SubList test
        List<Number> subList = numbers.subList(0,3);
        System.out.println(subList);

        System.out.println("-------------------");

        //listIterator test
        ListIterator<Number> listIterator = numbers.listIterator();
        while (listIterator.hasNext()){
            int index = listIterator.nextIndex();
            System.out.println(listIterator.next() + " " + index);
        }
        System.out.println("-------------------");

        while (listIterator.hasPrevious()){
            int nextIndex = listIterator.previousIndex();
            System.out.println(listIterator.previous() + " " + nextIndex);
        }

        System.out.println("-------------------");

        ListIterator<Number> listIteratorWithIndex = numbers.listIterator(2);
        while (listIteratorWithIndex.hasNext()){
            System.out.println(listIteratorWithIndex.next());
        }

        System.out.println("-------------------");
        while (listIteratorWithIndex.hasPrevious()){
            System.out.println(listIteratorWithIndex.previous());
        }


    }
}

package main;

import dlinkedlist.DLinkedList;

import java.util.*;

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
        List<Number> subList = numbers.subList(0, 3);
        System.out.println(subList);

        System.out.println("-------------------");

        //listIterator test
        ListIterator<Number> listIterator = numbers.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.nextIndex() + " " + listIterator.next() + " " + listIterator.nextIndex());
        }
        System.out.println("-------------------");

        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previousIndex() + " " + listIterator.previous() + " " + listIterator.previousIndex());
        }

        System.out.println("-------------------**");



        ListIterator<Number> listIteratorWithIndex = numbers.listIterator(1);
        while (listIteratorWithIndex.hasNext()){
            System.out.println(listIteratorWithIndex.next() + " " + listIteratorWithIndex.nextIndex());
        }

        System.out.println("-------------------**");
        while (listIteratorWithIndex.hasPrevious()){
            System.out.println(listIteratorWithIndex.previous());
        }

        System.out.println("-----------------------");

        // addAll test
        List<Number> listToAdd = new ArrayList<>();
        listToAdd.add(6);
        listToAdd.add(12);
        listToAdd.add(13);

        numbers.addAll(listToAdd);
        System.out.println(numbers);

        // containsAll test

        System.out.println("--------------------");

        boolean containsAll = new HashSet<>(numbers).containsAll(listToAdd);
        System.out.println(containsAll);

        System.out.println("-------------------");

        // retainAll test

        boolean retainAll = numbers.retainAll(listToAdd);
        System.out.println(numbers);

        System.out.println("-------------------");

        // removeAll test
        boolean removeAll = numbers.removeAll(listToAdd);
        System.out.println(numbers);
    }
}


package org.laura.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int n = 0;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        else {
            if (n == array.length)
                resize(2 * array.length);
            array[n++] = item;
        }
    }

    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        } else {
            // Selecting an index at random, and then swapping it with the last index
            int randomIndex = StdRandom.uniform(0, n);
            Item temp = array[n - 1];
            array[n - 1] = array[randomIndex];
            array[randomIndex] = temp;

            Item item = array[--n];
            array[n] = null;
            if (n > 0 && n == array.length / 4)
                resize(array.length / 2);
            return item;
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        if (n >= 0) System.arraycopy(array, 0, copy, 0, n);
        array = copy;
    }


    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] arrayCopy = (Item[]) new Object[n];
        private int i = n;
        private int randomIndex;

        public RandomizedQueueIterator() {
            System.arraycopy(array, 0, arrayCopy, 0, n);
        }


        public boolean hasNext() {
            return i > 0;
        }


        public Item next() {
            if (i == 0)
                throw new NoSuchElementException();
            else {
                randomIndex = StdRandom.uniform(0, i);
                Item temp = arrayCopy[i - 1];
                arrayCopy[i - 1] = arrayCopy[randomIndex];
                arrayCopy[randomIndex] = temp;
                return arrayCopy[--i];
            }
        }

    }


}
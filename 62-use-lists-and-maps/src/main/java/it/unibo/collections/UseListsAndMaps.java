package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int ELEM = 100_000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *          unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        List<Integer> arr = new ArrayList<>();
        for (int i = 1000; i < 2000; i++) {
            arr.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        List<Integer> newArr = new LinkedList<>(arr);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */

        if (!arr.isEmpty()) {
            int tmp = arr.get(arr.size() - 1);
            arr.set(arr.size() - 1, arr.get(0));
            arr.set(0, tmp);
        }

        // System.out.println(arr);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for(Integer i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
        // final var builder = new StringBuilder();
        // for (final int i : arr) {
        //     builder.append(i);
        //     builder.append(", ");
        // }
        // if (builder.length() > 0) {
        //     builder.delete(builder.length() - 2, builder.length());
        // }
        // System.out.println(builder);
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        for(int i=0; i < ELEM; i++) {
            arr.add(0, i);
        }
        time = System.nanoTime() - time;
        System.out.println("Insert new elem in the head of an ArrayList took " + timeAsString(time));
        time = System.nanoTime();
        for(int i=0; i < ELEM; i++) {
            newArr.add(0, i);
        }
        time = System.nanoTime() - time;
        System.out.println("Insert new elem in the head of an LinkedList took " + timeAsString(time));

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime();
        for(int i=0; i < 1000; i++) {
            arr.get(arr.size() / 2);
        }
        time = System.nanoTime() - time;
        System.out.println("Reading 1000 elements in the middle of an ArrayList took " + timeAsString(time));
        for(int i=0; i < 1000; i++) {
            newArr.get(arr.size() / 2);
        }
        time = System.nanoTime() - time;
        System.out.println("Reading 1000 elements in the middle of an LinkedList took " + timeAsString(time));

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> wd = new HashMap<>();
        wd.put("Africa", 1_110_635_000L);
        wd.put("Americas", 972_005_000L);
        wd.put("Antarctica", 0L);
        wd.put("Asia", 4_298_723_000L);
        wd.put("Europe", 742_452_000L);
        wd.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        long totPop = 0;
        for (long pop : wd.values()) {
            totPop += pop;
        }
        System.out.println(totPop);
    }

    private static String timeAsString(final long nanoseconds) {
        return nanoseconds + "ns (" + NANOSECONDS.toMillis(nanoseconds) + "ms)";
    }
}

package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class OrdredByWeightNumbers {

    /**
     * Take each value of integeres of a String and calculate the sum of its digits (the "weight")
     * Then return the list in ascending order by weight, as a string joined by a space.
     * when two numbers have the same "weight", are considered to be strings
     *
     * @param strng a list as a String containing positive integers separated by a space, the list can be empty
     * @return a list as a String of integers separated by space ordered by weight
     */
    public static String orderWeight(String strng) {


        //should not be null, should contain only digits or spaces or empty String
        if (strng == null || !strng.matches("(\\d|\\s|^$)+")) {
            throw new IllegalArgumentException("The list can not be null or contains non digits");
        }

        //extract an Array of String numbers using split by spaces
        //the replaceFirst just help to remove the leading space of split
        String[] arrayOfNumbers = strng.replaceFirst("^\\s+", "").split("\\s+");

        //prepare a TreeMap because we need a sort by key (the weight)
        //and put a list of same weight numbers with the same key
        TreeMap<Integer, List<String>> weightsOrdered = new TreeMap<>();

        Arrays.stream(arrayOfNumbers)
                //we stream the numbers Strings
                .forEach(
                        number -> {
                            //we stream characters of every number, we convert every char to int,
                            //and then we sum the stream of chars to find the weight that will be the key of the map
                            int weight = number.chars().map(Character::getNumericValue).sum();
                            //we take a reference of the same weight numbers list
                            List<String> list = weightsOrdered.get(weight);
                            //we check if a number already exist with same weight else we create a new List
                            if (list == null) {
                                list = new ArrayList<>();
                                weightsOrdered.put(weight, list);
                            }
                            //we add the number to the list
                            list.add(number);
                        });

        //we join the Map element to a one String separated by spaces
        StringJoiner result = new StringJoiner(" ");
        weightsOrdered.forEach(
                (k, v) -> {
                    result.add(v.stream().sorted().collect(Collectors.joining(" ")));
                }
        );

        return result.toString();
    }

    public static void main(String[] args) {

        ArrayList<String> array = new ArrayList<>();
        array.add(null);
        array.add(null);

        System.out.println("result :" + orderWeight("   ")+":");

    }
}
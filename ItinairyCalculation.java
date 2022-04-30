package com.example.demo;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItinairyCalculation {

    /**
     * determine the ordered complete path itineraries of a given list of itineraries
     *
     * @param routes ArrayList<ArrayList<String>> of itineraries
     * @return ordered complete path itineraries
     */
    public static String findRoutes(ArrayList<ArrayList<String>> routes) {

        if ( routes == null ) {
            throw new IllegalArgumentException("Null not excepted");
        }

        //a Map can represent a simple Graph : Departure -> Destination
        Map<String, String> graph = new HashMap<>();

        try {
        //we load the graph from the input Lists
        routes.forEach(route -> graph.put(route.get(0), route.get(1)));
        } catch (Exception exception) {
            throw new RuntimeException("Invalid itineraries found", exception);
        }

        //used to hold all list of paths in the graph
        ArrayList<String> possiblePaths = new ArrayList<String>();

        //we loop through the graph and explore all the possible paths starting from the first element
        for (Map.Entry<String, String> entry : graph.entrySet()) {
            //a String Joiner to add the path
            StringJoiner path = new StringJoiner(", ");
            //we add the first travel
            path.add(entry.getKey());
            path.add(entry.getValue());
            //the destination become the departure of the next travel
            String next = entry.getValue();
            //we will join nex destinations until there is no next destination
            while (graph.get(next) != null) {
                //add the next Destination
                path.add(graph.get(next));
                //the destination become the departure (key) of the next travel if it exists
                next = graph.get(next);
            }
            //add this path
            possiblePaths.add(path.toString());
        }
        //the longest path is the complete path between all destinations
        return possiblePaths.stream().max(Comparator.comparing(String::length)).get();
    }

    @Test
    public void exceptionWhenNullParm( ) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> findRoutes(null));
        assertEquals("Null not accepted", exception.getMessage());
    }

    public static void main(String[] args) {

        System.out.println(findRoutes(new ArrayList<ArrayList<String>>
                (Arrays.asList(new ArrayList<String>(Arrays.asList(null, "TAG")),
                        new ArrayList<String>(Arrays.asList("CEB", "TAC")),
                        new ArrayList<String>(Arrays.asList("TAG", "CEB")),
                        new ArrayList<String>(Arrays.asList("TAC", "BOR"))))));


    }
}
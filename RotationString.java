package com.example.demo;

public class RotationString {

    /**
     * computes the number of rotations needed to make string first equal to string second, if possible
     *
     * @param first String to be rotated
     * @param second target String to be matched by rotating first
     * @return int number of rotations needed to turn string first into second, -1 if invalid
     */
    public static int shiftedDiff(String first, String second) {
        //a rotation should not be null
        if (first == null || second == null) {
            return -1;
        }
        //a rotation should have same length
        else if (first.length() != second.length()) {
            return -1;
        } else if (first.equals(second)) {
            //same Strings found already so no rotation needed
            return 0;
        } else {
            // the rotated String should be contained into the concat of first to it-self
            // example: any rotation of "abc" should be contained into "abcabc"
            String concatFirst = first + first;
            //the start index of second in concatFirst
            int startIndexOfSecond = concatFirst.indexOf(second);
            //if its is a rotation it should be > 0
            if (startIndexOfSecond > 0) {
                //the number of rotation needed should be the length (of second or first) minus startIndexOfSecond
                return first.length() - startIndexOfSecond;
            } else {
                //no rotation found
                return -1;
            }
        }
    }
    public static void main(String[] args) {
        shiftedDiff("ABCDE", "EABCD");
    }
}
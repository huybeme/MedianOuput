package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * A class for reading a series of numbers from a text file and output the
 *      median into a separate text file
 *
 * Completed by: Huy Le, Michael McTague, and Maria Pires
 *      date: 03/24/2020
 */

public class Main {

    public static void main(String[] args) throws IOException {

        // IOException allows to read a text file without a complete directory as long as file is saved text file
        //      is in project folder; code below allows to read a file as input
        String path = "ArrayLab6.txt";
        File myFile = new File(path);
        Scanner inputFile = new Scanner(myFile);

        // FileWriter allows to write output into a file; false overwrites file instead of appending to file
        FileWriter fw = new FileWriter("MedianLab6", false);
        PrintWriter outputFile = new PrintWriter(fw);

        boolean loopBreaker = true;
        ArrayList<Double> ArrayLab6 = new ArrayList<Double>();      // array list of undefined size of type double

        while (loopBreaker) {
            String readLine = inputFile.nextLine();                 // read each line in file as String
            double stringToDouble = Double.parseDouble(readLine);   // convert string into double
            ArrayLab6.add(stringToDouble);                          // add double into array list

            if (!inputFile.hasNext()) {                             // if end of file is reached, break loop
                loopBreaker = false;
            }
        }

        System.out.println("This is the unsorted array list: ");
        for (int i = 0; i < ArrayLab6.size(); i++) {                // prints out array list using a loop
            System.out.print(ArrayLab6.get(i) + "  ");
        }
        System.out.println();
        System.out.println();

        // convert arrayList into a fixed array and output the array
        //      selection sort does not work with ArrayList type, therefore conversion is necessary
        double[] sortArray = new double[ArrayLab6.size()];
        for (int i = 0; i < sortArray.length; i++) {
            sortArray[i] = ArrayLab6.get(i);
        }

        // sorts array in ascending order
        selectionSort(sortArray);

        System.out.println("This is the sorted array list using selection sort: ");
        for (int i = 0; i < sortArray.length; i++) {
            System.out.print(sortArray[i] + "  ");
        }
        System.out.println();

        //System.out.println(ArrayLab6);                                    // *side note* ArrayList has built-in toString method for printing

        outputFile.println("The median of the array list is: " + getMedian(sortArray));   // determine the median print into a file,

        outputFile.close();                                                 // close file

    }

    public static double getMedian(double[] array) {
        if (array.length % 2 == 0) {
            // if the length of array is even
            //      for even number of an array, the median will be the average of the two medians
            //      the array length divided 2 will be the element of the second median therefore
            //      we also need to get the element before the second median as well for the calculation
            return (array[array.length / 2] + array[(array.length / 2) - 1]) / 2;
        }
        else {
            // else the length of array is odd
            //      the length of an array will be an integer and an integer divided by an integer
            //      will be an integer therefore no rounding will occur, this calculations allows to get
            //      the middle index
            return array[array.length / 2];
            }
    }

    // selection sort for double array data type
    public static void selectionSort(double[] array) {
        double minValue;

        /*
        The below cycle repeats the swapping until all the numbers are in the right location,
        for, instance, when the minimum value is placed at position 0, the cycle starts again at position 1 and follows the same logic.
         */
        int minIndex, startScan, index;
        for (startScan = 0; startScan < (array.length -1); startScan++) {

            //sets the initial position at 0 and attributes the initial minimum value to the one at that position
            minIndex = startScan;
            minValue = array[startScan];

            //searches for the minimum value in the array
            for (index = startScan + 1; index < array.length; index++) {
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }

            // if minimum value has been found, swap it with element at position 0
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
    }

    // selection sort for integer array data type
    public static void selectionSort(int[] array) {
        int minIndex, startScan, index, minValue;

        /*
        The below cycle repeats the swapping until all the numbers are in the right location,
        for, instance, when the minimum value is placed at position 0, the cycle starts again at position 1 and follows the same logic.
         */
        for (startScan = 0; startScan < (array.length -1); startScan++) {
            //sets the initial position at 0 and attributes the initial minimum value to the one at that position
            minIndex = startScan;
            minValue = array[startScan];

            //searches for the minimum value in the array
            for (index = startScan + 1; index < array.length; index++) {
                if (array[startScan] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }
            // if minimum value has been found, swap it with element at position 0
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
    }
}

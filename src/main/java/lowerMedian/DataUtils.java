package lowerMedian;

import java.util.Random;

public class DataUtils {

  /**
   * Handles data operations, like generating random data and
   * throwing data away in an array
   */
  public DataUtils() {

  }

  /**
   * Generates n random integers
   * @param size the number of integers to generate
   * @param max the maximum integer value the function can generate
   * @return an array containing the randomly generated integers
   */
  public static int[] generateData(int size, int max) {
    Random rand = new Random();

    // array to hold random integers
    int[] randomArray = new int[size];

    // fill array
    for(int i = 0; i < size; i++) {
      randomArray[i] = rand.nextInt(max);
    }

    return randomArray;
  }
}

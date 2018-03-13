package lowerMedian;

import java.util.Random;

public class SelectionPartition {

  public SelectionPartition() {

  }

  /**
   * Finds the lower median of the input array
   * Bootstrap for selection-by-partition recursive call
   * @param inputArray array of integers in which we find the lower median
   * @return the array, in the sorted-state when the lower median is found
   */
  public static int findLowerMedian(int[] inputArray) {
    // return the lower median, or the ceil(n/2)th element
    int lowerMedianIndex = inputArray.length / 2;
    if(inputArray.length % 2 == 1) {
        lowerMedianIndex = (inputArray.length + 1) / 2;
    }

    return select(inputArray, 0, inputArray.length - 1, lowerMedianIndex);
  }

  private static int select(int[] inputArray, int pivot, int end, int desiredElementIndex) {
    // base case, we've gotten start and end indexes at the same point
    if(pivot == end) return inputArray[pivot];

    // otherwise, partition the array into two subarrays
    int newPivot = randomizedPartition(inputArray, pivot, end);
    int numElementsInLowSide = newPivot - pivot + 1;

    // check if we're at our desired element
    if(desiredElementIndex == numElementsInLowSide) {
      return inputArray[newPivot];
    }

    // check if element is in lower array
    else if (desiredElementIndex < numElementsInLowSide){
      return select(inputArray, pivot, newPivot - 1, desiredElementIndex);
    }

    // element still in upper array, work to the right
    // adjust desired element index
    else {
      return select(inputArray, newPivot + 1, end, desiredElementIndex - numElementsInLowSide);
    }
  }

  private static int randomizedPartition(int[] inputArray, int pivot, int end) {
    // generate random pivot index between start and end
    Random rand = new Random();
    int randomPivot = rand.nextInt(end - pivot + 1) + pivot;

    // swap random element to the pivot
    swap(inputArray, randomPivot, pivot);

    // randomization complete, conduct normal partition
    return partition(inputArray, pivot, end);
  }

  /**
   * Rearranges subarray in place
   * All elements to left of pivot are less or equal to the pivot
   * @param inputArray the array to partition
   * @param start the index of the pivot (all elements to left less than or equal to pivot in array)
   * @param end the final index of the array
   * @return the new pivot index, updated when elements are placed before it
   */
  private static int partition(int[] inputArray, int start, int end) {
    // choose last element as pivot value
    int pivotValue = inputArray[end];

    // if current pivot (start) is less than or equal to the chosen pivot value,
    // the chosen pivot value is moved to after the current pivot (start) and becomes the new current pivot
    // this upholds having all elements to the left of the current pivot less than or equal to the pivot
    int tempPivotPointer = start - 1;
    for(int i = tempPivotPointer + 1; i <= end - 1; i++) {

      // chosen pivot value greater than current pivot value
      // make new pivot value by swapping with element immediately after current pivot and incrementing
      // pointer to the pivot
      if(inputArray[i] <= pivotValue) {
        tempPivotPointer+= 1;
        swap(inputArray, tempPivotPointer, i);
      }
    }

    // swap pivot element with leftmost element that is greater than pivot value
    swap(inputArray, tempPivotPointer + 1, end);

    // return pivot index
    return tempPivotPointer + 1;
  }

  /**
   * Swaps elements i and j in an array
   * @param inputArray array where swapping should take place
   * @param i index of one element to be swapped
   * @param j index of one element to be swapped
   */
  private static void swap(int[] inputArray, int i, int j){
    int tmp = inputArray[i];
    inputArray[i] = inputArray[j];
    inputArray[j] = tmp;
  }

}

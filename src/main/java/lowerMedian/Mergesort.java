package lowerMedian;


public class Mergesort {

  /**
   * Bootstrap for recursion call; seeds the recursion to make things simpler
   */
  public static long mergesort(int[] intArray, int left, int right) {
    int[] tmp = new int[right - left + 1];
    return mergesortWorker(intArray, tmp, left, right);
  }

  /**
   * Sorts the input array using the mergesort algorithm. Also calculates the number of inversions
   * in the array.
   *
   * @param intArray an array of integers
   * @param tmp a temporary array for processing during sorting
   * @param left, the
   * @return the number of inversions in the input array
   */
  public static long mergesortWorker(int[] intArray, int[] tmp, int left, int right) {
    int split = 0;
    long inversionCount = 0;

    // if left is greater than or equal to right, then array has at most one element (i.e. is sorted)
    if (left < right) {
      // divide array into two
      split = (int) Math.floor((left + right) / 2);

      // call mergesort on both sides on the array
      inversionCount = mergesortWorker(intArray, tmp, left, split);
      inversionCount += mergesortWorker(intArray, tmp, split + 1, right);

      // merge the sorted sides
      inversionCount += merge(intArray, tmp, left, split + 1, right);
    }

    return inversionCount;
  }

  public static long merge(int[] intArray, int[] tmp, int left, int split, int right) {

    long inversionCount = 0;

    // create variables to serve as 'moving pointers' to both left and right arrays while processing
    // also create variable as moving pointer for resultant array
    int leftPointer = left;
    int rightPointer = split;
    int resultantPointer = left;

    // check which current element is larger
    // place larger element into correct position in resultant array
    // go until one of the side arrays has been depleted, then just copy over remaining elements from other side
    // since they have to be in order
    while ((leftPointer < split) && (rightPointer <= right)) {
      // element in left is larger (in case of equal we'll just copy right by default)
      if (intArray[leftPointer] < intArray[rightPointer]) {
        tmp[resultantPointer] = intArray[leftPointer];
        resultantPointer++;
        leftPointer++;
      } else {
        tmp[resultantPointer] = intArray[rightPointer];
        resultantPointer++;
        rightPointer++;

        // each check until now was the result of an inversion; increase the count accordingly
        inversionCount = inversionCount + split - leftPointer;
      }

    }

    // copy remaining elements (left)
    while(leftPointer < split) {
      tmp[resultantPointer] = intArray[leftPointer];
      resultantPointer++;
      leftPointer++;
  }

    // copy remaining elements (right)
    while(rightPointer <= right) {
      tmp[resultantPointer] = intArray[rightPointer];
      resultantPointer++;
      rightPointer++;
    }

    // fill original array with sorted elements
    for(int i = left; i <= right; i++) {
      intArray[i] = tmp[i];
    }

    return inversionCount;
}

}

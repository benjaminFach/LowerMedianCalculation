package lowerMedian;

import org.junit.Assert;
import org.junit.Test;

public class TestMergesort {

  @Test
  public void testMergesort() {
    int[] testArray = new int[]{11,6,9,22};
    long inversions = Mergesort.mergesort(testArray, 0, testArray.length - 1);

    Assert.assertArrayEquals("Resulting arrays are equal", new int[]{6, 9, 11, 22}, testArray);
    Assert.assertEquals("Inversion calculation", 2, inversions);

  }


}

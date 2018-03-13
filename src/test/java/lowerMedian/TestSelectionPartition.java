package lowerMedian;

import org.junit.Assert;
import org.junit.Test;

public class TestSelectionPartition {

  @Test
  public void testSelectionPartition() {
    int[] testArray = new int[]{17,2,3,9,1};
    int lowerMedian = SelectionPartition.findLowerMedian(testArray);

    Assert.assertEquals("Correctness of median",3 , lowerMedian);

  }


}

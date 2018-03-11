package lowerMedian;

import java.util.ArrayList;
import java.util.List;

public class Driver {

  private static class SimulationResult {

    /** the number of inversions in the input array before running selection/partition **/
    private int numInversionsBefore;

    /** the number of inversions in the input array after running selection/partition **/
    private int numInversionsAfter;

    private long mergesortRuntime;
    private long selectionRuntime;

    protected SimulationResult(int numInversionsBefore, int numInversionsAfter, long mergesortRuntime, long selectionRuntime) {
      this.numInversionsBefore = numInversionsBefore;
      this.numInversionsAfter = numInversionsAfter;
      this.mergesortRuntime = mergesortRuntime;
      this.selectionRuntime = selectionRuntime;
    }

  }

  public static void main(String[] args) {
    // ArrayList to hold results of simulations for
    List<SimulationResult> results  = new ArrayList<>();

    // loop through simulation with different data sizes
    for(int i = 100; i <= 100000; i = i*10) {
      System.out.format("Running simulation on %d data points%n", i);
      final int[] data = DataUtils.generateData(i, 1000000);

      // begin tracking runtime of mergesort
      final long mergesortStartTime = System.nanoTime();

      // the merge sort will simultaneously calculate the number of inversions
      int numInversionsBefore = Mergesort.mergesort(data.clone());

      // end tracking runtime of mergesort
      final long mergesortRunTime = System.nanoTime() - mergesortStartTime;

      // find the median with selection/partition
      final long selectionStartTime = System.nanoTime();
      SelectionPartition.findLowerMedian(data);

      // calculate time to complete selection
      final long selectionRuntime = System.nanoTime() - selectionStartTime;

      // run mergesort on the resulting selection/parition array to get the number of inversions
      // still remaining after finding the lower median
      int numInversionsAfter = Mergesort.mergesort(data);

      // store the result
      results.add(new SimulationResult(numInversionsBefore, numInversionsAfter, mergesortRunTime, selectionRuntime));

    }
  }

}

package lowerMedian;

import java.util.ArrayList;
import java.util.List;

public class Driver {

  private static class SimulationResult {

    /**
     * the number of inversions in the input array before running selection/partition
     **/
    private long numInversionsBefore;

    /**
     * the number of inversions in the input array after running selection/partition
     **/
    private long numInversionsAfter;

    private long selectionRuntime;
    private long mergesortRuntime;
    private int selectionMedian;
    private int mergesortMedian;

    protected SimulationResult(int selectionMedian, int mergesortMedian, long selectionRuntime,
        long mergesortRuntime, long numInversionsBefore, long numInversionsAfter) {
      this.selectionMedian = selectionMedian;
      this.mergesortMedian = mergesortMedian;
      this.selectionRuntime = selectionRuntime;
      this.mergesortRuntime = mergesortRuntime;
      this.numInversionsBefore = numInversionsBefore;
      this.numInversionsAfter = numInversionsAfter;
    }

    @Override
    public String toString() {
      return String.format(
          "Selection median: %d\nMergesort Median: %d\nSelection runtime: %d ns\nMergesort runtime: %d ns\nInversion ratio: %f\n",
          selectionMedian, mergesortMedian, selectionRuntime, mergesortRuntime,
          ((double) numInversionsBefore / (double) numInversionsAfter));
    }

  }


  public static void main(String[] args) {
    // ArrayList to hold results of simulations for
    List<SimulationResult> results = new ArrayList<>();

    // loop through simulation with different data sizes
    for (int i = 100; i <= 100000; i = i * 10) {
      final int[] data = DataUtils.generateData(i, 1000000);
      final int[] dataClone = data.clone();

      // begin tracking runtime of mergesort
      final long mergesortStartTime = System.nanoTime();

      // the merge sort will simultaneously calculate the number of inversions
      long numInversionsBefore = Mergesort.mergesort(dataClone, 0, data.length - 1);
      int mergesortMedian = dataClone[(data.length / 2) - 1];

      // end tracking runtime of mergesort
      final long mergesortRunTime = System.nanoTime() - mergesortStartTime;

      // find the median with selection/partition
      final long selectionStartTime = System.nanoTime();
      int selectionMedian = SelectionPartition.findLowerMedian(data);

      // calculate time to complete selection
      final long selectionRuntime = System.nanoTime() - selectionStartTime;

      // run mergesort on the resulting selection/parition array to get the number of inversions
      // still remaining after finding the lower median
      long numInversionsAfter = Mergesort.mergesort(data, 0, data.length - 1);

      // store the result
      results.add(new SimulationResult(selectionMedian, mergesortMedian, selectionRuntime,
          mergesortRunTime, numInversionsBefore, numInversionsAfter));
    }

    for(SimulationResult result : results) {
      System.out.println(result.toString());
    }

  }

}

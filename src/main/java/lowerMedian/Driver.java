package lowerMedian;

import java.util.ArrayList;
import java.util.List;

public class Driver {

  private static class SimulationResult {

    /** the number of inversions in the input array before running selection/partition **/
    private long numInversionsBefore;

    /** the number of inversions in the input array after running selection/partition **/
    private long numInversionsAfter;

    private long mergesortRuntime;
    private long selectionRuntime;

    protected SimulationResult(long numInversionsBefore, long numInversionsAfter, long mergesortRuntime, long selectionRuntime) {
      this.numInversionsBefore = numInversionsBefore;
      this.numInversionsAfter = numInversionsAfter;
      this.mergesortRuntime = mergesortRuntime;
      this.selectionRuntime = selectionRuntime;
    }

    @Override
    public String toString() {
      return String.format("Mergesort runtime: %d ns\nSelection runtime: %d ns\nInversion ratio: %d\n",
          mergesortRuntime, selectionRuntime, numInversionsBefore);
    }

  }

  public static void main(String[] args) {
    // ArrayList to hold results of simulations for
    List<SimulationResult> results  = new ArrayList<>();

    // loop through simulation with different data sizes
    for(int i = 100; i <= 100000; i = i*10) {
      final int[] data = DataUtils.generateData(i, 1000000);

      // begin tracking runtime of mergesort
      final long mergesortStartTime = System.nanoTime();

      // the merge sort will simultaneously calculate the number of inversions
      long numInversionsBefore = Mergesort.mergesort(data.clone(), 0, data.length - 1);

      // end tracking runtime of mergesort
      final long mergesortRunTime = System.nanoTime() - mergesortStartTime;

      // find the median with selection/partition
      final long selectionStartTime = System.nanoTime();
      SelectionPartition.findLowerMedian(data);

      // calculate time to complete selection
      final long selectionRuntime = System.nanoTime() - selectionStartTime;

      // run mergesort on the resulting selection/parition array to get the number of inversions
      // still remaining after finding the lower median
      long numInversionsAfter = Mergesort.mergesort(data, 0 , data.length - 1 );

      // store the result
      results.add(new SimulationResult(numInversionsBefore, numInversionsAfter, mergesortRunTime, selectionRuntime));
    }

    for(SimulationResult simResult : results) {
      System.out.println(simResult.toString());
    }

  }

}

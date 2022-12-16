package AllocationPolicy;

import Memory.Partition;

import java.util.Collections;
import java.util.Vector;

public class WorstFit implements IAllocator {
    @Override
    public void sort(Vector<Partition> partitions) {
        // Sort Vector in Descending Order

        Collections.sort(partitions);
        Collections.reverse(partitions);
    }
}

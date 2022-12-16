package AllocationPolicy;

import Memory.Partition;
import Memory.Process;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class BestFit implements IAllocator {
    @Override
    public void sort(Vector<Partition> partitions) {
        // Sort Vector in Ascending Order
        Collections.sort(partitions);
    }
}

package AllocationPolicy;

import Memory.Partition;
import Memory.Process;

import java.util.Vector;

public interface IAllocator {
    void sort(Vector<Partition> partitions);

}

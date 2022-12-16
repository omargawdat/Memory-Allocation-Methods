package Memory;

import AllocationPolicy.BestFit;
import AllocationPolicy.IAllocator;

import java.util.Vector;

public class Memory {
    IAllocator allocationPolicy;
    public Vector<Partition> partitions;

    public Memory(Vector<Partition> partitions) {
        this.partitions = partitions;
        this.allocationPolicy = new BestFit(); // Default
    }

    public void setAllocationPolicy(IAllocator allocationPolicy) {
        this.allocationPolicy = allocationPolicy;
    }

    public Vector<Process> allocate(Vector<Process> jobProcesses) {
        allocationPolicy.sort(partitions);

        Vector<Process> unallocProcess = new Vector<>();
        for (Process process : jobProcesses) {
            boolean isAllocated = false;
            for (int j = 0; j < partitions.size(); j++) {
                Partition partition = partitions.get(j);
                if (partition.isEmpty() && partition.size >= process.size) {
                    int extraSpace = partition.size - process.size;
                    partition.setProcess(process);
                    if (extraSpace > 0)
                        partitions.insertElementAt(new Partition("External Fragment", extraSpace), j + 1);
                    isAllocated = true;
                    break;
                }
            }
            if (!isAllocated)
                unallocProcess.add(process);
        }
        return unallocProcess;
    }

    public void compact() {
        int total = 0;
        for (Partition p : partitions) {
            if (p.getProcess() == null)
                total += p.size;
        }
        // Remove all empty spaces
        partitions.removeIf(i -> i.getProcess() == null);
        if (total > 0)
            // Add the new Compacted Space
            partitions.add(new Partition("Compaction Partition: ", total));
    }
}

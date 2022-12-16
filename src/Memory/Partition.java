package Memory;

public class Partition implements Comparable<Partition> {
    public String name;
    public int size;

    Process process;

    public Partition(String name, int size) {
        this.name = name;
        this.size = size;
        this.process = null;
    }

    public void setProcess(Process process) {
        this.process = process;
        this.size = process.size;
    }

    public Process getProcess() {
        return process;
    }

    public boolean isEmpty() {
        return this.process == null;
    }


    @Override // For Sorting
    public int compareTo(Partition partition) {
        if (this.size > partition.size)
            return 1;
        else
            return -1;
    }
}
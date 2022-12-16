import AllocationPolicy.BestFit;
import AllocationPolicy.FirstFit;
import AllocationPolicy.WorstFit;
import Component.Printer;
import Component.R;
import Memory.Memory;
import Memory.Partition;
import Memory.Process;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        // Take Memory.Memory Partitions From the User
        Vector<Partition> partitions = PartitionInput();
        // Take Processes From the User
        Vector<Process> processes = ProcessInput();

        // Select Allocation Method
        int choice;
        do {
            System.out.println("Memory Allocation Policy: ");
            System.out.println("[1] First Fit");
            System.out.println("[2] Best Fit");
            System.out.println("[3] Worst Fit");
            choice = R.readInt("Choice: ", 1, 4);
            System.out.println("PRESS Any Other Key For Exit..");

            // Send deep to keep user input from change as we use this inputs many times
            Memory memory = new Memory(getDeepPartitions(partitions));
            switch (choice) {
                case 1 -> {
                    memory.setAllocationPolicy(new FirstFit());
                }
                case 2 -> {
                    memory.setAllocationPolicy(new BestFit());
                }
                case 3 -> {
                    memory.setAllocationPolicy(new WorstFit());
                }
                default -> System.exit(0);
            }

            // Allocate Processes into Partitions
            Vector<Process> unallocatedProcesses = memory.allocate(getDeepProcesses(processes));
            System.out.println(unallocatedProcesses);

            // Print Partitions
            Printer.printMemoryInfo(memory.partitions, unallocatedProcesses);

            // Compact Memory?
            System.out.println("Compact Memory?");
            System.out.println("[1] YES");
            System.out.println("[2] NO");
            int choice2 = R.readInt("Choice:", 1, 2);
            if (choice2 == 1) {
                memory.compact();
                Vector<Process> unallocatedProcesses2 = memory.allocate(unallocatedProcesses);
                Printer.printMemoryInfo(memory.partitions, unallocatedProcesses2);
            }
        } while (true);
    }


    public static Vector<Partition> PartitionInput() {
        int partitions_count = R.readInt("Number of Partitions:", 1, 10000);
        Vector<Partition> partitions = new Vector<>();
        for (int i = 0; i < partitions_count; i++) {
            System.out.println("Partition [" + (i + 1) + "] : ");
            System.out.print("Name: ");
            String name = new Scanner(System.in).nextLine();
            int size = R.readInt("Size: ", 1, 10000);
            partitions.add(new Partition(name, size));
        }
        return partitions;
    }

    public static Vector<Process> ProcessInput() {
        int processes_count = R.readInt("Number of Processes:", 1, 10000);
        Vector<Process> processes = new Vector<>();
        for (int i = 0; i < processes_count; i++) {
            System.out.println("- Process [" + (i + 1) + "] -");
            System.out.print("Name: ");
            String name = new Scanner(System.in).nextLine();
            int size = R.readInt("Size: ", 1, 10000);
            processes.add(new Process(name, size));
        }
        return processes;
    }

    public static Vector<Partition> getDeepPartitions(Vector<Partition> partitions) {
        Vector<Partition> deepPartitions = new Vector<Partition>();
        for (Partition p : partitions)
            deepPartitions.add(new Partition(p.name, p.size));
        return deepPartitions;
    }

    public static Vector<Process> getDeepProcesses(Vector<Process> processes) {
        Vector<Process> deepProcesses = new Vector<Process>();
        for (Process p : processes)
            deepProcesses.add(new Process(p.name, p.size));
        return deepProcesses;
    }
}
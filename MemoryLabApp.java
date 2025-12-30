import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Lab: JVM Memory Analysis
 * <p>
 * TODO: Complete this application to demonstrate memory behavior
 * <p>
 * Run with: java -Xms128m -Xmx256m -Xlog:gc*:file=gc.log MemoryLabApp
 */
public class MemoryLabApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== JVM Memory Lab ===");
        printMemoryStatus("Initial");

        List<byte[]> memoryBlocks = new ArrayList<>();

        // TODO: Implement the following
        // 1. Allocate memory in a loop (e.g., 1MB chunks)
        // 2. Print memory status after each allocation
        // 3. Handle OutOfMemoryError gracefully
        // 4. Add a small delay between allocations for observation

        // Your code here:

        try {
            while (true) {
                byte[] block = new byte[1000 * 1000];
                memoryBlocks.add(block);

                printMemoryStatus("Blocks: %5d".formatted(memoryBlocks.size()));
                TimeUnit.MILLISECONDS.sleep(17);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Exception: " + e.getMessage());
        }

        printMemoryStatus("Final");
    }

    /**
     * Helper method to print current memory status
     */
    private static void printMemoryStatus(String label) {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        System.out.printf("[%s] Max: %d MB, Total: %d MB, Used: %d MB, Free: %d MB%n",
                label, maxMemory, totalMemory, usedMemory, freeMemory);
    }
}

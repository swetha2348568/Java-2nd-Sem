import java.util.Scanner;

public class CoinCombination {

    private static int totalCombinations;

    // Set total combinations in a thread-safe manner
    public static synchronized void setTotalCombinations(int combinations) {
        totalCombinations = combinations;
    }

    // Get total combinations in a thread-safe manner
    public static synchronized int getTotalCombinations() {
        return totalCombinations;
    }

    // Count coin combinations using dynamic programming
    public static int countCoinCombinations(int[] coinValues, int targetSum) {
        int[] dp = new int[targetSum + 1];

        dp[0] = 1;

        // Iterate through each coin value
        for (int coin : coinValues) {
            // Update combinations for each sum value
            for (int i = coin; i <= targetSum; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[targetSum]; // Return the total combinations for the target sum
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("Coin Combinations");
        System.out.println("-----------------");

        // Get the number of coins from the user
        System.out.print("Enter the number of coins: ");
        int numCoins = input.nextInt();

        int[] coinValues = new int[numCoins];

        // Get values for each coin from the user
        for (int i = 0; i < numCoins; i++) {
            System.out.print("Enter the value of coin " + (i + 1) + ": ");
            coinValues[i] = input.nextInt();
        }

        // Get the target sum from the user
        System.out.print("Enter the target sum: ");
        int sumValue = input.nextInt();

        input.close();

        // Thread for handling user input and calculating combinations
        Thread inputThread = new Thread(() -> {
            int combinations = countCoinCombinations(coinValues, sumValue);
            setTotalCombinations(combinations);
        });

        // Thread for displaying the computed total combinations
        Thread displayThread = new Thread(() -> {
            try {
                inputThread.join(); // Waits for the input thread to complete its task
                int combinations = getTotalCombinations();
                System.out.println(" ");
                System.out.println("Total Possible Ways or Combinations: " + combinations);
                System.out.println(" ");
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });

        // Start both threads
        inputThread.start();
        displayThread.start();
    }
}

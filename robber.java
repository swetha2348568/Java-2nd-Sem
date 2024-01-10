import java.util.Arrays;
abstract class robber{

    // Abstract method to display some randomized content

    abstract void RobbingClass();

    // Abstract method declaration for all type of houses

    abstract int RowHouses(int[] houses);
    abstract int SquareHouses(int[] houses);
    abstract int RoundHouses(int[] houses);
    abstract int MultiHouses(int[] squareHouses, int[] roundHouses, int[] rowHouses); // varargs

}

class Prof_Robber extends robber{

    void RobbingClass(){
        System.out.println("Solving House Robber Problem");
    }

    // Logic for row houses

    int RowHouses(int[] houses){


        // If there is only 1 element, just return it
        if(houses.length<2){
            return houses[0];
        }

        // Creation of array to store the maximum loot at each index
        // Starting the process of memoization

        int[] dp = new int[houses.length];

        // Memoize maximum loots at first 2 indexes
        dp[0] = houses[0]; // First house just choose whatever is the value
        dp[1] = Math.max(houses[0],houses[1]); // Maximum of two values

        // Use them to fill the complete array
        // loop from 2 till end

        for(int i=2;i<houses.length;i++){
            // Core Logic
            dp[i] = Math.max(dp[i-2] + houses[i], dp[i-1]); // Formula - Max(total_loot[n-2]+loot[n],total_loot[n-1])
        }
        return dp[houses.length-1]; // returning the last value of the array, that will be the maximum profit

    }

    // Logic for round houses

    int RoundHouses(int[] houses){

        if(houses.length<2){
            return houses[0];
        }

        // Creation of array to store the maximum loot at each index
        // Starting the process of memoization

        int[] dp = new int[houses.length];

        // Memoize maximum loots at first 2 indexes
        dp[0] = houses[0]; // First house just choose whatever is the value
        dp[1] = Math.max(houses[0],houses[1]); // Maximum of two values

        // Used copyOfRange to extract specific elements from the original array
        return Math.max(RowHouses(Arrays.copyOfRange(houses, 0, houses.length - 1)), // Excluding the last element
                RowHouses(Arrays.copyOfRange(houses, 1, houses.length)) // Excluding the first element
        );
    }

    // Square houses follows the same logic as row houses

    int SquareHouses(int[] houses){

        return RowHouses(houses);

    }

    // Logic for multi houses

    int MultiHouses(int[] squareHouses, int[] roundHouses, int[] rowHouses){

        int maxSquare = SquareHouses(squareHouses);
        int maxRound = RoundHouses(roundHouses);
        int maxRow = RowHouses(rowHouses);

        return maxRound+maxRow+maxSquare;


    }



        // Main Method
        public static void main(String[] args) {

            Prof_Robber abc = new Prof_Robber();

            int[] rowHouses = {3,2,6,0};
            System.out.println("");
            abc.RobbingClass();
            System.out.println("----------------------------");
            System.out.println("Row Houses Result: " + abc.RowHouses(rowHouses));


            int[] roundHouses = {1,6,3,7};
            System.out.println("Round Houses Result: " + abc.RoundHouses(roundHouses));

            int[] squareHouses = {12,10,22,7};
            System.out.println("Square Houses Result: " + abc.SquareHouses(squareHouses));


            int[] rowTypeHouse = {3,3,8,2};
            int[] squareTypeHouse = {5,12,15,6};
            int[] roundTypeHouse = {8,9,12,5};


            System.out.println("Provided input for MultiHouse Building");
            System.out.println("--------------------------------------");
            int totalRobbedAmount = abc.MultiHouses(roundTypeHouse, squareTypeHouse, rowTypeHouse);
            System.out.println("Total robbed amount by abc without triggering alarms in multi house building : " + totalRobbedAmount);



        }


    }




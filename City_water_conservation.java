interface WaterConservationSystem{

    int calculateTrappedWater(int[] blockHeights);
}

// Creation of abstract class

abstract class RainySeasonConservation implements WaterConservationSystem{

    public abstract int calculateTrappedWater(int[] blockHeights);
}

// This class holds the core logic

public class City_water_conservation extends RainySeasonConservation {

    // Method

    public int calculateTrappedWater(int[] a){

        int res = 0; // Return the trapped water

        // Finding left boundary

        for(int i = 1; i<=a.length-2;i++){
            int lb = a[i];

            // This loop start from the leftmost element, and go till i
            for(int j=0; j<i;j++){

                // To find a higher height
                if(a[j]>lb){
                    lb = a[j]; // update lb
                }
            }

            // Finding right boundary

            int rb = a[i];

            // This loop starts from i+1 till end
            // Traversing right side

            for(int j=i+1; j<a.length;j++){
                if(a[j]>rb){
                    rb = a[j]; // update rb
                }
            }
            // Finding the water level

            int wl;   // Variable for water level

            if(rb>lb){
                wl = lb;
            }
            else{
                wl = rb;
            }

            // Finding the trapped level

            int tw = wl - a[i]; // Water level - height
            res = res + tw; // result + tw
        }
        return res;
    }
   // class City_water_conservation {    // Main Method
    public static void main(String[] args) {

        // Creation of integer array
        int[] blockheight1 = {3,0,0,2,0,4};
        int[] blockheight2 = {3,0,2,0,4};
        City_water_conservation blr = new City_water_conservation();
        System.out.println("");
        System.out.println("Water Conservation Mechanism");
        System.out.println("----------------------------");
        System.out.println("Volume of water that can be conserved between these blocks : "+blr.calculateTrappedWater(blockheight1));
        System.out.println("Volume of water that can be conserved between these blocks : "+blr.calculateTrappedWater(blockheight2));
        System.out.println("");
    }

}



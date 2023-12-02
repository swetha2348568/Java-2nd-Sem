import java.util.Scanner;

public class Performance_2348568 {
     int[] marks;

    // Constructor
    public Performance_2348568() {
        marks = new int[10];
    }

    // Method to read marks into the array
    public void read_marks() {
        Scanner obj = new Scanner(System.in);

        System.out.println("Enter the marks of 10 students:");

        for (int i = 0; i < 10; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            marks[i] = obj.nextInt();

            // Ensure marks are between 0 and 100
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid input. Marks should be between 0 and 100. Please try again.");
                i--; // Repeat input for the same student
            }
        }
    }

    // Method to find the highest mark
    public int high_mark() {
        int max_mark = marks[0];

        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > max_mark) {
                max_mark = marks[i];
            }
        }

        return max_mark;
    }

    // Method to find the least mark
    public int least_mark() {
        int min_mark = marks[0];

        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < min_mark) {
                min_mark = marks[i];
            }
        }

        return min_mark;
    }

    // Method to find the mode
    public int find_mode() {
        int[] freq = new int[101]; // Assuming marks are between 0 and 100

        for (int mark : marks) {
            freq[mark]++;
        }

        int mode = 0;
        int max_freq = 0;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] >= max_freq) {
                max_freq = freq[i];
                mode = i;
            }
        }

        return mode;
    }

    // Method to find the frequency at mode
    public int modal_freq() {
        int mode = find_mode();
        int mode_freq = 0;

        for (int mark : marks) {
            if (mark == mode) {
                mode_freq++;
            }
        }

        return mode_freq;
    }

    // Method to display the results
    public void display() {
        System.out.println("Highest Mark: " + high_mark());
        System.out.println("Least Mark: " + least_mark());
        System.out.println("Mode: " + find_mode());
        System.out.println("Mode Frequency: " + modal_freq());
    }

    public static void main(String[] args) {
        Performance_2348568 obj = new Performance_2348568();
        obj.read_marks();
        obj.display();
    }
}

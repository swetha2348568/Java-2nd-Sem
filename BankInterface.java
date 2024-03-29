import java.util.Scanner;

// Abstract class 'Bank'

abstract class Bank {
    abstract double getBalance(); 
    abstract double getInterestRate(); 
}

// BankA

class BankA extends Bank {
    private double balance;

    BankA(double balance) {
        this.balance = balance;
    }

    @Override
    double getBalance() {
        return balance;
    }

    @Override
    double getInterestRate() {
        return 0.07;
    }
}

// BankB

class BankB extends Bank {
    private double balance;

    BankB(double balance) {
        this.balance = balance;
    }

    @Override
    double getBalance() {
        return balance;
    }

    @Override
    double getInterestRate() {
        return 0.074;
    }
}

//  BankC

class BankC extends Bank {
    private double balance;

    BankC(double balance) {
        this.balance = balance;
    }

    @Override
    double getBalance() {
        return balance;
    }

    @Override
    double getInterestRate() {
        return 0.079;
    }
}

class BankInterface {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            

            System.out.print("Enter initial balance for Bank A: ");
            double initialBalanceA = scanner.nextDouble();
            BankA bankA = new BankA(initialBalanceA);
            System.out.print("Enter initial balance for Bank B: ");
            double initialBalanceB = scanner.nextDouble();
            BankB bankB = new BankB(initialBalanceB);
            System.out.print("Enter initial balance for Bank C: ");
            double initialBalanceC = scanner.nextDouble();
            BankC bankC = new BankC(initialBalanceC);

            // Displaying balance and interest rate for each bank

            System.out.println("\nBank A:");
            System.out.println("Balance: " + bankA.getBalance());
            System.out.println("Interest Rate: " + bankA.getInterestRate());

            System.out.println("\nBank B:");
            System.out.println("Balance: " + bankB.getBalance());
            System.out.println("Interest Rate: " + bankB.getInterestRate());

            System.out.println("\nBank C:");
            System.out.println("Balance: " + bankC.getBalance());
            System.out.println("Interest Rate: " + bankC.getInterestRate());
        }
    }
}


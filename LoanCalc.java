/**
* Computes the periodical payment necessary to re-pay a given loan.
*/
public class LoanCalc {
	
	static double epsilon = 0.001;  // The computation tolerance (estimation error)
	static int iterationCounter;    // Monitors the efficiency of the calculation
	
    /** 
     * Gets the loan data and computes the periodical payment.
     * Expects to get three command-line arguments: sum of the loan (double),
     * interest rate (double, as a percentage), and number of payments (int).  
     */
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan sum = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		
		// Computes the periodical payment using brute force search
		System.out.print("Periodical payment, using brute force: ");
		System.out.printf("%.2f", bruteForceSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("Periodical payment, using bi-section search: ");
		System.out.printf("%.2f", bisectionSolver(loan, rate, n, epsilon));
		System.out.println();
		System.out.println("number of iterations: " + iterationCounter);
	}
	
	/**
	* Uses a sequential search method  ("brute force") to compute an approximation
	* of the periodical payment that will bring the ending balance of a loan close to 0.
	* Given: the sum of the loan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
    	// Sets initial payment as loan/n
		// while loan is greater than 0, checks if the endBalance we got using current 
		// payment is smaller than epsilon.
		// if so- it returns the current payment value
		// else- it adds epsilon to the current payment value and tries again
		double payment = loan/n; 
		while (loan > 0) {
			if (endBalance(loan, rate, n, payment) < epsilon) {
				return payment; 
			} else {
				payment = payment + epsilon; 
				iterationCounter++; 
			}
		}
    	return payment;
    }
    
    /**
	* Uses bisection search to compute an approximation of the periodical payment 
	* that will bring the ending balance of a loan close to 0.
	* Given: the sum of the loan, the periodical interest rate (as a percentage),
	* the number of periods (n), and epsilon, a tolerance level.
	*/
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0; 
		double L = 1;  
		double H = loan;  
		double payment = loan / 2; 
		while ( H - L > epsilon) {
			if (endBalance(loan, rate, n, payment) > 0 ) {
				L = payment;
				H = H; 
			} else {
				H = payment; 
				L = L; 
			}
			payment = ( H + L ) / 2;
			iterationCounter++; 
		}
    	return payment;
    }
	
	/**
	* Computes the ending balance of a loan, given the sum of the loan, the periodical
	* interest rate (as a percentage), the number of periods (n), and the periodical payment.
	*/
	private static double endBalance(double loan, double rate, int n, double payment) {
		// Note that we use a different variable Loan in this function, so we couldn't
		// change the value of loan given by the user while using the function
		double Loan = loan; 
		for (int i = 0; i < n; i++) {
			Loan = (Loan - payment) * (1 + rate); 
		}
    	return Loan;
	}
}
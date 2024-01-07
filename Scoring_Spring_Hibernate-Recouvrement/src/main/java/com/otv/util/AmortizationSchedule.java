package com.otv.util;

import java.text.NumberFormat;

public class AmortizationSchedule {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new AmortizationSchedule(5,1000,12).printAmoritizationSchedule();
	}

	private double interest ;
	private double principal ;
	private int numPayments ;
	private double balance ;
	private double payment ;

	
	public AmortizationSchedule (double i, double p, int n) {
	interest = i/1200 ;
	principal = p ;
	numPayments = n ;
	}

	//Add a member function to calculate and output the schedule.

	public void printAmoritizationSchedule () {

	//Declare variables to store calculations.

	double curInterest = 0, curPrincipal = 0 ;
	double totalPayments = 0, totalPrincipal = 0, totalInterest = 0 ;

	//Calculate the payment, set the current balance to the amount of the loan and print column headers for a comma-delimited table.

	payment = (principal * interest) / (1 - Math.pow((1 + interest), -numPayments)) ;
 
	balance = principal ; curInterest = 0 ;
	System.out.println ("Period, Payment, Principal, Interest, Balance") ;
	System.out.println (1+" "+payment+" "+principal+" "+interest+" "+balance);

	//Loop through each payment period.

	for (int period = 1 ; period <= numPayments ; period++) {

	//Calculate the interest portion of the current payment.

	curInterest = balance * interest ;

	//For the final payment, adjust for rounding by setting the payment to the current interest portion plus the outstanding balance.

	if (period == numPayments) {

	payment = balance + curInterest ;

	}

	//Calculate the current principal payment and the current balance. Print out the results.

	curPrincipal = payment - curInterest ;

	balance -= curPrincipal ;
	
	NumberFormat format=NumberFormat.getInstance();
	format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule

	String periodFormat=format.format(period);
	String curPrincipalFormat=format.format(curPrincipal);
	String curInterestFormat=format.format(curInterest);
	String balanceFormat=format.format(balance);
	String paymentFormat=format.format(payment);

	System.out.println (period + "; " + paymentFormat + "; " + curPrincipalFormat + "; " + curInterestFormat + "; " + balanceFormat) ;

	//Add the current schedule values to the running totals and end the loop.

	totalPayments += payment ;

	totalPrincipal += curPrincipal ;

	totalInterest += curInterest ;

	}

	//Finally, print the totals for each column and exit the method.

	System.out.println ("Totals, " + totalPayments + ", " + totalPrincipal + ", " + totalInterest) ;

	}
	
}

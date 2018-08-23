package app;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public abstract class DepoBase implements Comparable<DepoBase>{
	/**
	 * 
	 */

	protected LocalDate startDate;
	protected int dayLong;
	protected double sum;
	protected double interestRate;
	
	public DepoBase() {	}
	
	public DepoBase(LocalDate startDate, int dayLong, double sum, double interestRate){
		this.startDate = startDate;
		this.dayLong = dayLong;
		this.sum = sum;
		this.interestRate = interestRate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getDayLong() {
		return dayLong;
	}

	public void setDayLong(int dayLong) {
		this.dayLong = dayLong;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	
		public double calculateInterest(LocalDate start, LocalDate maturity){
			int daysInYear;
			int startYear = start.getYear();
			int maturityYear = maturity.getYear();
			if (startYear != maturityYear) {
				daysInYear = start.lengthOfYear();
				}
			else{
				daysInYear = 365;
				if (start.isLeapYear()){
					daysInYear = 366;
				}		
			}
						
			double dayCf = start.until(maturity, ChronoUnit.DAYS) + 1;
	        double interest = sum * (interestRate / 100.0) * (dayCf / daysInYear);
			
	        return interest;
		}
		
		public abstract double getInterest();

		public static class ComparSum implements Comparator<DepoBase>{
			public int compare(DepoBase o1, DepoBase o2) {
				
				if(o1.getSum() > o2.getSum()) return 1;				
				if(o1.getSum() < o2.getSum()) return -1;
				if(o1.getInterest() > o2.getInterest()) return 1;				
				if(o1.getInterest() < o2.getInterest()) return -1;
				return 0;
			}
		}
		
		public static Comparator<DepoBase> SumComparator = new Comparator<DepoBase>(){
			public int compare(DepoBase a, DepoBase b) {
				if(a.getSum() > b.getSum()) return 1;				
				if(a.getSum() < b.getSum()) return -1;
				return 0;
				// TODO Auto-generated method stub			
			}
		};
		
		public static Comparator<DepoBase> DaysComparator = new Comparator<DepoBase>(){
			public int compare(DepoBase a, DepoBase b) {
				if(a.getDayLong() > b.getDayLong()) return 1;				
				if(a.getDayLong() < b.getDayLong()) return -1;
				return 0;
				// TODO Auto-generated method stub			
			}
		};
		
		public int compareTo(DepoBase depo) {
			if(depo.getInterest() > depo.getInterest()) return 1;				
			if(depo.getInterest() < depo.getInterest()) return -1;
			return 0;
		}
		
		
		
}



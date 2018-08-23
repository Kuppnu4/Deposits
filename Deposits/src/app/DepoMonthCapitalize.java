package app;

import java.time.LocalDate;

public class DepoMonthCapitalize extends DepoBase{
public DepoMonthCapitalize(){   }
	
	public DepoMonthCapitalize(LocalDate startDate, int dayLong, double sum, double interestRate){
		super(startDate, dayLong, sum, interestRate);
	}
	
	public double getInterest(){
		double interest = 0.0;          // проценты за текущий период
		double capital = 0.0;           // начисленные проценты
		double tempSum = sum;
		
		LocalDate beginPeriod = startDate;
		LocalDate start = beginPeriod;
		LocalDate end = start.plusDays(dayLong);
		LocalDate endPeriod = beginPeriod;

		while (true){
            beginPeriod = endPeriod.plusDays(1);
			endPeriod = beginPeriod.plusMonths(1);
			endPeriod = LocalDate.of(endPeriod.getYear(), endPeriod.getMonth(), 1);
            endPeriod = endPeriod.minusDays(1);
			if (endPeriod.isAfter(end)) {
				break;
			}
			interest = calculateInterest(beginPeriod, endPeriod);
			capital += interest;
			sum += interest;
		}
		interest = calculateInterest(beginPeriod, end);
		capital += interest;
		sum = tempSum;
		return capital;
	}
}


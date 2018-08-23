package app;

import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		
		DepoList sd = new DepoList();
		sd.init();
		ArrayList<DepoBase> depos = sd.getList();
		
		depos.sort(new DepoBase.ComparSum());	        	// сортировка вложенным классом
		Collections.sort(depos, new CompBySumAndDays());  //сортировка самостоятельным классом 
		Collections.sort(depos); 							//сортировка compareTo		
		depos.sort((depo1, depo2)->(int)(depo1.getSum()*100 - depo2.getSum()*100)); // сортировка с помощью лямбда-выражений
		
		System.out.println("All Deposits");					
		depos.forEach(d -> System.out.format("summ = %1$9.2f      interest = %2$8.2f\n", d.getSum(), d.getInterest()));
		
		
		System.out.println("Large deposits");
		ArrayList<DepoBase> largeSum = (ArrayList<DepoBase>)depos
				.stream()
				.filter(d -> d.getSum()>10000.0)   //берем только вклады более 10000,0
				.collect(Collectors.toList());
		largeSum.forEach(d -> System.out.format("summ = %1$9.2f      interest = %2$8.2f\n Interest Rate = %3$7.2f\n"
				, d.getSum(), d.getInterest(), d.getInterestRate()));
		
		
		System.out.println("Small rate deposits");
		depos
			.stream()
			.filter(d -> d.getInterestRate() < 16.5) // берем только вклады со ставкой меньше 16.5%
			.forEach(d -> System.out.format("summ = %1$9.2f      interest = %2$8.2f\n Interest Rate = %3$7.2f\n"
				, d.getSum(), d.getInterest(), d.getInterestRate()));
		
		double avg = depos //Получить среднее значение депозитов с низким процентом
				.stream()
				.filter(d -> d.getInterestRate() < 16.5)
				.mapToDouble(DepoBase :: getSum)
				.average()
				.getAsDouble();				
				System.out.println(avg + ": average summ of small rate deposits");
				
		double sum = depos // получить сумму всех депозитов
				.stream()
				.mapToDouble(DepoBase :: getSum)
				.reduce(0,  (a,b) -> a+b);
				System.out.println(sum + ": all deposits summ");		
		
		LocalDate now = LocalDate.now(); String thisDay = now.getDayOfMonth() + "_" + now.getMonthValue() + "_" + now.getYear();
		try (PrintStream outF = new PrintStream("report" + thisDay + ".txt")){					
			System.out.println("Сумма                 Профит");
			outF.println("Сумма                 Профит");									
			for (DepoBase dep : depos) {				
				System.out.printf("summ = %1$9.2f      interest = %2$8.2f\n", dep.getSum(), dep.getInterest());
				outF.format("summ = %1$9.2f      interest = %2$8.2f\n", dep.getSum(), dep.getInterest());
			}						
		}catch(IOException ex) {
			ex.printStackTrace();
		}		
		System.out.println("report is done");
				
		sd.removeSmallDeps(depos); //удаляем маленькие депозиты
		System.out.println(sd.getPrincipal() + ": large deposits summ");
		
				
	}
}




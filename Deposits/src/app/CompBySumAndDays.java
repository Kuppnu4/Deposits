package app;

import java.util.Comparator;

public class CompBySumAndDays implements Comparator<DepoBase> {

	
	public int compare(DepoBase o1, DepoBase o2) {
		// TODO Auto-generated method stub
		int flag = (int) (o1.getSum() - o2.getSum());
		if (flag == 0) flag = (int)(o1.getDayLong() - o2.getDayLong());
		if (flag == 0) flag = o1.compareTo(o2);
		return flag;
		
	}

}

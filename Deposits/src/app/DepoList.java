package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class DepoList {
public ArrayList<DepoBase> depoList = null;
	
	DepoList(){		
	}
	
	DepoList(ArrayList<DepoBase> depoList){
		this.depoList = depoList;
	}	
	
	public ArrayList<DepoBase> getList() {
		return depoList;
	}

	public void setList(ArrayList<DepoBase> depoList) {
		this.depoList = depoList;
	}

	public ArrayList<DepoBase> init() {
		
		depoList = new ArrayList<>();
		depoList.add(new DepoSimple(LocalDate.of(2013, 9, 8), 61, 2500, 18));
		depoList.add(new DepoSimple(LocalDate.of(2012, 2, 1), 181, 10000, 21));
		depoList.add(new DepoSimple(LocalDate.of(2013, 11, 12), 30, 5500, 15.3));
		depoList.add(new DepoSimple(LocalDate.of(2011, 12, 18), 370, 43000, 19.56));
		depoList.add(new DepoSimple(LocalDate.of(2013, 7, 12), 91, 12000, 16));
		return depoList;
	}
	
	public double getPrincipal() {
		double total = 0;
		ArrayList<DepoBase> deps = depoList;
		
		Iterator<DepoBase> iter = deps.iterator();
		while(iter.hasNext()) {
			total += iter.next().getSum();
		}
		
		return total;
	}
	
	public ArrayList<DepoBase> removeSmallDeps(ArrayList<DepoBase> deps){
		for(Iterator<DepoBase> iter = deps.iterator(); iter.hasNext();) {
			if(iter.next().getSum() < 10000)
				iter.remove();
		}
		return deps;
			
	}
}

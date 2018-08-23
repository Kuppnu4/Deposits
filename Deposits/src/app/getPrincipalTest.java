package app;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

class getPrincipalTest {

	
	@Test
	void test1() {
		DepoList sd = new DepoList();		
		sd.init();	
		assertEquals(73000.0, sd.getPrincipal(), 0.05);
	
	}
	
	void test2() {
		DepoList sd = new DepoList();		
		sd.init();
		
		assertEquals(73000.0, sd.getPrincipal(), 0.05);
	}

	
}

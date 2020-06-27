package Day_8Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Day_8.Calculate;

public class CalculateTest {
	@Before
	public void init() {
		System.out.println("start");
	}
	@Test
	public void testAdd() {
		Calculate calculate = new Calculate();
		System.out.println("add test");
		int result = calculate.add(1, 2);
		Assert.assertEquals(3, result);
	}
	@Test
	public void testSud() {
		Calculate calculate = new Calculate();
		System.out.println("sub test");
		int result = calculate.sub(1, 2);
		Assert.assertEquals(3, result);
	}
	@After
	public void finish() {
		System.out.println("end");
	}
}

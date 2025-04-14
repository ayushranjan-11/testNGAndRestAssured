package newLearning;

import org.testng.annotations.*;

public class ClassForParallelExecution {

	@Test(priority = 1)
	public void methodRunInParallel() {
		System.out.println("Thread ID in this methodRunInParallel method is: " + Thread.currentThread().threadId());
		System.out.println("Method from parallel execution of different classess");
	}

	@Test(priority = 2)
	public void methodForNumericAddition() {
		int a = 10, b = 20, c;
		System.out.println(c = a + b);
		System.out
				.println("Thread ID in this methodForNumericAddition method is: " + Thread.currentThread().threadId());
	}
}

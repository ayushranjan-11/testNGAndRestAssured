package newLearning;

import org.testng.annotations.*;

public class TestNGLearning {

	public static void main(String[] args) {
		System.out.println("Main method");
	}

	@BeforeClass
	public void methodBeforeClass() {
		System.out.println("Method called with annotation @BeforeClass");
	}

	@Test
	public void methodOne() {
		System.out.println("Method called with annotation @Test");
	}

}

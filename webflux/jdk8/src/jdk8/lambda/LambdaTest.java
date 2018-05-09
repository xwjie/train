package jdk8.lambda;

public class LambdaTest {

	public void test() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("常规模式");
			}
		}).start();

		new Thread(() -> {
			System.out.println("lambda模式");
		}).start();
	}

	public static void main(String[] args) {
		LambdaTest demo = new LambdaTest();

		demo.test();
	}

}

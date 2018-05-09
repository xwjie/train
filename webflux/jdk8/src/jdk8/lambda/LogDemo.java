package jdk8.lambda;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * lambda的惰性求值
 * 
 */
public class LogDemo {

	private static final Logger logger = Logger.getLogger(LogDemo.class.getName());

	@Override
	public String toString() {
		System.out.println("这个方法执行了, 耗时1秒钟");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}

		return "LogDemo";
	}

	public void test() {
		// 如果不加判断直接打印, 会有额外多余的开销, 就算最终日志并没有打印
		// logger.fine("打印一些日志:" + this);

		// 加了日志级别判断, 如果日志最终不打印的话, 没有额外开销
		// if (logger.isLoggable(Level.FINE)) {
		// logger.fine("打印一些日志:" + this);
		// }

		// 使用lambda表达式的惰性求值,不需要判断日志级别
		logger.fine(() -> "打印一些日志:" + this);
	}

	public static void main(String[] args) {
		LogDemo demo = new LogDemo();
		demo.test();
	}

}

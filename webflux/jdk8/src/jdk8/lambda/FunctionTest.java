package jdk8.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

//@FunctionalInterface
interface MyFunction<T, R> {

	/**
	 * Applies this function to the given argument.
	 *
	 * @param t
	 *            the function argument
	 * @return the function result
	 */
	// R apply(T t);

	R apply2(T t);
}

public class FunctionTest {

	public static void main(String[] args) {
		String name = "";
		String name1 = "12345";

		Function<String, Boolean> function = s -> s.length() > 2;

		System.out.println(function);
		System.out.println(function.apply("2"));
		System.out.println("----------------------------------------");

		MyFunction<String, Boolean> myFunction = s -> s.length() > 2;

		System.out.println(myFunction);
		System.out.println(myFunction.apply2("2"));

		System.out.println("----------------------------------------");

		MyFunction<String, Integer> myFunction3 = s -> {
			return s.length();
		};

		System.out.println(myFunction3);
		System.out.println(myFunction3.apply2("2"));

		System.out.println("----------------------------------------");

		Consumer<String> sysout = System.out::print;

		sysout.accept("打印：" + sysout);

		System.out.println(validInput(name, inputStr -> inputStr.isEmpty() ? "名字不能为空" : inputStr));
		System.out.println(validInput(name1, inputStr -> inputStr.length() > 3 ? "名字过长" : inputStr));
	}

	public static String validInput(String name, Function<String, String> function) {
		return function.apply(name);
	}
}

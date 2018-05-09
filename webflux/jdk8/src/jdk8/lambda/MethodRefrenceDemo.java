package jdk8.lambda;

import java.util.function.BiFunction;
import java.util.function.IntUnaryOperator;

/**
 * 静态方法引用和实例方法引用.
 * 
 *
 */
class DemoClass {

	/**
	 * 这里是一个静态方法
	 */
	public static int staticMethod(int i) {
		return i * 2;
	}

	/**
	 * 这里是一个实例方法
	 */
	public int normalMethod(int i) {
		System.out.println("实例方法可以访问this:" + this);
		return i * 3;
	}

}

class DemoClass2 {

	/**
	 * 这里是一个实例方法, 代码上有2个参数 而我们调用的时候只有一个参数
	 */
	public int normalMethod(DemoClass2 this,int i) {
		return i * 2;
	}
}

public class MethodRefrenceDemo {

	public static void main(String[] args) {

		DemoClass2 demo2 = new DemoClass2();

		// 代码定义上有2个参数, 第一个参数为this
		// 但实际上调用的时候只需要一个参数
		demo2.normalMethod(1);

		// 静态方法的方法引用
		IntUnaryOperator methodRefrence1 = DemoClass::staticMethod;
		System.out.println(methodRefrence1.applyAsInt(111));

		DemoClass demo = new DemoClass();

		// 实例方法normalMethod的方法引用
		IntUnaryOperator methodRefrence2 = demo::normalMethod;
		System.out.println(methodRefrence2.applyAsInt(111));

		// 对同一个实例方法normalMethod也可以使用静态引用
		// 代码上normalMethod虽然只有一个参数,但实际上有一个隐含的this函数
		// 所以使用的是2个参数bifunction函数接口
		BiFunction<DemoClass, Integer, Integer> methodRefrence3 = DemoClass::normalMethod;
		System.out.println(methodRefrence3.apply(demo, 111));
	}

}

package jdk8.lambda;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 级联表达式
 * 
 * @author 晓风轻
 *
 */
public class CascadeDemo {

	public static void main(String[] args) {

		Function<Integer, Predicate<Integer>> exp = x -> y -> x > y;
		System.out.println(exp.apply(2).test(1));

		Function<Integer, UnaryOperator<Integer>> exp2 = x -> y -> x + y;
		System.out.println(exp2.apply(1).apply(2));

		//
		IntBinaryOperator addExp = (x, y) -> x + y;
		System.out.println(addExp.applyAsInt(1, 2));
	}

}

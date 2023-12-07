package leo.alves.exploring.application;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;

public class MethodReferencesTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Consumer<String> printer = System.out::println;
		printer.accept("Testing from the consumer method reference...");
		LambdaExpressionsTest.next(sc);
		
		// Writing Static Method References
		DoubleUnaryOperator sqrt = Math::sqrt;		
		Double result = sqrt.applyAsDouble(25);
		printer.accept("Result of sqrt: " + result);
		LambdaExpressionsTest.next(sc);
		
		IntBinaryOperator max = Integer::max;
		int maxNum = max.applyAsInt(18, 11);
		printer.accept("The max is: " + maxNum);
		LambdaExpressionsTest.next(sc);
	
	}

}

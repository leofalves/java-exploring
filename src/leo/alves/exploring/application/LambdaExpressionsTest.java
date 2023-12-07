package leo.alves.exploring.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Random;

/*
 * Check examples on:
 * https://dev.java/learn/lambdas/functional-interfaces/
 * 
 * */
public class LambdaExpressionsTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<String> strings = Arrays.asList("vermelho", "branco", "preto", "alo", "leo");
		List<String> stringsMutable = new ArrayList<>(strings);
		
		// ###### Predicates ###################
		// take an argument, return a boolean (bipredicates - take 2 arguments)
		predicateExamples(sc, strings, stringsMutable);
		
		// ###### Consumers ###################
		// take an argument, do not return anything (biconsumers - take 2 arguments)
		consumerExamples(sc, strings);

		// ###### Suppliers ###################
		// do not take any argument, return something
		supplierExamples(sc);
		
		// ###### Functions ###################
		// take an argument, return something (bifunctions - take 2 arguments)
		functionExamples(sc, strings);
		
		
		// ###### Runnable ###################
		Runnable runnable = () -> System.out.println("I am running from the Runnable Interface...");
		runnable.run();
		next(sc);
		

		System.out.println("--------------------------------------------------");
		System.out.println("Press any key to terminate...");
		sc.nextLine();
		sc.close();
	}
	

	// ######################################################################
	// FUNCTIONS
	static void functionExamples(Scanner sc, List<String> strings) {
		
		// Get an object and returns a different one		
		Function<String, Integer> toLenght = s -> s.length();
		String word = "QualquerPalavra";
		int len = toLenght.apply(word);
		System.out.println("Usando Function<T1, T2>");
		System.out.println("A palavra " + word + " tem " + len + " letras." );
		next(sc);
		
		UnaryOperator<String> toUpperCase = w -> w.toUpperCase();
		strings.replaceAll(toUpperCase);
		System.out.println(strings);
		next(sc);
		
		BiFunction<String, String, Integer> findWordInSentence = (pWord, pSentence) -> {
			return pSentence.indexOf(pWord);
		};
		System.out.println("Using BiFunction. Search for word in a sentence.");
		Integer indexOf = findWordInSentence.apply("World", "Hello World!");
		System.out.println("Result: " + indexOf.toString());
		next(sc);
		
	}
	
	
	// ######################################################################
	// SUPPLIERS
	static void supplierExamples(Scanner sc) {

		Supplier<String> supplier = () -> "Hello man from the Supplier Interface..";
		System.out.println(supplier.get());

		Random random = new Random(314L);
		Supplier<Integer> newRandom = () -> random.nextInt(10);
		for (int index = 0; index < 5; index++) {
			System.out.println("Random Integer from Supplier Interface " + newRandom.get() + " ");
		}
		next(sc);

		// Prevent unnecessary boxing and unboxing
		// It also exists IntSupplier, BooleanSupplier, LongSupplier and DoubleSupplier
		IntSupplier newRandom2 = () -> random.nextInt(10);
		for (int index = 0; index < 5; index++) {
			int nextRandom = newRandom2.getAsInt();
			System.out.println("Random Integer from the IntSupplier Interface " + nextRandom + " ");
		}
		next(sc);

	}
	
	
	// ######################################################################
	// CONSUMERS
	static void consumerExamples(Scanner sc, List<String> strings) {

		// Simple Consumer
		Consumer<String> print = s -> System.out.println("Printed from inside Consumer... " + s);
		for (String s : strings) {
			print.accept(s);
		}
		next(sc);

		// Consumer with 2 parameters		
		BiConsumer<Random, Integer> randomNumberPrinter = (paramRandom, paramNumber) -> {
			for (int i = 0; i < paramNumber; i++) {
				System.out.println("next random from the BiConsumer Interface = " + paramRandom.nextInt(50));
			}
		};
		randomNumberPrinter.accept(new Random(), 10);
		next(sc);

	}
	

	// ######################################################################
	// PREDICATES
	static void predicateExamples(Scanner sc, List<String> strings, List<String> stringsMutable) {
		
		// Versao "completa"
		Predicate<String> predicate1 = (String s) -> {
			return s.length() > 3;
		};
		// Versao simplificada
		Predicate<String> predicate2 = s -> s.length() == 3;

		for (String s : strings) {
			if (predicate1.test(s)) {
				System.out.println("A string '" + s + "' satisfez a condição do predicate 1.");
			}

			if (predicate2.test(s)) {
				System.out.println("A string '" + s + "' satisfez a condição do predicate 2.");
			}
		}
		next(sc);

		
		//Specialized Predicates (avoid auto-unboxing)
		IntPredicate intPred = i -> i > 10;
		if (intPred.test(20)) {
			System.out.println("Using the Specialized Predicate");	
		}
		next(sc);
		
		// Predicate with 2 parameters
		BiPredicate<String, Integer> biPred = (pString, pLength) -> pString.length() > pLength;		
		System.out.println("The string Cascavel is greater than 6 chars? " + biPred.test("Cascavel", 6));
		next(sc);
		
		
		// Passing a Predicate to a Collection
		System.out.println("Strings BEFORE remove with predicate: " + stringsMutable);
		Predicate<String> isOddLength = s -> s.length() % 2 == 0;
		stringsMutable.removeIf(isOddLength);
		System.out.println("Strings AFTER remove with predicate: " + stringsMutable);
		next(sc);
	}
	
	// Insert a line break and ask a key to continue
	static void next (Scanner sc) {
		System.out.println("--------------------------------------------------");
		System.out.println("Press any key to continue...");
		sc.nextLine();		
	}
}

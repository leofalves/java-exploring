package leo.alves.exploring.application;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.Random;

public class LambdaExpressionsTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<String> strings = Arrays.asList("vermelho", "branco", "preto", "alo", "leo");

		// ######################################################################
		// PREDICATE
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
		

		// ######################################################################
		// CONSUMER
		Consumer<String> print = s -> System.out.println("Printed from inside Consumer... " + s);
		for (String s : strings) {
			print.accept(s);
		}
		next(sc);

		// ######################################################################
		// RUNNABLE
		Runnable runnable = () -> System.out.println("I am running from the Runnable Interface...");
		runnable.run();
		next(sc);

		// ######################################################################
		// SUPPLIER
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


		// ######################################################################
		// BICONSUMER (Consumer with 2 parameters)		
		BiConsumer<Random, Integer> randomNumberPrinter = (paramRandom, paramNumber) -> {
			for (int i = 0; i < paramNumber; i++) {
				System.out.println("next random from the BiConsumer Interface = " + paramRandom.nextInt(50));
			}
		};
		randomNumberPrinter.accept(new Random(), 10);
		
		
		System.out.println("--------------------------------------------------");
		System.out.println("Press any key to terminate...");
		sc.nextLine();
		sc.close();

	}
	
	static void next (Scanner sc) {
		System.out.println("--------------------------------------------------");
		System.out.println("Press any key to continue...");
		sc.nextLine();		
	}
}

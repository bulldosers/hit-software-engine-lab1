package lab7;

import java.util.Scanner;

public class Input {
	Polynomial core = new Polynomial();
	Polynomial preCore = new Polynomial();
	public String work() { 
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		while (true) {
			System.out.print(">");
			String str = cin.nextLine();
			str = str.trim();
			if (str.charAt(0) != '!') { 
					System.out.println("Error , expression error !"); 
			} else {
				if (preCore.empty()) {
					System.out.println("Error , no expression !");
				} else if (str.charAt(1) == 's') {
					System.out.println(core.derivative(str) ); 
				} else {
					System.out.println("Error , no expression !"); 
				}
			} 
		}
	}
} 
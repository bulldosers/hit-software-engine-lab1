package lab7;

import java.util.Scanner;

public class Work {
	Polynomial core = new Polynomial();
	Polynomial preCore = new Polynomial();

	public static void main(String args[]) {
		new Work().work();
		// Sol sol = new Sol();
		// String result = sol.simplify("x*yy*zzz","!simplify x=2");
		// System.out.println(result);
	}
	
	public String Derivative(String ss, String str) {
		return "";//null;
		/*
		 * core.clear(); if (isExpression(ss)) { preCore = core;
		 * //System.out.println(ss); } else {
		 * //System.out.println("Error , expression error !"); //
		 * return"Error , expression error !"; }
		 */
		// if (isSimplify(str)) {
		// return simplify1(str);
		// } else {
		// System.out.println("Error , format error !");
		// return "Error , format error !";
		// }
	}
	
	public String simplify(String ss, String str) {
		return "";//null;
		/*
		 * core.clear(); if (isExpression(ss)) { preCore = core;
		 * //System.out.println(ss); } else {
		 * //System.out.println("Error , expression error !"); //
		 * return"Error , expression error !"; }
		 */
		// if (isSimplify(str)) {
		// return simplify1(str);
		// } else {
		// System.out.println("Error , format error !");
		// return "Error , format error !";
		// }
	}

	public void work() {
		preCore.clear();
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);

		while (true) {
			System.out.print(">");
			String str = cin.nextLine();
			str = str.trim();
			if (str.charAt(0) != '!') {
				core.clear();
				if(str.charAt(0) == '-' || str.charAt(0) == '+') str = '0' + str;
				if (isExpression(str)) {
					preCore = core; 
					System.out.println(str); 
				} else {
					System.out.println("Error , expression error !"); 
				}
			} else {
				if (preCore.empty()) {
					System.out.println("Error , no expression !");
				} else if (str.charAt(1) == 's') {
					if (isSimplify(str)) {
						System.out.println( simplify1(str) );
					} else {
						System.out.println("Error , format error !");
					}
				} else {
					if (isDerivative(str)) {
						System.out.println( derivative1(str) );
					} else {
						System.out.println("Error , format error !");
					}
				}
			}
			// System.out.println(s);
		}
	}

	private Boolean isExpression(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!isSymbol(str.charAt(i))) {
				return false;
			}
		}
		int current = 0;
		for (int i = 1; i < str.length(); i++) {
			char operator = str.charAt(i);
			if (operator == '-' || operator == '+') {
				if (!core.addMonomial(str.substring(current, i))) {
					return false;
				}
				current = i;
			}
		}
		if (!core.addMonomial(str.substring(current, str.length()))) {
			return false;
		}
		return true;
	}
	
	private String simplify1(String str) {
		// System.out.println("test:"+str);
		str = str.substring(9, str.length());
		str = str.trim();
		return preCore.simplify(str);
	}

	private String derivative1(String str) {
		str = str.substring(4);
		str = str.trim();
		return preCore.derivative(str);
	}

	private Boolean isDerivative(String str) {
		if (str.length() < 4) {
			return false;
		}
		if (!str.substring(1, 4).equals("d/d")) {
			return false;
		}
		return true;
	} 

	private Boolean isSymbol(char cha) {
		if (cha == 43 || cha == 45 || cha == 42 || cha <= 57 && cha >= 48 || cha <= 122 && cha >= 97
				|| cha <= 90 && cha >= 65) {
			return true;
		}
		return false;
	} 
	
	private Boolean isSimplify(String str) {
		if (str.length() < 9) {
			return false;
		}
		if (!str.substring(1, 9).equals("simplify")) {
			return false;
		}
		return true;
	}
}
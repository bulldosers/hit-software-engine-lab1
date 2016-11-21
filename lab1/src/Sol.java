import java.util.Scanner;

/**
 * @author Administrator
 *
 */
class Sol {
    /**
     * 
     */
    Mystack core = new Mystack();
    /**
     * 
     */
    Mystack preCore = new Mystack();

    /**
     * 
     */
    public static void main(String args[])
    { 
    	Sol sol = new Sol();
		String result = sol.simplify("x*yy*zzz","!simplify x=2");
		System.out.println(result);
    }
    
    public String simplify(String ss,String str){ 
    	core.clear();
    	if (isExpression(ss)) {
            preCore = core;
            //System.out.println(ss);
        } else {
            //System.out.println("Error , expression error !");
            return"Error , expression error !";
        }
    	if (isSimplify(str)) {
            return simplify1(str);
        } else {
			//System.out.println("Error , format error !");
        	return "Error , format error !";
        } 
    }
    
    public void work() {  //�����������ܺ���
        preCore.clear();
        Scanner cin = new Scanner(System.in);
        
        while (true) {
            System.out.print(">");
            String str = cin.nextLine();
            str = str.trim();
            if (str.charAt(0) != '!') {
                core.clear();
                //if(s.charAt(0) == '-' || s.charAt(0) == '+') s = '0' + s;
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
                        simplify1(str);
                    } else {
						System.out.println("Error , format error !");
					}
                } else {
                    if (isDerivative(str)) {
                        derivative(str);
                    } else {
						System.out.println("Error , format error !");
					}
                }
            }
            //System.out.println(s);
        }  
    }

    
    
    /**
     * @param str
     */
    private String simplify1(String str) { //����
    	//System.out.println("test:"+str);
        str = str.substring(9, str.length());
        str = str.trim();
        return preCore.simplify(str);
    }

    /**
     * @param str
     */
    private void derivative(String str) { //��
        str = str.substring(4);
        str = str.trim();
        preCore.derivative(str);
    }

    /**
     * @param str
     * @return
     */
    private Boolean isSimplify(String str) { //�����ж�
        if (str.length() < 9) {
            return false;
        }
        if (!str.substring(1, 9).equals("simplify")) {
            return false;
        }
        return true;
    }

    /**
     * @param str
     * @return
     */
    private Boolean isDerivative(String str) { //���ж�
        if (str.length() < 4) {
            return false;
        }
        if (!str.substring(1, 4).equals("d/d")) {
            return false;
        }
        return true;
    }

    /**
     * @param str
     * @return
     */
    private Boolean isExpression(String str) { //���ʽ�ж�
        for (int i = 0; i < str.length(); i++) {
            if (!isSymbol(str.charAt(i))) {
                return false;
            }
        }
        int current = 0;
        for (int i = 1; i < str.length(); i++) {
            char operator = str.charAt(i);
            if (operator == '-' || operator == '+') {
                if (!core.add(str.substring(current, i))) {
					return false;
				}
                current = i;
            }
        }
        if (!core.add(str.substring(current, str.length()))) {
			return false;
		}
        return true;
    }

    /**
     * @param cha
     * @return
     */
    private Boolean isSymbol(char cha) { //�����ж�
        if (cha == 43 || cha == 45 || cha == 42 ||
        		cha <= 57 && cha >= 48 || 
        		cha <= 122 && cha >= 97 || 
        		cha <= 90 && cha >= 65) {
            return true;
        }
        return false;
    }
}

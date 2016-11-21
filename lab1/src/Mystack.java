/**
 * @author Administrator
 *
 */
public class Mystack { // �洢�����
	Node[] exp = new Node[100]; // ����ʽ������
	int tot;

	/**
     * 
     */
	public Mystack() {
	}

	/**
     * 
     */
	public void clear() {
		tot = 0;
		exp = new Node[100];
	}

	/**
	 * @return
	 */
	public Boolean empty() {
		return tot == 0;
	}

	/**
	 * @param node
	 */
	public void push(Node node) {
		exp[++tot] = node;
	}

	/**
	 * @param str
	 * @return
	 */
	public Boolean add(String str) { // ��ֺ����������ʽ��ֳɵ���ʽ���飬�̶���ֳɶ������Ϣ
		// System.out.println(s);
		char operator = str.charAt(0);
		str = str.trim();
		if (isNum(str)) {
			push(new Node("", toNum(str)));
			return true;
		}
		if (isVar(str)) {
			if (operator == '+') {
				push(new Node(str.substring(1, str.length()), 1));
			} else if (operator == '-') {
				push(new Node(str.substring(1, str.length()), -1));
			} else {
				push(new Node(str, 1));
			}
			return true;
		}
		int current = 0;
		int tmp = 1;
		int flag = 1;
		String nam = "";
		str += '*';
		if (operator == '+') {
			current = 1;
		} else if (operator == '-') {
			current = 1;
			flag = -1;
		}
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == '*') {
				String temp = str.substring(current, i);
				// System.out.println(temp+' '+i);
				if (isVar(temp)) {
					nam += temp + '*';
				} else if (isNum(temp)) {
					int toN = toNum(temp);
					tmp *= toN;
				} else {
					return false;
				}
				current = i + 1;
			}
		}
		push(new Node(nam, tmp * flag));
		return true;
	}

	/**
	 * @param str
	 * @return
	 */
	private int toNum(String str) { // ת����
		int tmp = 0;
		int flag = 1;
		char operator = str.charAt(0);
		if (operator == '-') {
			flag = -1;
		} else if (operator >= '0' && operator <= '9') {
			tmp = operator - '0';
		}
		for (int i = 1; i < str.length(); i++) {
			operator = str.charAt(i);
			tmp = tmp * 10 + operator - '0';
		}
		return tmp * flag;
	}

	/**
	 * @param str
	 * @return
	 */
	private Boolean isNum(String str) { // �ж�����
		char operator = str.charAt(0);
		if (operator != '+' && operator != '-'
				&& (operator < '0' || operator > '9')) {
			return false;
		}
		for (int i = 1; i < str.length(); i++) {
			if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param str
	 * @return
	 */
	private Boolean isVar(String str) { // �жϱ���
		for (int i = 0; i < str.length(); i++) {
			char operator = str.charAt(i);
			if ((operator >= '0' && operator <= '9')
					|| (operator == '*' || operator == '^' || operator == ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param str
	 */
	public String simplify(String str) { // ������
		String vars[] = new String[100];
		int num[] = new int[100];
		int top = 0;
		int current = 0;
		str += ' ';
		for (int i = 0; i < str.length(); i++) {
			char operator = str.charAt(i);
			if (operator == ' ') {
				for (int j = current; j < i; j++) {
					if (str.charAt(j) == '=') {
						vars[top] = str.substring(current, j);
						num[top++] = toNum(str.substring(j + 1, i));
						// System.out.println(Vars[top-1] + '=' + num[top-1]);
						break;
					}
				}
				current = i + 1;
			}
		}
		String ans = "";
		for (int i = 1; i <= tot; i++) {
			Node tmp = new Node(exp[i]);
			// System.out.println(tmp.coe);
			for (int j = 0; j < tmp.top; j++) {
				for (int k = 0; k < top; k++) {
					if (vars[k].equals(tmp.vars[j])) {
						int abc = num[k];
						for (int r = 0; r < tmp.num[j]; r++) {
							tmp.coe *= abc;
						}
						tmp.num[j] = 0;
					}
				}
			}
			if (tmp.coe < 0) {
				ans += tmp.coe;
			} else {
				ans += "+" + tmp.coe;
			}
			for (int j = 0; j < tmp.top; j++) {
				for (int k = 0; k < tmp.num[j]; k++) {
					ans += '*' + tmp.vars[j];
				}
			}
		}
		//System.out.println(ans.substring(1));
		return ans.substring(1);
	}

	/**
	 * @param str
	 */
	public String derivative(String str) { // �󵼺���
		String vars[] = new String[100];
		int top = 0;
		vars[top++] = str;
		String ans = "";
		for (int i = 1; i <= tot; i++) {
			Node tmp = new Node(exp[i]);
			// System.out.println(tmp.coe);
			for (int j = 0; j < tmp.top; j++) {
				for (int k = 0; k < top; k++) {
					if (vars[k].equals(tmp.vars[j])) {
						if (tmp.num[j] > 0) {
							tmp.coe *= tmp.num[j];
							tmp.num[j]--;
							// System.out.println(Vars[k]);
						}
					}
				}
			}
			if (tmp.coe < 0) {
				ans += tmp.coe;
			} else {
				ans += "+" + tmp.coe;
			}
			for (int j = 0; j < tmp.top; j++) {
				for (int k = 0; k < tmp.num[j]; k++) {
					ans += '*' + tmp.vars[j];
				}
			}
		}
		return ans.substring(1);
		//System.out.println(ans.substring(1));
	}
}
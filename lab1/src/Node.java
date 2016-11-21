/**
 * @author Administrator
 *
 */
public class Node { //����ʽ������
    /**
     * 
     */
    String varName;
    /**
     * 
     */
    public String[] vars = new String[100]; //����ĸ��������
    /**
     * 
     */
    public int num[] = new int[100]; //ϵ������
    public int coe;
    /**
     * 
     */
    public int top;

    public Node() {
    }

    public Node(Node node) {
        for (int i = 0; i < 100; i++) {
			vars[i] = node.vars[i];
		}
        for (int i = 0; i < 100; i++) {
			num[i] = node.num[i];
		}
        coe = node.coe;
        top = node.top;
    }

    /**
     * @param name
     * @param acoe
     */
    public Node(String name, int acoe) { //������ʽ���Ϊ������洢���ֱ��¼ϵ����ָ����Ϣ
        varName = name;
        coe = acoe;
        int current = 0;
        top = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '*') {
                num[top] = 0;
                vars[top++] = name.substring(current, i);
                current = i + 1;
            }
        }
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                if (vars[j].equals(vars[i])) {
                    num[j]++;
                    break;
                }
            }
        }
    }
}
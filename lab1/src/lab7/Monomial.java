package lab7;

public class Monomial{
	private String expsresion; 
    public String[] vars = new String[100]; 
    public int indexs[] = new int[100];  
    public int coe; 
    public int top;

    public Monomial() {
    }

    public Monomial(Monomial monomial) {
        for (int i = 0; i < 100; i++) {
			vars[i] = monomial.vars[i];
		}
        for (int i = 0; i < 100; i++) {
			indexs[i] = monomial.indexs[i];
		}
        coe = monomial.coe;
        top = monomial.top;
    } 
    public Monomial(String name, int acoe) { 
        setexpsresion(name);
        coe = acoe;
        int current = 0;
        top = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '*') {
                indexs[top] = 0;
                vars[top++] = name.substring(current, i);
                current = i + 1;
            }
        }
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                if (vars[j].equals(vars[i])) {
                    indexs[j]++;
                    break;
                }
            }
        }
    }

	public String getexpsresion() {
		return expsresion;
	}

	public void setexpsresion(String expsresion) {
		this.expsresion = expsresion;
	}
}


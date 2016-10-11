import java.util.*;

public class lab1 
{
	public static void main(String args[])
	{ 
		new Sol().work();
	}
}
class Node
{
	String Varname;
	public String[] Vars = new String[100];
	public int num[] = new int[100];
	public int coe;
	public int top;
	public Node(){}
	public Node(Node e)
	{ 
		for(int i=0;i<100;i++) Vars[i] = e.Vars[i];
		for(int i=0;i<100;i++) num[i] = e.num[i];
		coe = e.coe;
		top = e.top; 
	}
	public Node(String name,int aCoe)
	{
		Varname = name;
		coe = aCoe; 
		int l = 0;
		top = 0;
		for(int i=0;i<name.length();i++)
		{
			if(name.charAt(i) == '*')
			{
				num[top] = 0;
				Vars[top++] = name.substring(l,i);
				l = i+1;
			}
		}
		for(int i=0;i<top;i++)
		{
			for(int j=0;j<top;j++)
			{
				if(Vars[j].equals(Vars[i]) )
				{
					num[j]++;
					break;
				}
			}
		}
		//for(int i=0;i<top;i++) System.out.println(Vars[i] + '!' + num[i]);  System.out.println(name + ' ' + aCoe);
	}
}
class Mystack
{
	Node[] exp = new Node[100];
	int tot;
	public Mystack(){}
	public void clear()
	{ 
		tot = 0;
		exp = new Node[100];
	} 
	public Boolean empty()
	{
		return tot==0;
	}
	public void push(Node x)
	{
		exp[++tot] = x; 
	}
	public Boolean add(String s)
	{
		//System.out.println(s);
		s.trim();
		if(isNum(s) )
		{
			push(new Node("",toNum(s)) );
			return true;
		}
		if(isVar(s) )
		{
			char e = s.charAt(0);
			if(e == '+') push(new Node(s.substring(1,s.length()),1) );
			else if(e == '-') push(new Node(s.substring(1,s.length()),-1) );
			else push(new Node(s,1) );
			return true;
		} 
		int l = 0;
		int tmp = 1;
		int flag = 1;
		String nam = "";
		s+='*';
		char c = s.charAt(0);
		if(c == '+') l=1;
		else if(c == '-') 
		{
			l = 1;
			flag = -1;
		}
		for(int i=1;i<s.length();i++)
		{
			if(s.charAt(i) == '*')
			{
				String temp = s.substring(l,i);
				//System.out.println(temp+' '+i);
				if(isVar(temp) )
				{
					nam += temp +'*';
				}else if(isNum(temp) ) 
				{
					int t = toNum(temp );
					tmp *= t;
				}else return false; 
				l = i+1;
			}
		} 
		push(new Node(nam,tmp*flag) );
		return true;
	}
	private int toNum(String s)
	{
		int tmp = 0;
		int flag = 1;
		char e = s.charAt(0);
		if(e == '-') flag = -1;
		else if(e>='0' && e<='9') tmp = e-'0';
		for(int i=1;i<s.length();i++)
		{
			e = s.charAt(i);
			tmp = tmp*10 + e - '0';
		}
		return tmp*flag;
	}
	private Boolean isNum(String s)
	{
		char e = s.charAt(0);
		if(e != '+' && e !='-' && (e<'0' || e>'9') ) return false;
		for(int i=1;i<s.length();i++)
		{
			if(! (s.charAt(i)>='0' && s.charAt(i)<='9') )
			{
				return false;
			}
		}
		return true;
	}
	private Boolean isVar(String s)
	{
		for(int i=0;i<s.length();i++)
		{
			char e = s.charAt(i);
			if( (e>='0' && e<='9') || (e=='*' || e=='^' || e==' ') )
			{
				return false;
			}
		}
		return true;
	} 
	
	public void Simplify(String s)
	{
		String Vars[] = new String[100];
		int num[] = new int[100];
		int top = 0;
		int l = 0;
		s += ' ';
		for(int i=0;i<s.length();i++)
		{
			char e = s.charAt(i);
			if(e == ' ')
			{
				for(int j=l;j<i;j++)
				{
					if(s.charAt(j) == '=')
					{
						Vars[top] = s.substring(l,j);
						num[top++] = toNum( s.substring(j+1,i) );
						//System.out.println(Vars[top-1] + '=' + num[top-1]);
						break;
					}
				}
				l = i+1;
			}
		}
		String ans = "";
		for(int i=1;i<=tot;i++)
		{
			Node tmp = new Node(exp[i] );
			//System.out.println(tmp.coe);
			for(int j=0;j<tmp.top;j++)
			{
				for(int k=0;k<top;k++)
				{
					if(Vars[k].equals(tmp.Vars[j]) )
					{
						int abc = num[k];
						for(int r=0;r<tmp.num[j];r++)
						{
							tmp.coe *= abc;
						}
						tmp.num[j] = 0; 
					}
				}
			}
			if(tmp.coe<0) ans += tmp.coe;
			else ans += "+" + tmp.coe;
			for(int j=0;j<tmp.top;j++)
			{
				for(int k=0;k<tmp.num[j];k++)
				{
					ans += '*' + tmp.Vars[j];
				}
			}
		}
		System.out.println(ans.substring(1) );
	}
	
	public void Derivative(String s)
	{
		String Vars[] = new String[100]; 
		int top = 0;
		int l = 0;
		Vars[top++] = s;
		String ans = "";
		for(int i=1;i<=tot;i++)
		{
			Node tmp = new Node(exp[i] );
			//System.out.println(tmp.coe);
			for(int j=0;j<tmp.top;j++)
			{
				for(int k=0;k<top;k++)
				{
					if(Vars[k].equals(tmp.Vars[j]) )
					{
						if(tmp.num[j]>0)
						{
							tmp.coe *= tmp.num[j];
							tmp.num[j]--;
							//System.out.println(Vars[k]);
						} 
					}
				}
			}
			if(tmp.coe<0) ans += tmp.coe;
			else ans += "+" + tmp.coe;
			for(int j=0;j<tmp.top;j++)
			{
				for(int k=0;k<tmp.num[j];k++)
				{
					ans += '*' + tmp.Vars[j];
				}
			}
		}
		System.out.println(ans.substring(1) );
	}
}
class Sol
{  
	Mystack core = new Mystack();
	Mystack pre_core = new Mystack();
	public void work()
	{
		pre_core.clear();
		Scanner Cin = new Scanner(System.in);
		while(true)
		{
			System.out.print(">");
			String s = Cin.nextLine(); 
			String fx = "";
			s = s.trim();  
			if(s.charAt(0) != '!')
			{
				core.clear();
				//if(s.charAt(0) == '-' || s.charAt(0) == '+') s = '0' + s;
				if(isExpression(s) )
				{
					pre_core = core;
					System.out.println(s);  
				}else
				{
					System.out.println("Error , expression error !");
				}
			}else
			{
				if(pre_core.empty() )
				{
					System.out.println("Error , no expression !");
				}else if(s.charAt(1) == 's' )
				{
					if(isSimplify(s) )
					{
						Simplify(s);
					}else System.out.println("Error , format error !");
				}else
				{
					if(isDerivative(s) )
					{
						Derivative(s);
					}else System.out.println("Error , format error !");
				}
			}
			//System.out.println(s);
		}
	} 
	private void Simplify(String s)
	{
		s = s.substring(9,s.length());
		s = s.trim();
		pre_core.Simplify(s);
	}
	
	private void Derivative(String s)
	{
		s = s.substring(4);
		s = s.trim();
		pre_core.Derivative(s);
	} 
	private Boolean isSimplify(String s)
	{
		if (s.length() < 9 )
		{
			return false;
		}
		if ( !s.substring(1, 9).equals("simplify") )
		{
			return false;
		}
		return true;
	} 
	private Boolean isDerivative(String s)
	{
		if (s.length() < 4 )
		{
			return false;
		}
		if ( !s.substring(1, 4).equals("d/d") )
		{
			return false;
		}
		return true;
	} 
	private Boolean isExpression(String s)
	{
		for (int i=0; i < s.length(); i++)
		{
			if (!isSymbol(s.charAt(i) ) )
			{
				return false;
			}
		} 
		int l = 0;
		for(int i=1;i<s.length();i++)
		{
			char e = s.charAt(i);
			if(e == '-' || e == '+')
			{
				if( !core.add( s.substring(l,i) ) ) return false;
				l = i;
			}
		}
		if(!core.add( s.substring(l,s.length() ) ) ) return false;
		return true;
	}
	private Boolean isSymbol(char c)
	{
		if (c == '+' || c == '-' || c =='*')
		{
			return true;
		} 
		if (c <= '9' && c >= '0')
		{
			return true;
		}
		if (c <= 'z' && c >= 'a')
		{
			return true;
		}
		if (c <= 'Z' && c >= 'A')
		{
			return true;
		} 
		return false;
	} 
}

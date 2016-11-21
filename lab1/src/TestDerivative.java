import static org.junit.Assert.*;
import org.junit.Test;

public class TestDerivative { 
	private final String[] e1 = {"x*yy*zzz","x*yy*zzz-a*b","x*yy*zzz+a*b"};
	private final String[] e2 = {"!d/dx","!d/dyy","!d/d"};
	private final String[] e3 = {"1*yy*zzz","1*x*zzz-1*a*b","1*x*yy*zzz+1*a*b"};
	@Test
	public void testDerivative() {
		Sol sol = new Sol();
		for(int i=0;i<3;i++)
		{
			String result = sol.Derivative(e1[i],e2[i]); 
			assertEquals(e3[i],result);
		}
	}
}

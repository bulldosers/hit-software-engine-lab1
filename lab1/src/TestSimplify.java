import static org.junit.Assert.*; 
import org.junit.Test;

public class TestSimplify {
	private final String[] expressions = {"x*yy*zzz","x*yy*zzz+k"};

	private final String[] orders = {"!simplify   x=2","!simplify yy=-2",
	"!simplify k=2","!simplify x=2 yy=3","!123simplify","!simplify x  =  2","!simplify k=2",
	"!simplify 1=2.2","!simplifyx=22","!simplify"};

	private final String[] expectations = {"2*yy*zzz","2*x*zzz","1*x*yy*zzz","6*zzz",
	"Error , format error !","Error , format error !","1*x*yy*zzz",
	"1*x*yy*zzz","22*yy*zzz","1*x*yy*zzz"};   
	
	@Test
	public void testSimplify() {
		Sol sol = new Sol(); 
		String result = sol.simplify(expressions[0],orders[0]); 
		assertEquals(expectations[0], result);   
	}
} 

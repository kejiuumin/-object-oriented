package 계산기;
import java.util.*;

public class Calc {
	public static double eval(String exp) {
	    StringTokenizer st = new StringTokenizer(exp);
	    Stack<Double> stack = new Stack<>();

	    while (st.hasMoreTokens()) {
	        String tok = st.nextToken();
	        if (Infix2Postfix.opType(tok) > 0) {
	            if (Infix2Postfix.opType(tok) == 2 && !st.hasMoreTokens()) {
	                if (stack.isEmpty()) {
	                    throw new IllegalArgumentException("피연산자가 누락되었습니다.");
	                }
	                double v1 = stack.pop();
	                stack.push(-v1);
	            } else {
	                if (stack.size() < 2) {
	                    throw new IllegalArgumentException("피연산자가 누락되엇습니다.");
	                }
	                double v1 = stack.pop();
	                double v2 = stack.pop();
	                double value = 0;
	                switch (Infix2Postfix.opType(tok)) {
	                    case 1: // +
	                        value = v2 + v1;
	                        break;
	                    case 2: // -
	                        value = v2 - v1;
	                        break;
	                    case 3: // *
	                        value = v2 * v1;
	                        break;
	                    case 4: // /
	                        value = v2 / v1;
	                        break;
	                }
	                stack.push(value);
	            }
	        } else {
	            stack.push(Double.parseDouble(tok));
	        }
	    }

	    if (stack.isEmpty() || stack.size() > 1) {
	        throw new IllegalArgumentException("너무 많은 피연산자.");
	    }

	    double result = stack.pop();
	    return result;
	}
}

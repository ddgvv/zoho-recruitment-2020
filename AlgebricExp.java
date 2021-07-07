import java.util.Arrays;

public class AlgebricExp {

	static void getExpressionArray(String input) {
		input = input.replace("(", "");
		input = input.replace(")", "");
		input = input.replace("-", "-=");
		System.out.println(Arrays.toString(input.split("[*]")));
		String[] splitByAstrix = input.split("[*]");
		AlgebricExpresssion[] algebricExpresssion = new AlgebricExpresssion[10];
		for (int i = 0; i < splitByAstrix.length; i++) {
			String[] splitBySymbol = splitByAstrix[i].split("[+-]");
			for (int j = 0; j < splitBySymbol.length; j++) {
				System.out.println(splitBySymbol[j]);
				Expresssion expresssion = new Expresssion(splitBySymbol[j]);
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println('z'-0);
		getExpressionArray("(2xy+4x^2y-2x)*(2x^2y+6xy)");
	}
}

class AlgebricExpresssion {
	Expresssion[] expresssion = new Expresssion[10];
}

class Expresssion {
	Expresssion(String val) {
		val = val.replace("=", "-");
		int sign = val.charAt(0) == '-' ? -1 : 1 ;
		if(sign==-1) {val = val.substring(1);}
		int endIndex = getDigitEnd(val);
		number = endIndex != 0 ? Integer.parseInt(val.substring(0,endIndex)) : 1;
		number *= sign;
		System.out.println("number " + number);
		
		val = val.substring(endIndex).replace("^", "");
		for(int i = endIndex + 1; i < val.length() ; i ++ ) {
			int value = val.charAt(i) - '0';
			if( value <= 9 && value >= 0) {
				int digitEnd = getDigitEnd(val.substring(i));
				System.out.println("digitEnd "+Integer.parseInt(val.substring(i,i+digitEnd)));
			}
		}
		
 	}
	private int getDigitEnd(String val) {
		int endIndex = 0;
		for (int i = 0; i < val.length() ; i++) {
			int value = val.charAt(i) - '0';
			if( value <= 9 && value >= 0) {
				endIndex++;
			} else {
				break;
			}
		}
		return endIndex;
	}
	int number;
	Variable[] variablePower = new Variable[10];
	String expression;//x^2y^2z
}

class Variable {
	String var;
	int power;
	int ascii;
}
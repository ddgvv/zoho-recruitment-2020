
public class Operator {

	static void operatorConvertor(String input){
		int[] intList = new int[10]; 
		char[] opArray = new char[10]; 
		int opLength = 0;
		int numLength = 0;
		for(int i =0;i<input.length(); i++) {
			int val = input.charAt(i) - '0';
			if(val>=0 && val<=9) {
				intList[numLength++] = val;
			} else {
				opArray[opLength++] = input.charAt(i);
			}
		}
		int result = intList[0];
		for(int i =0;i <= opLength; i++) {
			switch(opArray[i]) {
				case '+':
					result += intList[i+1];
					break;
				case '-':
					result -= intList[i+1];
					break;
				case '/':
					result /= intList[i+1];
					break;
				case '*':
					result *= intList[i+1];
					break;
			}
		}
		System.out.println(result);
		
	}
	
	public static void main(String[] args) {
		operatorConvertor("67542-/+-");
		operatorConvertor("12345*+-+");
		operatorConvertor("374291--*+-");
	}
}

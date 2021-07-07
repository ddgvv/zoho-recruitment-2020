
public class Hello {
	
	private static int getMax(int num1,int num2 ) {
		return num1<num2 ? num2 : num1;
	}
	
	private static int[] getReverse(int str[],int start, int end ) {
		int temp;
		while(start<=end) {
			temp = str[start];
			str[start] = str[end];
			str[end] = temp;
			start++;
			end--;
		}
		return str;
	}
		
	private static void binaryDecimal(int n) {
		int[] digit = new int[10]; 
		int len = 0;
		while (n>0) {
			digit[len++] = n%10;
			n/=10;
		}
		digit = getReverse(digit,0,len -1 );
		int ans = 0;
		for (int i =0;i<len;i++) {
			ans = getMax(ans,digit[i]);
		}
		for (int i =1;i<=ans;i++) { // this many vales we have
			int num =0;
			for (int j =0;j<len;j++) {// get one value from each digit 
				if(digit[j]>0) {
					num = num * 10 + 1;
					digit[j]--;
				}
				else {
					num*=10;
				}
			}
			System.out.println(num + " ");
	}
	}

	public static void main(String[] args) {
		binaryDecimal(150);
	}
}

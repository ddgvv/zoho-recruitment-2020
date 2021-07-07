
public class SortedPairs {

	static int getMin(int[] inputArray, int prevMin) {
		int min = 99999;
		for(int i =0;i < inputArray.length; i++) {
			if(prevMin < inputArray[i] ) {
				min = inputArray[i] < min ? inputArray[i] : min;
			}
		}
		return min;
	}
	
	static int getMin(int[] inputArray) {
		int min = 99999;
		for(int i =0;i < inputArray.length; i++) {
			min = inputArray[i] < min ? inputArray[i] : min;
		}
		return min;
	}

	static int getMax(int[] inputArray, int prevMax) {
		int max = 0;
		for(int i =0;i < inputArray.length; i++) {
			if(prevMax > inputArray[i] ) {
				max = inputArray[i] > max ? inputArray[i] : max;
			}
		}
		return max;
	}

	static int getMax(int[] inputArray) {
		int max = 0;
		for(int i =0;i < inputArray.length; i++) {
			max = inputArray[i] > max ? inputArray[i] : max;
		}
		return max;
	}
	
	static void sortedPairs(int[] inputArray) {
		int[] resArray = new int[inputArray.length];
		resArray[0] = getMax( inputArray);
		resArray[1] = getMin( inputArray);
		for(int i = 2;i < inputArray.length; i++ ) {
			if(i%2 == 0) {
				resArray[i] = getMax( inputArray,resArray[i-2]);
			} else {
				resArray[i] = getMin( inputArray,resArray[i-2]);
			}
		}
		for(int i = 0;i < resArray.length; i++ ) {
			System.out.println(resArray[i]);
		}
	}
	
	public static void main(String[] args) {
		int[] array = {34,65,42,14,98,45,67} ;
		sortedPairs(array);
	}
}

public class SortedArray {
	
	static void sortedArray(int[] arrayA,int[] arrayB) {
		int[] arrayC = new int[arrayA.length + arrayB.length];
		int cLength = 0;
		int aStart = 0;
		int bStart = 0;
		int aEnd = arrayA.length - 1;
		int bEnd = arrayB.length - 1;
		for (int i = 0 ; i < arrayC.length; i++) {
			int aRes=9999,bRes=9999,aFront=0,bFront=0,res=0;
			if(aStart<=aEnd) {
				if(arrayA[aStart]<arrayA[aEnd]) {
					aRes = arrayA[aStart]; aFront = 1;
				} else {
					aRes = arrayA[aEnd]; 
				}
			}
			
			if(bStart<=bEnd) {
				if(arrayB[bStart]<arrayB[bEnd]) {
					bRes = arrayB[bStart]; bFront = 1;
				} else {
					bRes = arrayB[bEnd];
				}
			}
			
			if(aRes < bRes) {
				res = aRes;
				int val = aFront == 0 ? aEnd-- : aStart++ ;
			} else {
				res = bRes;
				int val = bFront == 0 ? bEnd-- : bStart++ ;
			}
			
			if (cLength == 0) {
				arrayC[cLength++] = res;
			} else if (arrayC[cLength-1] != res) {
				arrayC[cLength++] = res;
			}
		}

		for (int i = 0 ; i < cLength; i++) {
			System.out.print(arrayC[i]);
		}	
	}
	
	public static void main(String[] args) {
		int[] arrayA = {1,1,2,3};
		int[] arrayB = {6,5,4,1};
		sortedArray(arrayA, arrayB);
		
	}
}

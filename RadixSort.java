package unit1;
/** An algorithm which sorts an array of integers
 * @author Ariel Traver
 * @version 1.0 **/
public class RadixSort {
	
	/**Gets the requested digit
	 * @param num: the number
	 * @param digit: the desired digit
	 * @param base: the base you are working in, such as 10, 2 (binary), etc**/
	public static int getDigit(int num, int digit, int base) {
		num %= (int)Math.pow(base, digit);
		num /= (int)Math.pow(base, digit - 1);
		return num;
	}
	
	/**Arranges an array of integers in order according to a given digit.
	 * @param arr: the array to be sorted (note: as this is a static method, the original array is not affected)
	 * @param digit: the desired digit used for comparison
	 * @param  base: the base you are working in (such as 10, 2 (binary), etc)**/
	public static int[] countingSort(int[] arr, int digit, int base) {
		int[] digitArr = new int[arr.length]; //create a new array for the desired digit
		for (int i = 0; i < digitArr.length; i++) {
			digitArr[i] = getDigit(arr[i], digit, base); //fill with the digits only
		}
		int[] trackerArr = new int[base]; //an arary that will keep track of how many of each integer
		for (int i : digitArr) {
			trackerArr[i] += 1; //counts the repeats
		}
		int saved = 0; int saved2 = saved;
		for (int i = 1; i < trackerArr.length; i++) {
			saved2 = trackerArr[i];
			trackerArr[i] = trackerArr[i - 1] + saved;
			saved = saved2; //finds the first index for every integer
		}
		trackerArr[0] = 0; //first 0 needs to start at 0
		int[] finalArr = new int[digitArr.length]; //the return array
		for (int i = 0; i < digitArr.length; i++) {
			finalArr[trackerArr[digitArr[i]]] = arr[i]; //set each location to its appropriate value
			trackerArr[digitArr[i]] ++; //takes care of repeats
		}
		return finalArr;
	}
	
	/**Sorts an array of integers in linear time
	 * @param arr: The array to be sorted
	 * @param max: The maximum value in the array
	 * @param base: The base you are working in (10, 2 (binary), etc)**/
	public static int[] radixSort(int[] arr, int max, int base) {
		for(int i = 1; i < Math.log(max) / Math.log(base) + 1; i++) { //from 0 to number of digits
			arr = countingSort(arr, i, base);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 22, 77, 75, 50, 47, 999, 0, 22, 11};
		int[] radixArr = radixSort(arr, 999, 10);
		for (int i : radixArr) {
			System.out.print(i + " ");
		}
		
	}
}
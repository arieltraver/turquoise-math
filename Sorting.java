package unit1;
import java.util.*;

public class Sorting {
	public static void main(String[] args) {
		final int arrayLength = 8;
		Comparable[] arr = new Comparable[arrayLength];
		arr[0] = 1; arr[1] = 2; arr[2] = 2; arr[3] = 3; arr[4] = 4; arr[5] = 4; arr[6] = 4; 
		arr[7] = 5;
		removeDuplicates(arr);
		for (Comparable i : arr) { System.out.print(i); }
		System.out.println();
		Pair p1 = new Pair(6);
		Pair p2 = new Pair(5, p1);
		Pair p3 = new Pair(4, p2);
		Pair p4 = new Pair(2, p3);
		System.out.println(p4.allToString());
		Pair p11 = new Pair(7);
		Pair p22 = new Pair(3, p11);
		Pair p33 = new Pair(1, p22);
		Pair p44 = new Pair(0, p33);
		System.out.println(p44.allToString());
		System.out.println(merge(p4, p44).allToString());
	}
	
	public static void removeDuplicates(Comparable[] arry) {
		int lastNewIndex = 1; //index of the last known original value
		int currentIndex = 0; //index into which original values will be placed
		while (lastNewIndex < arry.length && currentIndex < arry.length - 1) {
			if (arry[currentIndex].compareTo(arry[lastNewIndex]) != 0) { //if they aren't the same
				arry[currentIndex + 1] = arry[lastNewIndex]; //put the unique index in the current position
				currentIndex++; //onto the next position
			}
			lastNewIndex++; //the last new index to be seen increases
		}
		for(int i = currentIndex + 1; i < arry.length; i++) {
			arry[i] = null; //fill the remaining spots with null
		}
	}
	
	public static Pair merge(Pair list1, Pair list2) {
		if (list1 != null && list2 != null) {
			Comparable a = list1.getFirst(); //value of the first pair
			Comparable b = list2.getFirst();
			if (a.compareTo(b) > 0) {
				Pair list3 = list2.getNext();
				list1 = new Pair (b, list1); //list 1 points to first item of list 2 and the rest of list 1
				list2 = list3; // change list 2 to point to second item
			}
			list1.setNext(merge(list1.getNext(), list2)); //move on to next item of list 1
		}
		if (list1 == null) list1 = list2; //fill with remaining items from list 2
		return list1;
	}
}

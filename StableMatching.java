package unit1;

import java.util.LinkedList;

public class StableMatching {
	
	public static int[] BetterGaleShapley(int[][] mPref, int[][] wPref) {
		int l = mPref.length;
		LinkedList<Integer> freeMen = new LinkedList<Integer>(); //list of available men
		for (int i = 0; i < l; i ++) {
			freeMen.addLast(i);
		}
		int[] nextWoman = new int[l]; //which woman(value) the man(index) will propose to next
		int[] currentMan = new int[l]; //the man(value) to which each woman (index) is engaged
		int[][] rankedMen = new int[l][l];
		for (int i = 0; i < l; i++) {
			nextWoman[i] = 0;
			currentMan[i] = -1; //Set current man list to a special nil value
			for (int j = 0; j < l; j ++) {
				rankedMen[i][wPref[i][j]] = wPref[i][j]; //make a sort of hashed list for easy access
			}
		}
		while(freeMen.size() > 0) { //while men are still available
			int man = freeMen.peek(); //get first item, don't remove
			System.out.println(man);
			int woman = mPref[man][nextWoman[man]];
			nextWoman[man] += 1; //to the next lady
			int currentFiancee = currentMan[woman];
			if (currentFiancee == -1) {
				freeMen.poll(); //remove this man from the free men list
				currentMan[woman] = man; //they are now engaged
			}
			else if (rankedMen[woman][man] > rankedMen[woman][currentFiancee]) {
				freeMen.poll(); //remove this man from the free men list
				freeMen.addFirst(currentFiancee); //add her former fiancee to the free men list
				currentMan[woman] = man; //they are now engaged
			}
		}
		return currentMan;
	}
	
	public static int[] GaleShapley(int[][] mPref, int[][] wPref) {
		int l = mPref.length;
		int[] freeMen = new int[l]; //list of available men
		int freeManIndex = 0;
		for (int i = 0; i < l; i ++) {
			freeMen[i] = i;
		}
		int[] nextWoman = new int[l]; //which woman(value) the man(index) will propose to next
		int[] currentMan = new int[l]; //the man(value) to which each woman (index) is engaged
		int[][] rankedMen = new int[l][l];
		for (int i = 0; i < l; i++) {
			nextWoman[i] = 0;
			currentMan[i] = -1; //Set current man list to a special nil value
			for (int j = 0; j < l; j ++) {
				rankedMen[i][wPref[i][j]] = wPref[i][j]; //make a sort of hashed list for easy access
			}
		}
		while(freeManIndex < l) { //while men are still available
			int man = freeMen[freeManIndex]; //get first item, don't remove
			System.out.println(man);
			int woman = mPref[man][nextWoman[man]];
			nextWoman[man] += 1; //to the next lady
			int currentFiancee = currentMan[woman];
			if (currentFiancee == -1) {
				freeManIndex++; //remove this man from the free men list
				currentMan[woman] = man; //they are now engaged
			}
			else if (rankedMen[woman][man] > rankedMen[woman][currentFiancee]) {
				freeMen[freeManIndex] = currentFiancee; //remove this man from the free men list and add the current one
				currentMan[woman] = man; //they are now engaged
			}
		}
        int[] returnArr = new int[l];
        for (int i : currentMan) { //return the array the way the homework asks for it
            returnArr[currentMan[i]] = i;
            }
        return returnArr;
    }
	
	public static void main(String[] args) {
		int[][] mensPicks = {{3, 1, 2, 0}, {2, 0, 3, 1}, {3, 1, 2, 0}, {0, 1, 2, 3}};
		int[][] ladysPicks = {{2, 3, 1, 0}, {1, 3, 2, 0}, {2, 3, 1, 0}, {2, 1, 3, 0}};
		int[] matchings = GaleShapley(mensPicks, ladysPicks);
		for (int i : matchings) {
			System.out.print(i + " ");
		}
	}

}

package unit1;
import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;

public class Graphs {


	public static LinkedList<Integer>[] makeGraph(String fileName) throws FileNotFoundException {
		 File graphFile = new File(fileName);
	     int graphSize = 0;
		 Scanner nodeCounter = new Scanner(graphFile);
	     while(nodeCounter.hasNextLine()) {
	    	 	graphSize++;
	    	 	nodeCounter.nextLine();
	     }
	     Scanner graphReader = new Scanner(graphFile);
	     LinkedList<Integer>[] graph = new LinkedList[graphSize];
	     for(int k = 0; k < graphSize; k++) {
	    	 	graph[k] = new LinkedList<Integer>();
	    	 	String[] arr = graphReader.nextLine().split(",");
	    	 	for (String str : arr) {
	    	 		graph[k].add(Integer.parseInt(str));
	    	 	}
	     }
	     return graph;
	}
		
	public static int getConnectorNode(String fileName, int node1, int node2) throws FileNotFoundException {
		LinkedList<Integer>[] graph = makeGraph(fileName);
		LinkedList<LinkedList<Integer>> layers = new LinkedList<LinkedList<Integer>>();
		boolean[] isDiscovered = new boolean[graph.length];
		layers.add(graph[node1]);
		for (int i = 0; i < graph.length - 1; i++) {
			layers.add(new LinkedList<Integer>());
		}
		while (layers.size() > 0) {
			LinkedList<Integer> currentLayer = layers.remove();
			for (int i : currentLayer) {
				int count = 0;
				for(int j : graph[i]) {
					if(!isDiscovered[j]) {
						layers.getFirst().add(j);
						isDiscovered[j] = true;
						count++;
					}
				}
				if(count <= 1) {
					return currentLayer.getLast();
				}
			}
		}
		return node1;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		LinkedList<Integer>[] GraphEx = makeGraph("/Users/arieltraver/Desktop/ all/AP CS/AP Java/CS231/src/unit1/GraphExample.txt");
		for(int i = 0; i < GraphEx.length; i++) {
			ListIterator itr = GraphEx[i].listIterator(0);
			while(itr.hasNext()) {
				System.out.print(itr.next() + " ");
			}
			System.out.println();
		}
		System.out.println(getConnectorNode("/Users/arieltraver/Desktop/ all/AP CS/AP Java/CS231/src/unit1/GraphExample.txt", 0, 5));
	}
}

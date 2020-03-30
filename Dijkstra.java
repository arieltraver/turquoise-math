package graphs;
import java.util.*;

public class Dijkstra {
	private int n;
	private int remaining;
	private int source;
	private int[][] graph;
	private int[] distances;
	private boolean[] found;
	private LinkedList<Integer>[] paths;
	
	public Dijkstra(int[][] graph, int source) {
		this.graph = graph;
		this.source = source;
		n = graph.length;
		remaining = n;
		distances = new int[n];
		found = new boolean[n];
		paths = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			distances[i] = Integer.MAX_VALUE;
			found[i] = false;
			paths[i] = new LinkedList<Integer>();
			paths[i].add(source);
		}
		distances[source] = 0;
		found[source] = true;
		this.findShortestPaths();
	}
	
	public int nearestNewNode() {
		int currentNearest = -1;
		int minimumDistance = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			if(!(found[i]) && distances[i] < minimumDistance) {
				minimumDistance = distances[i];
				currentNearest = i;
			}
		}
		return currentNearest;
	}
	public void updateDistances(int vertex) {
		int position = distances[vertex];
		for (int i = 0; i < n; i++) {
			if (position + graph[vertex][i] < distances[i] && graph[vertex][i] != 0) {
				distances[i] = position + graph[vertex][i];	
				paths[i] = (LinkedList<Integer>) paths[vertex].clone();
				paths[i].add(i);
			}
		}
		
	}
	
	public void findShortestPaths() {
		int currentNode = source;
		while (remaining > 1) {
			updateDistances(currentNode);
			currentNode = nearestNewNode();
			found[currentNode] = true;
			remaining--;
		}
	}
	
	public void printShortestPath(int destination) {
		Iterator itr = paths[destination].listIterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
		System.out.println();
	}
	
	public void printDistance(int destination) {
		System.out.println(distances[destination]);
	}
	
	public static void main(String[] args) {
		int[][] testgraph = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
		Dijkstra test = new Dijkstra(testgraph, 0);
		test.printShortestPath(3);
		test.printDistance(3);
	}
		

}

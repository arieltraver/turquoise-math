import java.util.*;

/**
 * @author Ariel Traver
 * @version 1.0
 * Hello and welcome!
 * Here Dijkstra's is implemented as an object.
 * To run the algorithm, simply initialize an object with your input graph and desired source node.
 * Then, use "printShortestPath" or "printDistance" on any destination.
 * This algorithm assumes a fully connected graph.**/
public class Dijkstra {
	private int n; //number of nodes
	private int remaining; //nodes not yet in the graph
	private int source; //source node
	private int[][] graph;
	private int[] distances; //shortest distances from source to each node
	private boolean[] found; //used in the algorithm; nodes which are already accounted for
	private LinkedList<Integer>[] paths; //linked lists of all the shortest paths to each node
	
	/**@param graph: the input graph
	 * @param source: the source node**/
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
	
	/**A utility function which picks out the nearest node by distance to the origin.**/
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
	
	/**A utility function which updates the distances of neighbors to the current node.**/
	public void updateDistances(int vertex) {
		int position = distances[vertex];
		for (int i = 0; i < n; i++) {
			if (position + graph[vertex][i] < distances[i] && graph[vertex][i] != 0) {
				distances[i] = position + graph[vertex][i];	
				paths[i] = (LinkedList<Integer>) paths[vertex].clone();
				//needed to clone it because all the indexes ended up pointing to the same list.
				paths[i].add(i);
			}
		}
		
	}
	
	/**Finds the shortest paths from the source to all other nodes.**/
	public void findShortestPaths() {
		int currentNode = source;
		while (remaining > 1) {
			updateDistances(currentNode);
			currentNode = nearestNewNode();
			found[currentNode] = true;
			remaining--;
		}
	}
	
	/**Prints out the shortest path from the source to a destination node.**/
	public void printShortestPath(int destination) {
		Iterator itr = paths[destination].listIterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
		System.out.println();
	}
	
	/**Prints out the distance from the source to a destination node.**/
	public void printDistance(int destination) {
		System.out.println(distances[destination]);
	}
	
	public static void main(String[] args) {
		//test graph borrowed from GeeksForGeeks.com
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

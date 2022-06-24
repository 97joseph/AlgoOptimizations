
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class UnderstandingAdjacencyList {

	static void play() throws FileNotFoundException {

		Graph g = new Graph();
		String file = "unweighted1.txt";
		g.readUnweightedGraph(file);

		ArrayList<LinkedList<Edge>> adjList = g.adjList;
		for (int i = 0; i < g.numVertices; i++) {
			LinkedList<Edge> row = adjList.get(i);
			System.out.print("Outgoing edges of vertex " + i + ": ");
			Iterator<Edge> it = row.iterator();
			while (it.hasNext()) {
				System.out.print(it.next() + " ");
			}
			System.out.println();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		play();
	}
}

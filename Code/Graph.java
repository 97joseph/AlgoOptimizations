
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {

	public int numVertices;
	public ArrayList<LinkedList<Edge>> adjList;
	
	public void readUnweightedGraph(String filePath) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(filePath));
		numVertices = fileReader.nextInt();
		adjList = new ArrayList<>(numVertices);
		for (int i = 0; i < numVertices; i++) {
			int numEdges = fileReader.nextInt();
			LinkedList<Edge> outEdges = new LinkedList<Edge>();
			for (int j = 0; j < numEdges; j++) {
				Edge edge = new Edge(i, fileReader.nextInt());
				outEdges.add(edge);
			}
			adjList.add(outEdges);
		}
		fileReader.close();
	}
}

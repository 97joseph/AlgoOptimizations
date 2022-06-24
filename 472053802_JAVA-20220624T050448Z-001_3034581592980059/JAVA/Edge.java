

public class Edge {

	int src, dest, weight;

	public Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
	
	public Edge(int src, int dest) {
		this.src = src;
		this.dest = dest;
		this.weight = 1;
	}

	public String toString() {
		return String.format("<%d, %d, %d>", src, dest, weight);
	}
}

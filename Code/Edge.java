
public class Edge {

	int src, dest;
	
	public Edge(int src, int dest) {
		this.src = src;
		this.dest = dest;
	}

	public String toString() {
		return String.format("<%d, %d>", src, dest);
	}
}

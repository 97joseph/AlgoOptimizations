
import java.util.Iterator;
import java.util.LinkedList;

public class DFS extends Graph {

    public int level[];
    private boolean closed[];

    public DFS(String filePath) throws Exception {
        readUnweightedGraph(filePath);
    }

    private void init() { // complete this method
        int[] level = new int[numVertices];
        this.level = level;
        boolean[] closed = new boolean[numVertices];
        this.closed = closed;
        for (int i = 0; i < closed.length; i++) {
            closed[i] = false;
            level[i] = Integer.MAX_VALUE;
        }
    }

    private void run(int s) { // complete this method
        LinkedList<Integer> stack = new LinkedList<>();
        level[s] = 0;
        stack.addLast(s);
        while (stack.isEmpty() == false) {
            int v = stack.removeLast();
            if (closed[v] == true) {
                continue;
            }
            closed[v] = true;
            LinkedList<Edge> row = adjList.get(v);
            Iterator<Edge> itr = row.iterator(); 
            while (itr.hasNext()) {
                Edge adjEdge = itr.next();
                int w = adjEdge.dest;
                if (closed[w] != true) {
                    level[w] = level[v] + 1;
                    stack.addLast(w);
                }
            }
        }
    }

    public void execute(int s) { // complete this method
        init();
        run(s);
    }

    public int countComponents() { // complete this method
        init();
        int counter = 0;
        for (int i = 0; i < numVertices; i++) {
            if (closed[i] != true) {
                run(i);
                counter++;
            }
        }
        return counter;
    }
}

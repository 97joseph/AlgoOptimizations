
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class MutantLanguage extends Graph {

    private String words[];
    private int numWords;
    private int inDegree[];

    public MutantLanguage(String filePath) throws FileNotFoundException {
        readLanguage(filePath);
        makeGraph();
    }

    private void readLanguage(String filePath) throws FileNotFoundException { // complete this method

        Scanner fileReader = new Scanner(new FileInputStream(filePath));
        numVertices = fileReader.nextInt();
        numWords = fileReader.nextInt();
        String wordsLength[] = new String[numWords];
        words = wordsLength;
        for (int i = 0; i < numWords; i++) {
            words[i] = fileReader.next();
        }
        fileReader.close();
    }

    private void makeGraph() { // complete this method
        int temp[] = new int[numVertices];
        inDegree = temp;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            inDegree[i] = 0;
            adjList.add(new LinkedList<Edge>());            
        }
        for (int i = 0; i < numWords - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i+1];
            int minLength;
            if (currentWord.length() < nextWord.length()) {
                minLength = currentWord.length();
            }
            else if (currentWord.length() > nextWord.length()) {
                minLength = nextWord.length();
            }
            else {
                minLength = currentWord.length();
            }
            for (int j = 0; j < minLength; j++) {
                char x = currentWord.charAt(j);
                char y = nextWord.charAt(j);
                if (x != y) {
                    int src = x - 97;
                    int dest = y - 97;
                    Edge e = new Edge(src, dest);
                    adjList.get(src).add(e);
                    inDegree[dest]++;
                    break;
                }
            }
        }
    }

    public char[] getOrder() throws Exception { // complete this method
        char[] topoOrder = new char[numVertices];
        LinkedList<Integer> vertexQ = new LinkedList();
        int topoLevel = 0;
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                vertexQ.addLast(i);
            }
        }
        while (vertexQ.size() > 0) {
            int v = vertexQ.removeLast();
            topoOrder[topoLevel] = (char)(v+97);
            topoLevel++;
            LinkedList<Edge> row = adjList.get(v);
            Iterator<Edge> itr = row.iterator();
            while (itr.hasNext()) {
                Edge adjEdge = itr.next();
                int w = adjEdge.dest;
                inDegree[w]--;
                if (inDegree[w] == 0) {
                    vertexQ.addLast(w);
                }
            }
        }
        if (topoLevel != numVertices) {
            return null;
        }
        else {
            return topoOrder;
        }
    }
}


import java.util.Arrays;

public class TestCorrectness {

    static final String UNWEIGHTED_GRAPH1_PATH = "unweighted1.txt";
    static final String UNWEIGHTED_GRAPH2_PATH = "unweighted2.txt";
    static final String UNDIRECTED_GRAPH1_PATH = "undirected1.txt";
    static final String UNDIRECTED_GRAPH2_PATH = "undirected2.txt";
    static final String MUTANT1_PATH = "mutant1.txt";
    static final String MUTANT2_PATH = "mutant2.txt";
    static final String MUTANT3_PATH = "mutant3.txt";

    private static void testBFS() throws Exception {
        String filePaths[] = { UNWEIGHTED_GRAPH1_PATH, UNWEIGHTED_GRAPH2_PATH };
        for (int i = 0; i < filePaths.length; i++) {
            System.out.printf("*** Test BFS on UnweightedGraph %d ***\n\n", i + 1);
            BFS bfs = new BFS(filePaths[i]);
            for (int j = 0; j < bfs.numVertices; j++) {
                bfs.execute(j);
                System.out.println("Level array (from v" + j + "):   "
                    + Arrays.toString(bfs.level).replaceAll("" + Integer.MAX_VALUE, "infty"));
            }
            System.out.println();
        }
    }

    private static void testTransitiveClosure() throws Exception {

        String filePaths[] = { UNWEIGHTED_GRAPH1_PATH, UNWEIGHTED_GRAPH2_PATH, UNDIRECTED_GRAPH1_PATH,
                UNDIRECTED_GRAPH2_PATH };
        int numFiles = 2;
        for (int i = 0; i < numFiles; i++) {
            System.out.printf("*** Test Transitive Closure on UnweightedGraph %d ***\n\n", i + 1);
            BFS tc = new BFS(filePaths[i]);
            boolean[][] M = tc.computeTransitiveClosure();
            for (int j = 0; j < tc.numVertices; j++)
                System.out.println(Arrays.toString(M[j]));
            System.out.println();
        }
        for (int i = 0; i < numFiles; i++) {
            System.out.printf("*** Test Transitive Closure on UndirectedGraph %d ***\n\n", i + 1);
            BFS tc = new BFS(filePaths[i + 2]);
            boolean[][] M = tc.computeTransitiveClosure();
            for (int j = 0; j < tc.numVertices; j++)
                System.out.println(Arrays.toString(M[j]));
            System.out.println();
        }
    }

    private static void testDFS() throws Exception {
        String filePaths[] = { UNWEIGHTED_GRAPH1_PATH, UNWEIGHTED_GRAPH2_PATH };
        for (int i = 0; i < filePaths.length; i++) {
            System.out.printf("*** Test DFS on UnweightedGraph %d ***\n\n", i + 1);
            DFS dfs = new DFS(filePaths[i]);
            for (int j = 0; j < dfs.numVertices; j++) {
                dfs.execute(j);
                System.out.println("Level array (from v" + j + "):   "
                    + Arrays.toString(dfs.level).replaceAll("" + Integer.MAX_VALUE, "infty"));
            }
            System.out.println();
        }
    }

    private static void testComponent() throws Exception {
        String filePaths[] = { UNDIRECTED_GRAPH1_PATH, UNDIRECTED_GRAPH2_PATH };
        for (int i = 0; i < filePaths.length; i++) {
            System.out.printf("*** Test Component Counter on UndirectedGraph %d ***\n\n", i + 1);
            DFS cc = new DFS(filePaths[i]);
            System.out.println("Number of components is " + cc.countComponents());
            System.out.println();
        }
    }

    private static void testMutantLanguage() throws Exception {
        String filePaths[] = { MUTANT1_PATH, MUTANT2_PATH, MUTANT3_PATH };
        for (int i = 0; i < filePaths.length; i++) {
            System.out.printf("*** Test Mutant Language %d ***\n\n", i + 1);
            MutantLanguage mutant = new MutantLanguage(filePaths[i]);
            char topoOrder[] = mutant.getOrder();
            if (topoOrder != null)
                System.out.println("Alphabet order: " + Arrays.toString(topoOrder));
            else
                System.out.println("Unfortunately, this language has circular dependency.");
            System.out.println();
        }
    }

    private static void testRotatedHelper(int array[], int arrayLen, int keys[], int numKeys) throws Exception {
        System.out.println("Array is: " + Arrays.toString(array));
        System.out.println(
            "Maximum is at index: " + Recursion.maxIndex(array, array[arrayLen - 1], 0, arrayLen - 1) + "\n");
        for (int a = 0; a < numKeys; a++) {
            int index = Recursion.rotatedBinarySearch(array, arrayLen, keys[a]);
            boolean actuallyExists = false;
            for (int i = 0; i < arrayLen; i++)
                if (array[i] == keys[a]) {
                    actuallyExists = true;
                    break;
                }
            if ((actuallyExists && index < 0) || (!actuallyExists && index >= 0)
            || (index >= 0 && array[index] != keys[a])) {
                throw new Exception("Something is wrong!!!");
            }
            if (index >= 0)
                System.out.printf("Key %2d found at index %d\n", keys[a], index);
            else
                System.out.printf("Key %2d not found\n", keys[a]);
        }
    }

    private static void testRotatedArray() throws Exception {
        System.out.println("\n*** Test Rotated Array ***\n");
        int A[] = { 8, 10, 14, 17, 19, 21, 1, 3, 5, 6 };
        int keys_A[] = { 8, 9, 13, 14, 15, 17, 18, 19, 20, 21, 24, 1, 2, 3, 4, 5, 6, 7, 9, 10, 12 };
        testRotatedHelper(A, A.length, keys_A, keys_A.length);

        System.out.println();
        int B[] = { 10, 1, 5, 7 };
        int keys_B[] = { 8, 10, 12, 0, 1, 3, 5, 6, 7, 9 };
        testRotatedHelper(B, B.length, keys_B, keys_B.length);

        System.out.println();
        int C[] = { 12, 1, 5, 7, 10, 11 };
        int keys_C[] = { 8, 10, 11, 12, 0, 1, 3, 5, 6, 7, 9 };
        testRotatedHelper(C, C.length, keys_C, keys_C.length);

        System.out.println();
        int D[] = { 12, 10, 11 };
        int keys_D[] = { 8, 10, 11, 12, 15 };
        testRotatedHelper(D, D.length, keys_D, keys_D.length);

        System.out.println();
        int E[] = { 12, 1 };
        int keys_E[] = { 0, 1, 4, 12, 14 };
        testRotatedHelper(E, E.length, keys_E, keys_E.length);
    }

    private static BinaryTreeNode binaryTree() {
        BinaryTreeNode node_22 = new BinaryTreeNode(22);
        BinaryTreeNode node_8 = new BinaryTreeNode(8);
        BinaryTreeNode node_16 = new BinaryTreeNode(16);
        BinaryTreeNode node_14 = new BinaryTreeNode(14);
        BinaryTreeNode node_20 = new BinaryTreeNode(20);
        BinaryTreeNode node_24 = new BinaryTreeNode(24);
        BinaryTreeNode node_8_2 = new BinaryTreeNode(8);
        BinaryTreeNode node_0 = new BinaryTreeNode(0);
        BinaryTreeNode node_6 = new BinaryTreeNode(6);
        BinaryTreeNode node_11 = new BinaryTreeNode(11);
        BinaryTreeNode node_20_2 = new BinaryTreeNode(20);
        BinaryTreeNode node_5 = new BinaryTreeNode(5);
        BinaryTreeNode node_70 = new BinaryTreeNode(70);

        node_22.left = node_8;
        node_22.right = node_16;
        node_8.left = node_14;
        node_8.right = node_20;
        node_16.left = node_24;
        node_16.right = node_8_2;
        node_14.left = node_0;
        node_14.right = node_6;
        node_20.right = node_11;
        node_8_2.right = node_20_2;
        node_6.left = node_5;
        node_6.right = node_70;

        return node_22;
    }

    private static void testTreeTraversal() {
        System.out.println("*** Test Tree Traversals ***\n");
        System.out.print("Pre-order Traversal: ");
        Recursion.preOrder(binaryTree());
        System.out.print("\nIn-order Traversal: ");
        Recursion.inOrder(binaryTree());
        System.out.print("\nPost-order Traversal: ");
        Recursion.postOrder(binaryTree());
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        testMutantLanguage();
        testBFS();
        testTransitiveClosure();
        testDFS();
        testComponent();
        testTreeTraversal();
        testRotatedArray();
    }
}

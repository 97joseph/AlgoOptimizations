

import java.util.Arrays;

public class TestCorrectness {

	static final String DIRECTED_GRAPH1_PATH = "directed1.txt";
	static final String DIRECTED_GRAPH2_PATH = "directed2.txt";
	static final String UNDIRECTED_GRAPH1_PATH = "undirected1.txt";
	static final String UNDIRECTED_GRAPH2_PATH = "undirected2.txt";
	static final String MUTANT1_PATH = "mutant1.txt";
	static final String MUTANT2_PATH = "mutant2.txt";
	static final String MUTANT3_PATH = "mutant3.txt";

	public static void testOrdered() {
		System.out.println("*** Using Ordered Set & Map ***\n");
		char[] str = { 'a', 'b', 'r', 'a', 'c', 'a', 'd', 'a', 'b', 'r', 'a', '$' };
		System.out
				.println("Alphabet of " + Arrays.toString(str) + " is " + SetsAndMaps.sortedAlphabet(str, str.length));

		System.out.print("Sorted order of " + Arrays.toString(str) + " is ");
		SetsAndMaps.bstSort(str, str.length);
		System.out.println(Arrays.toString(str));
	}

	private static void testZeroSumHelper(int[] arr, int len) {
		boolean isZero = SetsAndMaps.zeroSumSubArray(arr, len);
		System.out.printf("\n%s", Arrays.toString(arr));
		if (!isZero)
			System.out.printf(" has no subarray whose sum is zero.");
		else
			System.out.printf(" has a subarray whose sum is zero.");
	}

	private static void testZeroSum() {
		System.out.println("*** Using Unordered Set & Map ***");
		int[] arr1 = { 12, -26, 1, 8, 9, -6, 4, -12, -3, 12 };
		testZeroSumHelper(arr1, arr1.length);
		int[] arr2 = { 1, 7, 19, -14, 1, -14, 12 };
		testZeroSumHelper(arr2, arr2.length);
		int[] arr3 = { 1, 7, 19, -14, 1, -14, 8, 9, -6, 4, -12, -3, 12 };
		testZeroSumHelper(arr3, arr3.length);
		int[] arr4 = { -6, 4, -12, -3, 12 };
		testZeroSumHelper(arr4, arr4.length);
		int[] arr5 = { -6, 4, -12, 0, -3, 12 };
		testZeroSumHelper(arr5, arr5.length);
		int[] arr6 = { 0, -6, 4, -12, -3, 12 };
		testZeroSumHelper(arr6, arr6.length);
		int[] arr7 = { 1, 8, 9, -6, 4, -12, -3 };
		testZeroSumHelper(arr7, arr7.length);
		int[] arr8 = { 1, 8, 9, 0 };
		testZeroSumHelper(arr8, arr8.length);
	}

	private static void testHeavyHitters() {
		System.out.println();
		int[] arr = { 5, 3, 4, 5, 1, 3, 5, 3, 9, 4, 9, 2, 3, 8, 3, 0, 9, 3, 9 };
		System.out.println("Array is: " + Arrays.toString(arr));
		for (int i = 2; i <= 9; i++)
			System.out.printf("%d-heavy hitters are: %s\n", i, SetsAndMaps.kHeavyHitters(arr, arr.length, i));

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
		TreeTraversals.preOrder(binaryTree());
		System.out.print("\nIn-order Traversal: ");
		TreeTraversals.inOrder(binaryTree());
		System.out.print("\nPost-order Traversal: ");
		TreeTraversals.postOrder(binaryTree());
		System.out.println();
	}

	private static void testBFS() throws Exception {
		String filePaths[] = { DIRECTED_GRAPH1_PATH, DIRECTED_GRAPH2_PATH };
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Test BFS on Directed Graph %d ***\n\n", i + 1);
			BFS bfs = new BFS(filePaths[i]);
			for (int j = 0; j < bfs.numVertices; j++) {
				bfs.execute(j);
				System.out.println("Level array (from v" + j + "):   "
						+ Arrays.toString(bfs.level).replaceAll("" + Integer.MAX_VALUE, "infty"));
			}
			System.out.println();
		}
	}

	private static void testComponent() throws Exception {
		String filePaths[] = { UNDIRECTED_GRAPH1_PATH, UNDIRECTED_GRAPH2_PATH };
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Test Component Counter on Undirected Graph %d ***\n\n", i + 1);
			BFS cc = new BFS(filePaths[i]);
			System.out.println("Number of components is " + cc.countComponents());
			System.out.println();
		}
	}

	private static void testTransitiveClosure() throws Exception {

		String filePaths[] = { DIRECTED_GRAPH1_PATH, DIRECTED_GRAPH2_PATH, UNDIRECTED_GRAPH1_PATH,
				UNDIRECTED_GRAPH2_PATH };
		int numFiles = 2;
		for (int i = 0; i < numFiles; i++) {
			System.out.printf("*** Test Transitive Closure on Directed Graph %d ***\n\n", i + 1);
			BFS app = new BFS(filePaths[i]);
			boolean[][] tc = app.computeTransitiveClosure();
			for (int j = 0; j < app.numVertices; j++)
				System.out.println(Arrays.toString(tc[j]));
			System.out.println();
		}
		for (int i = 0; i < numFiles; i++) {
			System.out.printf("*** Test Transitive Closure on Undirected Graph %d ***\n\n", i + 1);
			BFS app = new BFS(filePaths[i + 2]);
			boolean[][] tc = app.computeTransitiveClosure();
			for (int j = 0; j < app.numVertices; j++)
				System.out.println(Arrays.toString(tc[j]));
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

	public static void main(String[] args) throws Exception {
		testTreeTraversal();
		System.out.println();
		testOrdered();
		System.out.println();
		testZeroSum();
		System.out.println();
		testHeavyHitters();
		System.out.println();
		testBFS();
		testComponent();
		testTransitiveClosure();
		testMutantLanguage();
	}
}

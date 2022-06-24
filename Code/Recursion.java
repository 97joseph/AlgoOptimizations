public class Recursion {

    public static void preOrder(BinaryTreeNode node) { // complete this method
        System.out.print(node.value + "");
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    public static void inOrder(BinaryTreeNode node) { // complete this method

        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.value + "");
        inOrder(node.right);	    
    }

    public static void postOrder(BinaryTreeNode node) { // complete this method
        if (node == null) {
            return;
        }
        postOrder(node.left);	       
        postOrder(node.right);
        System.out.print(node.value + "");

    }

    public static int rotatedBinarySearch(int array[], int length, int key) { // complete this method
        int maxInd = maxIndex(array, array[length-1] , 0, (length-1));
        if (key == array[maxInd]) {
            return maxInd;
        }
        if (key >= array[0]) {
            return binarySearch(array, 0, maxInd - 1, key);           
        }
        else {
            return binarySearch(array, maxInd + 1, length-1, key);
        }
    }

    public static int maxIndex(int array[], int lastValue, int left, int right) { // complete this method
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;
        if (array[mid] > array[mid + 1]) {
            return mid;
        }
        else if (array[mid] < lastValue) {
            return maxIndex(array, lastValue, left, mid-1);
        }
        else {
            return maxIndex(array, lastValue, mid+1, right);
        }
        //return mid;
    }

    public static int binarySearch(int array[], int left, int right, int key) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key)
                return binarySearch(array, mid + 1, right, key);
            else
                return binarySearch(array, left, mid - 1, key);
        }
        return -1;
    }
}

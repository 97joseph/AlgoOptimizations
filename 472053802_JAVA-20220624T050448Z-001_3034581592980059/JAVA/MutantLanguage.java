

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
	}

	private void makeGraph() { // complete this method
	}

	public char[] getOrder() throws Exception { // complete this method
	}
}

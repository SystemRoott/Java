package mainPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class mainClass {

	private void addRelationsAndWeights(String data, Graph graph, int index) {
		// Split incoming line
		String[] fragmentedData = data.split("-");
		// Check data
		if (fragmentedData.length != graph.neighbors.size()) {
			System.out.println("Error: Too much relation");
			System.out.println("Node count: " + (graph.neighbors.size() - 1));
			System.out.println("Error is here: " + data);
			System.exit(0);
		}
		// Add film to the film list
		graph.weights.addLast(fragmentedData[0]);
		// Mark which id is 1
		LinkedList<Integer> Ids = new LinkedList<Integer>();
		for (int i = 1; i < fragmentedData.length; i++) {
			if (fragmentedData[i].equalsIgnoreCase("1")) {
				Ids.addLast(i);
			}
		}
		// if count of Id is even number, process ids go in pairs
		if (Ids.size() % 2 == 0) {
			for (int i = 0; i < Ids.size(); i += 2) {
				graph.addRelation(Ids.get(i), Ids.get(i + 1), index);
			}
		}
		// Else make combination
		else if (Ids.size() % 2 == 1) {
			while (Ids.size() != 0) {
				for (int i = 1; i < Ids.size() && Ids.size() != 1; i++) {
					graph.addRelation(Ids.get(0), Ids.get(i), index);
				}
				if (Ids.size() == 1) {
					graph.addRelation(Ids.get(0), Ids.get(0), index);
				}
				Ids.removeFirst();
			}
		}

	}

	private static Graph getGraphFromFile(File file) {
		Graph result = null;
		try {
			// Reading via BufferedReader
			BufferedReader br = new BufferedReader(new FileReader(file));
			// Reading First Line
			String line = br.readLine();
			// Split
			String[] tmp = line.split("-");
			// First data is unnecessary
			tmp[0] = "";
			// List of actors
			LinkedList<String> neighbors = new LinkedList<String>();
			// First id is 1
			neighbors.addLast("");
			// Add actors to the list
			for (int i = 1; i < tmp.length; i++) {
				neighbors.addLast(tmp[i]);
			}
			// Graph Definition
			result = new Graph(neighbors);
			// Reading Films
			int index = 1;
			mainClass main = new mainClass();
			// reading and processing line by line
			for (String line1; (line1 = br.readLine()) != null;) {
				main.addRelationsAndWeights(line1, result, index);
				index++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {

		File file = new File("C:\\graphdata.txt");
		Graph graph = getGraphFromFile(file);

		graph.print();

		System.out.println("��kmak i�in ilk ki�i ad�na 'bitir' yaz�n�z.");
		System.out.println("GraphData.txt'yi tekrar okumak i�in ilk ki�i ad�na 'yenile' yaz�n�z.");
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.print("1. Oyuncu Ad�n� Giriniz: ");
			String name1 = input.nextLine();
			if (name1.equalsIgnoreCase("bitir")) {
				break;
			}if (name1.equalsIgnoreCase("yenile")) {
				graph = getGraphFromFile(file);
				System.out.println("Okuma tamamland�");
				graph.print();
				continue;
			}
			System.out.print("2. Oyuncu Ad�n� Giriniz: ");
			String name2 = input.nextLine();
			int from = graph.findNeigbor(name1);
			int to = graph.findNeigbor(name2);
			if (from == 0) {
				System.out.println(name1 + " adl� oyuncu tan�ml� de�il.");
				continue;
			}
			if (to == 0) {
				System.out.println(name2 + " adl� oyuncu tan�ml� de�il.");
				continue;
			}
			PathClass path = graph.findPath(from, to, new LinkedList<Integer>());
			if (path != null) {
				for (int i = 1; i < path.path.size(); i++) {
					String Name1 = graph.neighbors.get(path.path.get(i - 1));
					String Name2 = graph.neighbors.get(path.path.get(i));
					String MovieName = graph.weights.get(graph.relationships[path.path.get(i - 1)][path.path.get(i)]);
					if (i == 1) {
						System.out.println(Name1 + " '" + MovieName + "' filminde  " + Name2 + " ile rol ald�. ");
					} else {
						System.out.println(Name1 + " da '" + MovieName + "' filminde  " + Name2 + " ile rol ald�. ");
					}

				}
				System.out.println("");
			} else {
				System.out.println(name1 + " ile " + name2 + " aras�nda hi�bir ba�lant� bulunamad�.");
			}
		}

	}

}

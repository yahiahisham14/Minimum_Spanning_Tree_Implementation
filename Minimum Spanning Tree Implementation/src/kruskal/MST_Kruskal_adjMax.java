package kruskal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import dataStructures.Edge;
import dataStructures.UnionFind;

public class MST_Kruskal_adjMax {

	// Number of nodes.
	static int nodes;
	// Infinity
	static double INF = Double.MAX_VALUE;
	static boolean disconnected = false;
	static double[][] adjMax;
	static ArrayList<Edge> path;
	static double mstCost = 0;
	static long time;
	// Main Algorithm
	public static void kruskal() {
		long startTime=System.nanoTime();
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		UnionFind uF = new UnionFind(nodes);

		for (int i = 0; i < adjMax.length; i++) {
			for (int j = 0; j < adjMax[i].length; j++) {
				if (adjMax[i][j] != INF)
					pq.add(new Edge(i, j, adjMax[i][j]));

			}
		}

		while (!pq.isEmpty()) {

			Edge currEdge = pq.poll();
			int from = currEdge.from;
			int to = currEdge.to;

			if (UnionFind.find(from) != UnionFind.find(to)) {
				mstCost += currEdge.w;
				path.add(currEdge);
				UnionFind.makeUnion(from, to);
			}

		}
		
		//End Time
		long endTime=System.nanoTime();
		time=endTime-startTime;

	}

	// This method used to read ip of adjacency matrix.
	public static void adjMaxIp(String filePath) throws NumberFormatException,
			IOException {
		try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
			nodes = Integer.parseInt(bf.readLine());
			adjMax = new double[nodes + 1][nodes + 1];
			path = new ArrayList<Edge>();

			// initialize the array with INF;
			for (int i = 0; i < adjMax.length; i++) {
				Arrays.fill(adjMax[i], INF);
			}
			int edges = Integer.parseInt(bf.readLine());

			for (int i = 0; i < edges; i++) {
				String[] s = bf.readLine().split(",");
				int from = Integer.parseInt(s[0]);
				int to = Integer.parseInt(s[1]);
				double weight = Double.parseDouble(s[2]);

				if (weight<adjMax[from][to]){
					adjMax[from][to] = weight;
					adjMax[to][from]=weight;
				}
				
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// This method made for printing output of adjacecny list.
	public static void oP(String filePath) {
		
		try {
			 
			File file = new File(filePath+"KruskalMaxOutPut.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			if (disconnected) {
				bw.write("ERROR:Graph is disconnected");
				bw.write(System.getProperty("line.separator"));
			} else {
				bw.write("Cost " + mstCost+"             Time Taken : "+time+" ms.");
				bw.write(System.getProperty("line.separator"));
				bw.write(System.getProperty("line.separator"));
				bw.write("From    TO    Weight");
				bw.write(System.getProperty("line.separator"));
				for (int i = 0; i < path.size(); i++) {
					bw.write(path.get(i).from + "  --->  " + path.get(i).to
							+ "      " + path.get(i).w);
					bw.write(System.getProperty("line.separator"));
				}
			}
			bw.write("Finished kruskal adjacency Matrix");
			bw.write(System.getProperty("line.separator"));
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	public static ArrayList<Edge> getPath(){
		return path;
	}

}

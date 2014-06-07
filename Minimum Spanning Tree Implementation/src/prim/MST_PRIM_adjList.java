package prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import dataStructures.Edge;

public class MST_PRIM_adjList {

	// Number of nodes.
	static int nodes;
	// Infinity
	static double INF = Double.MAX_VALUE;
	static boolean disconnected = false;
	static ArrayList<Edge> adjList[];
	static ArrayList<Edge> path;
	static double mstCost=0;
	static long time;

	// Main Algorithm
	public static void primsList() {
		
		//begin time
		long startTime=System.nanoTime();
		
		boolean visited[] = new boolean[nodes + 1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

		pq.add(new Edge(-1, 1, 0));

		while (!pq.isEmpty()) {
			Edge e = pq.poll();

			if (visited[e.to])
				continue;

			visited[e.to] = true;

			mstCost += e.w;

			if (e.from != -1)
				path.add(e);

			for (int i = 0; i < adjList[e.to].size(); i++) {
				Edge newEdge = adjList[e.to].get(i);
				if (!visited[newEdge.to]) {
					pq.add(newEdge);
				}
			}
		
		}
		
		//end time
		long endTime=System.nanoTime();
		time=endTime-startTime;

	}

	// This method made for reading inputs stored in adjacencey list.
	public static void adjListIp(String filePath) throws IOException {

		try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {	

			nodes = Integer.parseInt(bf.readLine());
			adjList = new ArrayList[nodes + 1];
			path = new ArrayList<Edge>();
			for (int i = 0; i < nodes + 1; i++) {
				adjList[i] = new ArrayList<Edge>();
			}
			int edges = Integer.parseInt(bf.readLine());

			for (int i = 0; i < edges; i++) {
				String[] s = bf.readLine().split(",");
				int from = Integer.parseInt(s[0]);
				int to = Integer.parseInt(s[1]);
				double weight = Double.parseDouble(s[2]);

				adjList[from].add(new Edge(from, to, weight));
				adjList[to].add(new Edge(to, from, weight));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// This method made for printing output of adjacecny list.
	public static void adjListOp(String filePath) {
		
		try {
 
			File file = new File(filePath+"PrimOutPut.txt");
 
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
			bw.write("Finished Prim adjacent List");
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

package prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import dataStructures.Edge;

public class MST_PRIM_adjMax {

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
	public static void primAdjMax() {

		//begin time
		long beginTime=System.nanoTime();
		
		//current node
		int current=1;
		
		//minimum distance to enter the MST per n-1 loop
		double mind;
		int mini = -1;// The node which will join the Mst each time.

		// visited array for the nodes.
		boolean visited[] = new boolean[nodes + 1];
		// minimum distance a node can join the MST.
		double[] distance = new double[nodes + 1];
		Arrays.fill(distance, INF);
		// previous array to know the path
		int[] previous = new int[nodes + 1];

		// initialize the first node//
		distance[1] = 0;
		previous[1]=0;
		// Loop n-1 times , each time u garantee to take an edge.
			
		for (int k = 1; k < nodes; k++) {

			mind = INF;
			mini = -1;

			//mark the currnt node visited
			visited[current]=true;
			
			for (int i=1;i<nodes+1;i++){
				
				//if i not in the mst
				if (!visited[i]){
					//if the current node can relax any edges.
					if (adjMax[current][i]<distance[i]){
						distance[i]=adjMax[current][i];
						previous[i]=current;
					}
					
					//check every node searching for the minimum distance in this loop.
					if (distance[i]<mind){
						//make its distance is the minimum and take it as a mst candidate.
						mind=distance[i];
						mini=i;
					}
				}
			}
			// check for disconnected graph.
			if (mini == -1){
				disconnected = true;
				break;
			}

			if (previous[mini] != 0) {
				// push back the new edge to the mst.
				path.add(new Edge(previous[mini], mini,
						adjMax[previous[mini]][mini]));
			}
			
			//add the candidate to the mst.
			current=mini;
			mstCost+=mind;

		}
		//endTime
		long endTime=System.nanoTime();
		time=endTime-beginTime;

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
			 
			
 
			File file = new File(filePath+"PrimMaxOutPut.txt");
 
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
			bw.write("Finished prim adjacent Matrix");
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

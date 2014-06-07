package dataStructures;
public class Edge implements Comparable<Edge> {

	public int from, to;
	public double w;

	public Edge(int f, int t, double W) {
		from = f;
		to = t;
		w = W;
	}

	@Override
	public int compareTo(Edge o) {

		return Double.valueOf(w).compareTo(o.w);
	}
}

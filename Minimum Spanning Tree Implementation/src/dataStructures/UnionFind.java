package dataStructures;
public class UnionFind {
	static int[] parent;

	public UnionFind(int nodes) {
		parent = new int[nodes + 1];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}

	public static void makeUnion(int x, int y) {
		parent[find(x)] = find(y);
	}

	public static int find(int kid) {
		if (parent[kid] == kid)
			return kid;

		return parent[kid] = find(parent[kid]);

	}

}

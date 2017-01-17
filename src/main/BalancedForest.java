package main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BalancedForest {

	static Node[] allNodes;
	static Edge[] allEdges;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int q, n, a, b;
		Long minValue = null;
		q = sc.nextInt();

		for (int z = 0; z < q; z++) {
			n = sc.nextInt();
			allNodes = new Node[n + 1];
			allEdges = new Edge[n - 1];
			boolean flag = false;
			minValue = null;
			for (int i = 1; i <= n; i++) {
				allNodes[i] = new Node(i, sc.nextInt());
			}
			for (int i = 0; i < n - 1; i++) {
				a = sc.nextInt();
				b = sc.nextInt();
				allEdges[i] = new Edge(a, b);
				allNodes[a].getChilds().add(allNodes[b]);
				allNodes[b].getChilds().add(allNodes[a]);
			}

			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n - 1; j++) {
					removeEdge(allEdges[i]);
					removeEdge(allEdges[j]);
					Node tree1 = allNodes[allEdges[i].a];
					Node tree2 = allNodes[allEdges[i].b];
					Node tree3 = allNodes[allEdges[j].a];
					Node tree4 = allNodes[allEdges[j].b];
					if ((isReachable(tree1, tree3, new boolean[n + 1]))
							|| (isReachable(tree2, tree3, new boolean[n + 1]))) {
						tree3 = tree4;
					}

					long sumOfAllCoinsTree1 = sumOfAllCoins(tree1, n);
					long sumOfAllCoinsTree2 = sumOfAllCoins(tree2, n);
					long sumOfAllCoinsTree3 = sumOfAllCoins(tree3, n);

					if (sumOfAllCoinsTree1 == sumOfAllCoinsTree2
							&& sumOfAllCoinsTree3 < sumOfAllCoinsTree1) {
						
						if (minValue == null) {
							minValue = sumOfAllCoinsTree1 - sumOfAllCoinsTree3;
						} else {
							if (minValue > (sumOfAllCoinsTree1 - sumOfAllCoinsTree3)) {
								minValue = sumOfAllCoinsTree1
										- sumOfAllCoinsTree3;
							}
						}
					} else if (sumOfAllCoinsTree1 == sumOfAllCoinsTree3
							&& sumOfAllCoinsTree2 < sumOfAllCoinsTree1) {

						if (minValue == null) {
							minValue = sumOfAllCoinsTree1 - sumOfAllCoinsTree2;
						
						} else {
							if (minValue > (sumOfAllCoinsTree1 - sumOfAllCoinsTree2)) {
								minValue = sumOfAllCoinsTree1
										- sumOfAllCoinsTree2;
						
							}
						}

					} else if (sumOfAllCoinsTree2 == sumOfAllCoinsTree3
							&& sumOfAllCoinsTree1 < sumOfAllCoinsTree2) {

						if (minValue == null) {
							minValue = sumOfAllCoinsTree2 - sumOfAllCoinsTree1;
							
						} else {
							if (minValue > (sumOfAllCoinsTree2 - sumOfAllCoinsTree1)) {
								minValue = sumOfAllCoinsTree2
										- sumOfAllCoinsTree1;
							
							}
						}
					}

					addEdge(allEdges[i]);
					addEdge(allEdges[j]);
				}
			}
			if (minValue == null) {
				for (int i = 0; i < allEdges.length; i++) {
					removeEdge(allEdges[i]);
					if (sumOfAllCoins(allNodes[allEdges[i].a], n) == sumOfAllCoins(
							allNodes[allEdges[i].b], n)) {
						System.out.println(sumOfAllCoins(
								allNodes[allEdges[i].a], n));
						flag = true;
						break;
					} else {
						addEdge(allEdges[i]);
					}
				}
				if (!flag)
					System.out.println(-1);
			} else {
				System.out.println(minValue);
			}

		}
	}

	public static long sumOfAllCoins(Node root, int n) {
		boolean[] visited = new boolean[n + 1];
		return sumOfAllCoins(root, visited);
	}

	public static long sumOfAllCoins(Node root, boolean[] visited) {
		long sum = root.getCoins();
		visited[root.getId()] = true;
		for (Node node : root.getChilds()) {
			if (!visited[node.getId()])
				sum += sumOfAllCoins(node, visited);
		}
		return sum;
	}
	public static void removeEdge(Edge e) {
		allNodes[e.a].getChilds().remove(allNodes[e.b]);
		allNodes[e.b].getChilds().remove(allNodes[e.a]);
	}

	public static void addEdge(Edge e) {
		allNodes[e.a].getChilds().add(allNodes[e.b]);
		allNodes[e.b].getChilds().add(allNodes[e.a]);
	}

	public static boolean isReachable(Node root, Node node, boolean[] visited) {

		if (root.getId() == node.getId()) {
			return true;
		} else {
			visited[root.getId()] = true;
			;
			for (Node node2 : root.getChilds()) {
				if (!visited[node2.getId()]) {
					if (isReachable(node2, node, visited)) {
						return true;
					}
				}
			}
			return false;
		}
	}
}

class Edge {
	int a;
	int b;
	public Edge() {
	}
	public Edge(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
}

class Node implements Comparable<Node> {
	private int id;
	private long coins;
	private Set<Node> childs;

	Node(int id, long coins) {
		this.id = id;
		this.coins = coins;
		childs = new HashSet<Node>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCoins() {
		return coins;
	}

	public void setCoins(long coins) {
		this.coins = coins;
	}

	public Set<Node> getChilds() {
		return childs;
	}

	public void setChilds(Set<Node> childs) {
		this.childs = childs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		if (this.id == o.id)
			return 0;
		if (this.id < o.id)
			return -1;
		if (this.id > o.id)
			return 1;
		return 0;
	}
}
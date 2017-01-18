package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class IceLandProblem {

    static int start = 0, end = 0, edgeStart, edgeEnd;
    static String inputData = null, strNodes, strEdges, strEdge;
    static Node[] nodes;
    static List<Edge> edges = new ArrayList<>();
    static Node tempNode = null;
    static Edge tempEdge = null;
    static Stack<Node> stack = null;
    static StringBuffer solution = new StringBuffer();
    static int solutionCount = 0;
    static int between;
    static List<String> solutionList = new ArrayList<String>();

    public static void main(String[] args) {
        // new String("({A,B,C,D,E,F},{(A,C),(B,C),(C,E),(B,E),(B,D),(E,F)})")
        // new String("({A,B,C},{(A,B),(B,C),(C,A)}),({A,B,C,D,E},{(A,B),(B,C),(C,A),(E,D),(D,A)})"))
        // System.out.println(criticalBridges(1,new String("({A,B,C,D,E,F},{(A,C),(B,C),(C,E),(B,E),(B,D),(E,F)})")));
        System.out.println(
                criticalBridges(3, new String("({A,B,C},{(A,B),(B,C),(C,A)}),({A,B,C,D,E},{(A,B),(B,C),(C,A),(E,D),(D,A)}),({A,B,C,D,E,F},{(A,C),(B,C),(C,E),(B,E),(B,D),(E,F)})")));

    }

    public static String criticalBridges(int input1, String input2) {
        int N = input1;
        IceLandProblem iceLandProblem = new IceLandProblem();
        while (true) {
            start = input2.indexOf("(", end);
            if (start != -1)
                end = getEndOfBracket(input2, start);
            if (start != -1 && end != -1) {
                inputData = input2.substring(start + 1, end);
                strNodes = inputData.substring(inputData.indexOf("{", 0) + 1, inputData.indexOf("}", 0));
                between = inputData.indexOf("}", 0) + 1;
                StringTokenizer st = new StringTokenizer(strNodes, ",");
                int i = 0;
                nodes = new Node[st.countTokens()];
                edges = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    tempNode = iceLandProblem.new Node();
                    tempNode.name = st.nextToken().charAt(0);
                    nodes[i++] = tempNode;
                }
                edgeEnd = 0;
                edgeStart = 0;
                int tempStart = inputData.indexOf("{", between) + 1, tempEnd = inputData.indexOf("}", between);
                strEdges = inputData.substring(tempStart, tempEnd);

                while (true) {
                    edgeStart = strEdges.indexOf("(", edgeEnd);
                    edgeEnd = strEdges.indexOf(")", edgeEnd + 1);
                    if (edgeStart != -1 && edgeEnd != -1) {
                        strEdge = strEdges.substring(edgeStart + 1, edgeEnd);
                        tempEdge = iceLandProblem.new Edge();
                        tempEdge.critical = false;
                        if(strEdge.charAt(0) - 'A'<strEdge.charAt(strEdge.length() - 1) - 'A'){
                            tempEdge.start = nodes[strEdge.charAt(0) - 'A'];
                            tempEdge.end = nodes[strEdge.charAt(strEdge.length() - 1) - 'A'];
                        }
                        else{
                            tempEdge.end = nodes[strEdge.charAt(0) - 'A'];
                            tempEdge.start = nodes[strEdge.charAt(strEdge.length() - 1) - 'A'];
                        }
                        edges.add(tempEdge);
                        addEdge(tempEdge);
                    }
                    else {

                        if (!edges.isEmpty()) {
                            for (Edge edge : edges) {
                                if (isEdgeCritical(edge)) {
                                    edge.critical = true;
                                }
                            }
                        }
                        boolean flag = false;
                        Collections.sort(edges, iceLandProblem.new EdgesComparator());
                        for (Edge edge : edges) {
                            if (edge.critical == true) {
                                flag = true;
                                solution.append("(" + edge.start.name + "," + edge.end.name + "),");
                            }
                        }

                        if (!flag) {
                            solutionList.add("NA");
                        }
                        else {
                            solutionList.add(solution.substring(0, solution.length() - 1));
                        }
                        solution = new StringBuffer();
                        break;

                    }
                }
            }
            else {
                for (String string : solutionList) {
                    solution.append("{" + string + "},");
                }
                if (input1 > 1) {
                    solution = new StringBuffer(solution.substring(0, solution.length() - 1));
                    solution.append("}");
                    solution.insert(0, "{");
                }
                return solution.substring(0, solution.length() - 1);
            }
        }
    }

    private static int getEndOfBracket(String string, int start2) {
        Stack<String> stack = new Stack<>();
        int i = -1;
        for (i = start2; i < string.length(); i++) {
            if (string.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
            }
            else if (string.charAt(i) == '(') {
                stack.push("(");
            }
        }
        return i;
    }

    private static boolean isEdgeCritical(Edge edge) {
        removeEdge(edge);
        stack = new Stack<Node>();
        for (Node node : nodes) {
            node.considered = false;
        }
        if (!isReachable(edge.start, edge.end)) {
            addEdge(edge);
            return true;
        }
        addEdge(edge);
        return false;
    }

    private static boolean isReachable(Node node, Node toReach) {
        node.considered = true;
        for (Node tempNode : node.edges) {
            if (tempNode.considered == false) {
                if (tempNode.name == toReach.name)
                    return true;
                else {
                    tempNode.considered = true;
                    stack.push(tempNode);
                }
            }
        }
        while (!stack.isEmpty()) {
            for (Node tempNode : stack.pop().edges) {
                if (tempNode.considered == false) {
                    if (tempNode.name == toReach.name)
                        return true;
                    else {
                        tempNode.considered = true;
                        stack.add(tempNode);
                    }
                }
            }
        }
        return false;
    }

    private static void removeEdge(Edge edge) {

        edge.start.edges.remove(edge.end);
        edge.end.edges.remove(edge.start);
    }

    private static void addEdge(Edge tempEdge) {
        tempEdge.start.edges.add(tempEdge.end);
        tempEdge.end.edges.add(tempEdge.start);
    }

    class Node {
        char name;
        List<Node> edges = new ArrayList<Node>();
        boolean considered;
    }

    class Edge {
        Node start;
        Node end;
        boolean critical;
    }
    class EdgesComparator implements Comparator<Edge>, Serializable {
        
        /** {@inheritDoc} */
        public int compare(Edge arg0, Edge arg1) {
            if(new Character(arg0.start.name).equals(new Character(arg1.start.name)))
                return new Character(arg0.end.name).compareTo(new Character(arg1.end.name)) ;
            return new Character(arg0.start.name).compareTo(new Character(arg1.start.name)) ;
        }
    
    }

}

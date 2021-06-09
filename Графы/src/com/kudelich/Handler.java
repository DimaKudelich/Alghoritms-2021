package com.kudelich;

public class Handler {
    public static void printGraph(Graph graph) {
        for (int i = 0; i < graph.getEdges().length; i++) {
            System.out.println(graph.getEdges()[i].getV1().getName() + ";" + graph.getEdges()[i].getV2().getName());
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ";");
        }
        System.out.println();
    }

    public static void printAdjacencyStructure(Graph graph) {
        int[][] adjacencyStructure = graph.getAdjacencyStructure();

        for (int i = 0; i < adjacencyStructure.length; i++) {
            System.out.print(adjacencyStructure[i][0] + ":");
            for (int j = 1; j < adjacencyStructure[i].length; j++) {
                System.out.print(adjacencyStructure[i][j] + ";");
            }
            System.out.println();
        }
    }

    public static void printAdjacencyStructure2(DirectedGraph graph) {
        int[][] adjacencyStructure = graph.getAdjacencyStructure();

        for (int i = 0; i < adjacencyStructure.length; i++) {
            System.out.print(adjacencyStructure[i][0] + ":");
            for (int j = 1; j < adjacencyStructure[i].length; j++) {
                System.out.print(adjacencyStructure[i][j] + ";");
            }
            System.out.println();
        }
    }

    public static void printAdjacencyMatrix(Graph graph) {
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();

        printMatrix(adjacencyMatrix);
    }

    public static void printAdjacencyMatrix2(DirectedGraph graph) {
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();

        printMatrix(adjacencyMatrix);
    }

    public static void printIncidentMatrix(Graph graph) {
        int[][] incidentMatrix = graph.getIncidentMatrix();

        printMatrix(incidentMatrix);
    }

    public static void printIncidentMatrix2(DirectedGraph graph) {
        int[][] incidentMatrix = graph.getIncidentMatrix();

        printMatrix(incidentMatrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "; ");
            }
            System.out.println();
        }
    }

    public static int findMax(int[] array) {
        int max =  array[0];

        for (int i = 1; i < array.length; i++) {
            max = Math.max(array[i], max);
        }

        return max;
    }

    public static int findMin(int[] array) {
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            min = Math.min(array[i], min);
        }

        return min;
    }

    public static int findIndexWithMin(int[] array) {
        int min = array[0];
        int index = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i]<min){
                min = array[i];
                index = i;
            }
        }

        return index;
    }

    public static int findIndexWithMax(int[] array) {
        int max = array[0];
        int index = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i]>max){
                max = array[i];
                index = i;
            }
        }

        return index;
    }

    public static int[] specialSumOfRows(int []row1,int[]row2){
        for (int i = 0; i < row1.length; i++) {
            row1[i] = Math.max(row1[i],row2[i]);
        }
        return row1;
    }

    public static void printGreedyProcess(int []colors, int []degrees, int []bans){
        for (int i = 0; i < colors.length; i++) {
            System.out.print(colors[i] + ";");
        }
        System.out.println();
        for (int i = 0; i < degrees.length; i++) {
            System.out.print(degrees[i]+ ";");
        }
        System.out.println();
        for (int i = 0; i < bans.length; i++) {
            System.out.print(bans[i]+ ";");
        }
        System.out.println();
    }

    public static int[] addNewToStack(int[]stack,int newVertex){
        int []copyOfStack = new int[ stack.length+1];

        for (int i = 0; i < stack.length; i++) {
            copyOfStack[i] = stack[i];
        }
        copyOfStack[copyOfStack.length-1] = newVertex;

        return copyOfStack;
    }

    public static int goToLastVertex(int[] stack,int vertex){
        int last = 0;

        for (int i = stack.length-1; i >0 ; i--) {
            if (stack[i]==vertex){
                last = stack[i-1];
            }
        }

        return last;
    }

    public static boolean isVertexInStack(int[]stack,int vertex){
        for (int i = 0; i < stack.length; i++) {
            if(stack[i]==vertex){
                return true;
            }
        }
        return false;
    }

    public static int[][] addEdge(int[][]edges,int []edge){
        int[][]copy = new int[edges.length+1][2];

        for (int i = 0; i < edges.length; i++) {
            copy[i]=edges[i];
        }

        copy[copy.length-1] = edge;

        return copy;
    }
}

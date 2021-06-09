package com.kudelich;

public class Main {
    public static void main(String[] args) {
        int[] verticesList = {1, 2, 3, 4, 5, 6, 7};
        int[][] edgesList = {{1, 4}, {1, 5}, {2, 5}, {2, 6}, {2, 7}, {3, 6}, {3, 7}, {4, 5}, {5, 6}, {6, 7}};

        Graph graph = new Graph(verticesList, edgesList);

        //Handler.printGraph(graph);
        //Handler.printAdjacencyStructure(graph);
        //Handler.printAdjacencyMatrix(graph);
        //Handler.printIncidentMatrix(graph);
        //Handler.printArray(graph.greedyColoring());
        //Handler.printArray(graph.coloringWithAdjacencyMatrix());

        int[] verticesList1 = {1, 2, 3, 4, 5, 6, 7};
        int[][] edgesList1 = {{1, 4}, {1, 5}, {2, 6}, {2, 7}, {4, 5}, {5, 2}, {6, 3}, {6, 2}, {6, 5}, {6, 7}, {7, 3}};

        DirectedGraph graph1 = new DirectedGraph(verticesList1, edgesList1);
        //Handler.printAdjacencyMatrix2(graph1);
        //Handler.printAdjacencyStructure2(graph1);
        //Handler.printIncidentMatrix2(graph1);
        //graph1.bfs();
        /*. ({a,b,c,d}: (a,b), (a,c), (b,c), (b,d), (c,d)). */
        int[]verticesList2 = {1,2,3,4};
        int [][]edgesList2 = {{1,2},{1,3},{2,3},{2,4},{3,4}};

        Graph graph2 = new Graph(verticesList2,edgesList2);
        Graph graph3 = graph2.convertToGraphForEdgeColoring();
        //Handler.printAdjacencyStructure(graph3);
        Handler.printArray(graph3.coloringWithAdjacencyMatrix());
    }
}

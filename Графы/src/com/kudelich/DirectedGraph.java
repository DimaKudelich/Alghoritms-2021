package com.kudelich;

public class DirectedGraph {
    private Vertex[] vertices;
    private Edge[] edges;

    public DirectedGraph(int [] verticesList,int [][] edgesList){
        vertices = new Vertex[verticesList.length];

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(verticesList[i]);
        }

        edges = new Edge[edgesList.length];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new Edge(new Vertex(edgesList[i][0]),new Vertex(edgesList[i][1]));
        }
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public boolean isVerticesAdjacency(Vertex v1, Vertex v2){
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getV1().getName() == v1.getName() && edges[i].getV2().getName() == v2.getName()){
                return true;
            }
        }
        return false;
    }

    public int[][] getAdjacencyMatrix(){
        int[][]adjacencyMatrix = new int[vertices.length][vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (isVerticesAdjacency(vertices[i],vertices[j])){
                    adjacencyMatrix[i][j] = 1 ;
                }
            }
        }

        return adjacencyMatrix;
    }

    public int[][] getIncidentMatrix(){
        int[][]incidentMatrix = new int[vertices.length][edges.length];

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (vertices[i].getName() == edges[j].getV1().getName()){
                    incidentMatrix[i][j] = 1;
                }else if (vertices[i].getName() == edges[j].getV2().getName()){
                    incidentMatrix[i][j] = -1;
                }

                //incidentMatrix[i][j] = vertices[i].getName() == edges[j].getV1().getName() ? 1 : 0;
            }
        }

        return incidentMatrix;
    }

    public int[] getIncidentVertices(int vertex){
        int[]incidentVertices = new int[this.getDegree(vertex)];
        int index = 0;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getV1().getName() == vertex){
                incidentVertices[index] = edges[i].getV2().getName();
                index++;
            }
        }

        return incidentVertices;
    }

    public int[][] getAdjacencyStructure(){
        int [][]adjacencyStructure = new int[vertices.length][];

        for (int i = 0; i < vertices.length; i++) {
            adjacencyStructure[i] = new int[this.getDegree(i+1)+1];
            int [] incidentVertices = getIncidentVertices(i+1);

            adjacencyStructure[i][0] = i+1;
            for (int j = 1; j < adjacencyStructure[i].length; j++) {
                adjacencyStructure[i][j] = incidentVertices[j-1];
            }
        }

        return adjacencyStructure;
    }

    public int getDegree(int vertex){
        int degree = 0;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getV1().getName() == vertex){
                degree++;
            }
        }

        return degree;
    }

    public int[] dfs(){
        int[]stack = new int[0];
        int[]wasVisited = new int[this.vertices.length];
        wasVisited[0] = 1;
        int currentVertex = 1;
        stack = Handler.addNewToStack(stack,currentVertex);

        while(Handler.findMin(wasVisited)==0){
            System.out.println(currentVertex);
            int[]canBeVisited = getIncidentVertices(currentVertex);
            if (canBeVisited.length!=0) {
                for (int i = 0; i < canBeVisited.length; i++) {
                    if (wasVisited[canBeVisited[i] - 1] != 1) {
                        currentVertex = canBeVisited[i];
                        wasVisited[currentVertex - 1] = 1;
                        stack = Handler.addNewToStack(stack,currentVertex);
                        break;
                    }
                }
            }else{
                currentVertex = Handler.goToLastVertex(stack,currentVertex);
                stack = Handler.addNewToStack(stack,currentVertex);
            }

            Handler.printArray(canBeVisited);
            Handler.printArray(wasVisited);
            Handler.printArray(stack);
        }

        return stack;
    }

    public int[] bfs(){
        int[]stack = new int[0];
        int[]wasVisited = new int[this.vertices.length];
        wasVisited[0] = 1;
        int currentVertex = 1;
        stack = Handler.addNewToStack(stack,currentVertex);

        while(Handler.findMin(wasVisited)==0){
            System.out.println(currentVertex);
            int[]canBeVisited = getIncidentVertices(currentVertex);
            if (canBeVisited.length!=0){
                for (int i = 0; i < canBeVisited.length; i++) {
                    for (int j = 0; j < wasVisited.length; j++) {
                        if (canBeVisited[i]-1 == j){
                            wasVisited[j] = 1;
                        }
                    }
                }

                currentVertex = canBeVisited[0];
                for (int i = 0; i < canBeVisited.length;i++) {
                    if (!Handler.isVertexInStack(stack,canBeVisited[i])) {
                        stack = Handler.addNewToStack(stack, canBeVisited[i]);
                    }
                }
            }else{
                currentVertex = Handler.goToLastVertex(stack,currentVertex);
            }
            Handler.printArray(canBeVisited);
            Handler.printArray(wasVisited);
            Handler.printArray(stack);
        }

        return stack;
    }
}

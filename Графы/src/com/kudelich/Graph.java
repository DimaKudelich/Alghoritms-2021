package com.kudelich;

public class Graph {
    private Vertex[] vertices;
    private Edge[] edges;

    public Graph(int [] verticesList,int [][] edgesList){
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

    public int getDegree(int vertex){
        int degree = 0;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getV1().getName() == vertex || edges[i].getV2().getName() == vertex){
                degree++;
            }
        }

        return degree;
    }

    public int[] getIncidentVertices(int vertex){
        int[]incidentVertices = new int[this.getDegree(vertex)];
        int index = 0;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getV1().getName() == vertex){
                incidentVertices[index] = edges[i].getV2().getName();
                index++;
            }else if (edges[i].getV2().getName() == vertex){
                incidentVertices[index] = edges[i].getV1().getName();
                index++;
            }
        }

        return incidentVertices;
    }

    public int[][] getAdjacencyStructure(){
        int [][]adjacencyStructure = new int[this.getVertices().length][];

        for (int i = 0; i < adjacencyStructure.length; i++) {
            adjacencyStructure[i] = new int[this.getDegree(i+1)+1];
            int [] incidentVertices = this.getIncidentVertices(i+1);

            adjacencyStructure[i][0] = i+1;
            for (int j = 1; j < adjacencyStructure[i].length; j++) {
                adjacencyStructure[i][j] = incidentVertices[j-1];
            }
        }

        return adjacencyStructure;
    }

    public boolean isVerticesAdjacency(Vertex v1, Vertex v2){
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getV1().getName() == v1.getName() && edges[i].getV2().getName() == v2.getName()){
                return true;
            }else if (edges[i].getV2().getName() == v1.getName() && edges[i].getV1().getName() == v2.getName()){
                return true;
            }
        }
        return false;
    }

    public int[][] getAdjacencyMatrix(){
        int[][] adjacencyMatrix = new int[vertices.length][vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                adjacencyMatrix[i][j] = this.isVerticesAdjacency(vertices[i], vertices[j]) ? 1 : 0;
            }
        }

        return adjacencyMatrix;
    }

    public int[][] getIncidentMatrix(){
        int[][]incidentMatrix = new int[vertices.length][edges.length];

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                incidentMatrix[i][j] = vertices[i].getName() == edges[j].getV1().getName()
                        || vertices[i].getName() == edges[j].getV2().getName() ? 1 : 0;
            }
        }

        return incidentMatrix;
    }

    public int[] greedyColoring() {
        int[] colors = new int[vertices.length];
        int[] degrees = new int[vertices.length];
        int[] banColors = new int[vertices.length];

        for (int i = 0; i < degrees.length; i++) {
            degrees[i] = this.getDegree(i + 1);
        }

        while (Handler.findMin(colors)==0) {
            int maxDegree = Handler.findIndexWithMax(degrees);

            colors[maxDegree] = banColors[maxDegree]+1;
            degrees[maxDegree] = -1;

            for (int i = 0; i < vertices.length; i++) {
                if (isVerticesAdjacency(vertices[maxDegree],vertices[i])){
                    degrees[i]--;
                    banColors[i] = colors[maxDegree];
                }
            }
        }

        return colors;
    }

    public int[] coloringWithAdjacencyMatrix() {
        int[][] matrix = getAdjacencyMatrix();
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 1;
        }

        int[] bannedRows = new int[vertices.length];
        int[] colors = new int[vertices.length];
        int color = 1;

        while (Handler.findMin(bannedRows) == 0) {
            int currentRow = Handler.findIndexWithMin(bannedRows);
            colors[currentRow] = color;

            while(Handler.findMin(matrix[currentRow])==0){
                int indexOfMin = Handler.findIndexWithMin(matrix[currentRow]);

                if (bannedRows[indexOfMin]!=1){
                    matrix[currentRow] = Handler.specialSumOfRows(matrix[currentRow], matrix[indexOfMin]);
                    bannedRows[indexOfMin] = 1;
                    colors[indexOfMin] = color;
                }else{
                    matrix[currentRow][indexOfMin]= 1;
                }
            }

            bannedRows[currentRow] = 1;
            color++;
        }

        return colors;
    }

    public Graph convertToGraphForEdgeColoring(){
        int[]vertices = new int[this.edges.length];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = i+1;
        }
        int[][]edges = new int[0][2];

        int index = 0;
        for (int i = index; i < this.edges.length; i++) {
            for (int j = index; j < this.edges.length; j++) {
                if (i!=j){
                    if (this.edges[i].getV1().getName() == this.edges[j].getV1().getName()
                            || this.edges[i].getV1().getName() == this.edges[j].getV2().getName()
                            ||this.edges[i].getV2().getName() == this.edges[j].getV1().getName()
                            ||this.edges[i].getV2().getName() == this.edges[j].getV2().getName()
                    ){
                        int[]newEdge = {vertices[i],vertices[j]};
                        edges = Handler.addEdge(edges,newEdge);
                    }
                }
            }
            index++;
        }

        return new Graph(vertices,edges);
    }
}

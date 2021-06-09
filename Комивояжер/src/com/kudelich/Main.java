package com.kudelich;

public class Main {
    public static void main(String[] args) {
        int inf = 100;

        int[][] matrix = {
                {-1, 12, 4, 5, 10, 8},
                {12, -1, 5, 4, 10, 2},
                {4, 5, -1, 10, 15, 3},
                {5, 4, 10, -1, 5, 6},
                {10, inf, inf, 5, -1, 8},
                {8, 2, 3, 6, 8, -1}
        };

        int[][]test = {
                {-1,10,2,3,5,7,},
                {10,-1,5,10,15,3},
                {4,5,-1,6,5,2},
                {3,10,6,-1,7,15},
                {inf,15,5,3,-1,2},
                {7,3,inf,15,2,-1}
        };

        int[][] test3 = {
                {-1,7,4,8,7},
                {8,-1,3,3,4},
                {8,1,-1,3,7},
                {4,7,2,-1,2},
                {9,2,3,5,-1},
        };

        Handler.printMatrix(matrix);
        Algorithm.algorithmOfLittle(matrix);
    }
}

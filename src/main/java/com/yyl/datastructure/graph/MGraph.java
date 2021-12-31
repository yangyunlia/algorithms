package com.yyl.datastructure.graph;


import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//邻接矩阵
public class MGraph {
    private int[][] AdjMatrix; //邻接矩阵
    private int vexNum; //点的数量
    private int arcNum; //边的数量
    private int kind; //图的类型， 0-有向图，1-有向网，2-无向图-3-无向网

    //初始化点数、边数、图的类型，并从inputStream读入边集
    MGraph(int vexNum, int arcNum, int kind, InputStream inputStream) {
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.kind = kind;
        //初始化邻接矩阵
        this.AdjMatrix = new int[vexNum][vexNum];
        for(int i = 0; i < vexNum; i++) {
            Arrays.fill(this.AdjMatrix[i], 0);
        }

        switch (kind) {
            case 0:
                createGraph(inputStream); //无向图
                break;
            case 1:
                createNetwork(inputStream); //无向网
                break;
            case 2:
                createDiGraph(inputStream); //有向图
                break;
            case 3:
                createDiNetWork(inputStream); //有向网
                break;
        }
    }

    //深度优先搜索并返回访问队列
    public List<Integer> DFSTraverse() {
        int[] visited = new int[vexNum];
        Arrays.fill(visited, 0);
        List<Integer> result = new LinkedList<>();
        for(int i = 0; i < arcNum; i++) {
            if(visited[i] == 0) {
                result.addAll(DFS(i, visited));
            }
        }
        return result;
    }

    public List<Integer> DFS(int i, int[] visited) {
        visited[i] = 1;
        List<Integer> result = new LinkedList<>();
        result.add(i);
        for(int j = 0; j < vexNum; j++) {
            if(this.AdjMatrix[i][j] != 0 && visited[j] == 0) {
                result.addAll(DFS(i, visited));
            }
        }
        return result;
    }

    private void createGraph(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        for(int i = 0; i < this.arcNum; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            this.AdjMatrix[a][b] = 1;
            this.AdjMatrix[b][a] = 1;
        }
    }

    private void createNetwork(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        for(int i = 0; i< this.arcNum; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            this.AdjMatrix[a][b] = w;
            this.AdjMatrix[b][a] = w;
        }
    }

    private void createDiGraph(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        for(int i = 0; i < this.arcNum; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            this.AdjMatrix[a][b] = 1;
        }
    }

    private void createDiNetWork(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        for (int i = 0; i < this.arcNum; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            this.AdjMatrix[a][b] = w;
        }
    }

}

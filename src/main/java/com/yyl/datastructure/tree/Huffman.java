package com.yyl.datastructure.tree;

import javax.management.NotificationEmitter;
import java.util.PriorityQueue;

public class Huffman {
    private Node root;
    private class Node implements Comparable{
        int w;
        Node left;
        Node right;
        Node(int e) {
            w = e;
            this.left = null;
            this.right = null;
        }
        Node(Node left, Node right) {
            this(left.w + right.w);
            this.left = left;
            this.right = right;
        }
        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;
            return w - other.w;
        }
    }
    Huffman(int[] data) {
        PriorityQueue<Node> elements = new PriorityQueue<>();
        for(int e: data) {
            elements.add(new Node(e));
        }
        for(int i = 0;i < data.length - 1;i++) {
            Node n = new Node(elements.poll(), elements.poll());
            elements.add(n);
        }
        this.root = elements.poll();
    }


}
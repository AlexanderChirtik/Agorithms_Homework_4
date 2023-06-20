package org.example;

import java.util.Scanner;

class Node {

    Node left, right;
    int data;

    // красный - true    черный - false
    boolean color;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
        color = true;
    }
}

public class LeftRigthTree {

    public static void main(String[] args) {

        LeftRigthTree node = new LeftRigthTree();
        Scanner scan = new Scanner(System.in);

        char ch;
        do {
            System.out.println("Введите число");

            int num = scan.nextInt();
            root = node.insert(root, num);

            node.inorder(root);
            System.out.println("\nПродолжить? (y - ДА или n - НЕТ)");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

    private static Node root = null;

    Node rotateLeft(Node myNode) {
        System.out.printf("Поворот влево!\n");
        Node child = myNode.right;
        Node childLeft = child.left;

        child.left = myNode;
        myNode.right = childLeft;

        return child;
    }

    Node rotateRight(Node myNode) {
        System.out.printf("Поворот вправо!\n");
        Node child = myNode.left;
        Node childRight = child.right;

        child.right = myNode;
        myNode.left = childRight;

        return child;
    }

    boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return (node.color == true);
    }

    void swapColors(Node node1, Node node2) {
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    Node insert(Node node, int data) {

        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
            swapColors(node, node.left);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
            swapColors(node, node.right);
        }

        if (isRed(node.left) && isRed(node.right)) {
            node.color = !node.color;
            node.left.color = false;
            node.right.color = false;
        }
        return node;
    }

    void inorder(Node node) {
        if (node != null)
        {
            inorder(node.left);
            char c = '●';
            if (node.color == false)
                c = '◯';
            System.out.print(node.data + ""+c+" ");
            inorder(node.right);
        }
    }
}
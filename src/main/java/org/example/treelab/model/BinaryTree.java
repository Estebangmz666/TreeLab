package org.example.treelab.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void addData(T data) {
        root = insert(root, data);
    }

    private TreeNode<T> insert(TreeNode<T> node, T data) {
        if (node == null) return new TreeNode<>(data);
        if (data.compareTo(node.data) < 0) node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0) node.right = insert(node.right, data);
        return node;
    }

    public boolean dataExists(T data) {
        return search(root, data);
    }

    private boolean search(TreeNode<T> node, T data) {
        if (node == null) return false;
        if (data.compareTo(node.data) == 0) return true;
        return data.compareTo(node.data) < 0 ? search(node.left, data) : search(node.right, data);
    }

    public List<T> inOrden() {
        List<T> result = new ArrayList<>();
        inOrdenRec(root, result);
        return result;
    }

    private void inOrdenRec(TreeNode<T> node, List<T> list) {
        if (node != null) {
            inOrdenRec(node.left, list);
            list.add(node.data);
            inOrdenRec(node.right, list);
        }
    }

    public List<T> preOrden() {
        List<T> result = new ArrayList<>();
        preOrdenRec(root, result);
        return result;
    }

    private void preOrdenRec(TreeNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.data);
            preOrdenRec(node.left, list);
            preOrdenRec(node.right, list);
        }
    }

    public List<T> postOrden() {
        List<T> result = new ArrayList<>();
        postOrdenRec(root, result);
        return result;
    }

    private void postOrdenRec(TreeNode<T> node, List<T> list) {
        if (node != null) {
            postOrdenRec(node.left, list);
            postOrdenRec(node.right, list);
            list.add(node.data);
        }
    }

    public int getHeight() {
        return height(root);
    }

    private int height(TreeNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int getWeight() {
        return weight(root);
    }

    private int weight(TreeNode<T> node) {
        if (node == null) return 0;
        return 1 + weight(node.left) + weight(node.right);
    }

    public int countLeaves() {
        return countLeavesRec(root);
    }

    private int countLeavesRec(TreeNode<T> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    public T getMinorNode() {
        TreeNode<T> current = root;
        while (current != null && current.left != null) current = current.left;
        return current != null ? current.data : null;
    }

    public T getMajorNode() {
        TreeNode<T> current = root;
        while (current != null && current.right != null) current = current.right;
        return current != null ? current.data : null;
    }

    public void deleteTree() {
        root = null;
    }

    public List<T> printAmplitude() {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            result.add(node.data);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }

    public void deleteData(T data) {
        root = delete(root, data);
    }

    private TreeNode<T> delete(TreeNode<T> node, T data) {
        if (node == null) return null;
        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode<T> min = findMin(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        return node;
    }

    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int getLevel(T data) {
        return getLevelRec(root, data, 1);
    }

    private int getLevelRec(TreeNode<T> node, T data, int level) {
        if (node == null) return -1;
        if (data.compareTo(node.data) == 0) return level;
        if (data.compareTo(node.data) < 0)
            return getLevelRec(node.left, data, level + 1);
        else
            return getLevelRec(node.right, data, level + 1);
    }
}
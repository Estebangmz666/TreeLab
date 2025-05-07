package org.example.treelab.controller;

import java.util.List;

import org.example.treelab.model.BinaryTree;
import org.example.treelab.model.TreeNode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TreeController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCountLeaves;

    @FXML
    private Button btnDataExists;

    @FXML
    private Button btnDeleteData;

    @FXML
    private Button btnDeleteTree;

    @FXML
    private Button btnGetAmplitude;

    @FXML
    private Button btnGetHeight;

    @FXML
    private Button btnGetLevel;

    @FXML
    private Button btnGetMajor;

    @FXML
    private Button btnGetMinor;

    @FXML
    private Button btnGetWeight;

    @FXML
    private Button btnInorden;

    @FXML
    private Button btnIsEmpty;

    @FXML
    private Button btnPosorden;

    @FXML
    private Button btnPreorden;

    @FXML
    private Label lbMessage;

    @FXML
    private Canvas treeCanvas;

    @FXML
    private TextField tfNumber;

    private final BinaryTree<Integer> tree = new BinaryTree<>();

    @FXML
    void initialize() {
        drawTree();
    }

    @FXML
    void btnAddClicked(ActionEvent event) {
        String input = tfNumber.getText().trim();
        try {
            int value = Integer.parseInt(input);
            tree.addData(value);
            lbMessage.setText("Dato agregado correctamente: " + value);
            tfNumber.clear();
            drawTree();
        } catch (NumberFormatException e) {
            lbMessage.setText("Por favor ingrese un número válido.");
        }
    }

    @FXML
    void btnCountLeavesClicked(ActionEvent event) {
        if (tree.isEmpty()) {
            lbMessage.setText("El árbol está vacío.");
        } else {
            int count = tree.countLeaves();
            lbMessage.setText("Número de hojas: " + count);
        }
        drawTree();
    }

    @FXML
    void btnDataExistsClicked(ActionEvent event) {
        String input = tfNumber.getText().trim();
        try {
            int value = Integer.parseInt(input);
            boolean exists = tree.dataExists(value);
            if (exists) {
                lbMessage.setText("El dato " + value + " existe en el árbol.");
                tfNumber.clear();
            } else {
                lbMessage.setText("El dato " + value + " no existe en el árbol.");
                tfNumber.clear();
            }
        } catch (NumberFormatException e) {
            lbMessage.setText("Por favor ingrese un número válido.");
        }
    }

    @FXML
    void btnDeleteDataClicked(ActionEvent event) {
        String input = tfNumber.getText().trim();
        try {
            int value = Integer.parseInt(input);
            if (tree.dataExists(value)) {
                tree.deleteData(value);
                lbMessage.setText("El dato " + value + " ha sido eliminado del árbol.");
                drawTree();
            } else {
                lbMessage.setText("El dato " + value + " no existe en el árbol.");
            }
        } catch (NumberFormatException e) {
            lbMessage.setText("Por favor ingrese un número válido.");
        }
        tfNumber.clear();
    }

    @FXML
    void btnDeleteTreeClicked(ActionEvent event) {
        tree.deleteTree();
        lbMessage.setText("El árbol ha sido eliminado.");
        tfNumber.clear();
        drawTree();
    }

    @FXML
    void btnGetAmplitudeClicked(ActionEvent event) {
        List<Integer> amplitude = tree.printAmplitude();
        if (amplitude.isEmpty()) {
            lbMessage.setText("El árbol está vacío.");
        } else {
            lbMessage.setText("Amplitud del árbol: " + amplitude.toString());
        }
    }

    @FXML
    void btnGetHeightClicked(ActionEvent event) {
        int height = tree.getHeight();
        lbMessage.setText("La altura del árbol es: " + height);
    }

    @FXML
    void btnGetLevelClicked(ActionEvent event) {
        String input = tfNumber.getText().trim();
        try {
            int value = Integer.parseInt(input);
            int level = tree.getLevel(value);
            if (level == -1) {
                lbMessage.setText("El dato " + value + " no se encuentra en el árbol.");
            } else {
                lbMessage.setText("El dato " + value + " está en el nivel " + level + " del árbol.");
            }
        } catch (NumberFormatException e) {
            lbMessage.setText("Por favor ingrese un número válido.");
        }
    }

    @FXML
    void btnGetMajorClicked(ActionEvent event) {
        Integer major = tree.getMajorNode();
        if (major != null) {
            lbMessage.setText("El mayor nodo del árbol es: " + major.toString());
        } else {
            lbMessage.setText("El árbol está vacío.");
        }
    }

    @FXML
    void btnGetMinorClicked(ActionEvent event) {
        Integer minor = tree.getMinorNode();
        if (minor != null) {
            lbMessage.setText("El menor nodo del árbol es: " + minor.toString());
        } else {
            lbMessage.setText("El árbol está vacío.");
        }
    }

    @FXML
    void btnGetWeightClicked(ActionEvent event) {
        int weight = tree.getWeight();
        lbMessage.setText("El peso del árbol es: " + weight);
    }

    @FXML
    void btnInordenClicked(ActionEvent event) {
        List<Integer> inOrderList = tree.inOrden();
        if (inOrderList.isEmpty()) {
            lbMessage.setText("El árbol está vacío.");
        } else {
            lbMessage.setText("Recorrido en Inorden: " + inOrderList.toString());
        }
        drawTree();
    }

    @FXML
    void btnIsEmptyClicked(ActionEvent event) {
        if (tree.isEmpty()) {
            lbMessage.setText("El árbol está vacío.");
        } else {
            lbMessage.setText("El árbol no está vacío.");
        }
    }

    @FXML
    void btnPosordenClicked(ActionEvent event) {
        List<Integer> postOrderList = tree.postOrden();
        if (postOrderList.isEmpty()) {
            lbMessage.setText("El árbol está vacío.");
        } else {
            lbMessage.setText("Recorrido en Postorden: " + postOrderList.toString());
        }
        drawTree();
    }

    @FXML
    void btnPreordenClicked(ActionEvent event) {
        List<Integer> preOrderList = tree.preOrden();
        if (preOrderList.isEmpty()) {
            lbMessage.setText("El árbol está vacío.");
        } else {
            lbMessage.setText("Recorrido en Preorden: " + preOrderList.toString());
        }
        drawTree();
    }

    private void drawTree() {
        GraphicsContext gc = treeCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, treeCanvas.getWidth(), treeCanvas.getHeight());
        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.BLACK);
        TreeNode<Integer> root = tree.getRoot();
        if (root != null) {
            drawNode(gc, root, 350, 50, 100);
        }
    }

    private void drawNode(GraphicsContext gc, TreeNode<Integer> node, double x, double y, double deltaX) {
        if (node == null) return;
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.strokeText(String.valueOf(node.getData()), x - 7, y + 5);
        if (node.getLeft() != null) {
            gc.strokeLine(x, y, x - deltaX, y + 60);
            drawNode(gc, node.getLeft(), x - deltaX, y + 60, deltaX / 1.5);
        }
        if (node.getRight() != null) {
            gc.strokeLine(x, y, x + deltaX, y + 60);
            drawNode(gc, node.getRight(), x + deltaX, y + 60, deltaX / 1.5);
        }
    }  
}
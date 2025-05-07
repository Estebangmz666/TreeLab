module org.example.treelab {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.treelab to javafx.fxml;
    opens org.example.treelab.controller to javafx.fxml;
    opens org.example.treelab.model to javafx.fxml;
    exports org.example.treelab;
}
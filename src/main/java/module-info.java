module org.example.treelab {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.treelab to javafx.fxml;
    exports org.example.treelab;
}
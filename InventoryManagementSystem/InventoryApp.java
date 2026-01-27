package InventoryManagementSystem;

import javafx.application.Application;
import javafx.collections.transformation.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class InventoryApp extends Application {

    private final InventoryManager manager = InventoryManager.getInstance();

    @Override
    public void start(Stage stage) {

        TableView<Product> table = new TableView<>();
        TextField barcodeField = new TextField();
        TextField nameField = new TextField();
        TextField qtyField = new TextField();
        TextField priceField = new TextField();
        TextField searchField = new TextField();

        barcodeField.setPromptText("Barcode");
        nameField.setPromptText("Product Name");
        qtyField.setPromptText("Quantity");
        priceField.setPromptText("Price");
        searchField.setPromptText("Search by barcode or name");

        TableColumn<Product, String> barcodeCol = new TableColumn<>("Barcode");
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(barcodeCol, nameCol, qtyCol, priceCol);

        
        table.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else if (item.getQuantity() < 5) {
                    setStyle("-fx-background-color: #ffcccc;");
                } else {
                    setStyle("");
                }
            }
        });

        FilteredList<Product> filtered =
                new FilteredList<>(manager.getProducts(), p -> true);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filtered.setPredicate(p ->
                    p.getName().toLowerCase().contains(newVal.toLowerCase()) ||
                            p.getBarcode().contains(newVal)
            );
        });

        table.setItems(filtered);

        Button addBtn = new Button("Add");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");

        addBtn.setOnAction(e -> {
            int qty = Integer.parseInt(qtyField.getText());
            if (qty < 0) return;

            manager.addProduct(new Product(
                    barcodeField.getText(),
                    nameField.getText(),
                    qty,
                    Double.parseDouble(priceField.getText())
            ));
            clearFields(barcodeField, nameField, qtyField, priceField);
        });

        updateBtn.setOnAction(e -> {
            Product p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                int qty = Integer.parseInt(qtyField.getText());
                if (qty < 0) return;

                p.setQuantity(qty);
                p.setPrice(Double.parseDouble(priceField.getText()));
                table.refresh();
            }
        });

        deleteBtn.setOnAction(e -> {
            Product p = table.getSelectionModel().getSelectedItem();
            if (p != null) manager.deleteProduct(p);
        });

        HBox form = new HBox(10, barcodeField, nameField, qtyField, priceField, addBtn, updateBtn, deleteBtn);
        VBox root = new VBox(10, searchField, table, form);
        root.setPadding(new Insets(15));

        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root, 900, 500));
        stage.show();
    }

    private void clearFields(TextField... fields) {
        for (TextField f : fields) f.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

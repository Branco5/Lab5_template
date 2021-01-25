package com.pa.refactoring.view;

import com.pa.refactoring.model.Product;
import com.pa.refactoring.model.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class GuiShoppingCart extends BorderPane{
    private Button buttonAddProduct;
    private TextField textFieldPrice;
    private TextField textFieldProductName;
    private Label labelCost;
    private final ShoppingCart shoppingCart;
    private ListView<Product> listViewCartContents;
    private Button buttonTerminate;
    private Label labelEnd;

    public GuiShoppingCart(ShoppingCart sc){
        this.shoppingCart = sc;
        this.listViewCartContents = new ListView<>();
        initAddProduct();
        initShoppingCart();
        setTriggers();
    }

    public void initAddProduct(){
        GridPane gridPaneAddProduct = new GridPane();
        Label labelAddProduct = new Label("Add products to cart");
        labelAddProduct.setStyle("-fx-font-weight: bold");
        textFieldProductName = new TextField();
        textFieldPrice = new TextField();
        labelCost = new Label("Total cost: 0.0 €");
        HBox hBoxAddProductButtons = new HBox(6);
        hBoxAddProductButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxAddProductButtons.setStyle("-fx-padding: 2px 0 0 0");

        gridPaneAddProduct.addColumn(0, labelAddProduct, new Label("Name"),
                new Label("Price"), labelCost);
        gridPaneAddProduct.addColumn(1, new Label(""),
                textFieldProductName, textFieldPrice, hBoxAddProductButtons);

        buttonAddProduct = new Button("Add");
        buttonTerminate = new Button("End");
        labelEnd = new Label();
        this.setBottom(labelEnd);
        this.setTop(gridPaneAddProduct);
        hBoxAddProductButtons.getChildren().addAll(buttonAddProduct,buttonTerminate);
    }

    public void initShoppingCart(){
        GridPane gridPaneCartContents = new GridPane();
        Label labelCartContents = new Label("Cart contents");
        labelCartContents.setStyle("-fx-font-weight: bold");
        listViewCartContents = new ListView<>();
        gridPaneCartContents.addColumn(0, labelCartContents, listViewCartContents);
        GridPane.setHgrow(listViewCartContents, Priority.ALWAYS);
        this.setCenter(gridPaneCartContents);
        listViewCartContents.getItems().clear();
        addProductsToListView();
        this.setStyle("-fx-padding: 5px");
    }

    public void setTriggers(){
        buttonAddProduct.setOnAction((ActionEvent e) -> {
            if (textFieldProductName.getText().isEmpty() || textFieldPrice.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Shopping Cart Error");
                alert.setHeaderText(null);
                alert.setContentText("empty fields");
                alert.showAndWait();

            } else {
                try {
                    String name = textFieldProductName.getText();
                    double price = Double.parseDouble(textFieldPrice.getText());
                    shoppingCart.add(new Product(name, price));
                    labelCost.setText(String.format("Total Cost %.1f €", shoppingCart.getTotal()));
                    listViewCartContents.getItems().clear();
                    addProductsToListView();
                    textFieldPrice.clear();
                    textFieldProductName.clear();
                } catch (NumberFormatException nfe) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Shopping Cart Error");
                    alert.setHeaderText(null);
                    alert.setContentText("invalid format");
                    alert.showAndWait();
                }
            }
        });
        buttonTerminate.setOnAction((ActionEvent e) -> {

            shoppingCart.terminate();

            String strEnd;
            if (shoppingCart.isTerminated()) {
                strEnd = String.format("%s Total Cost %.2f Number of Items %d", shoppingCart.getDate(),
                        shoppingCart.getTotal(), shoppingCart.size());
            } else {
                strEnd = "";
            }
            labelCost.setText(String.format("Current Cost %.1f €", shoppingCart.getTotal()));
            buttonAddProduct.setDisable(true);

            labelEnd.setText(strEnd);
        });
    }

    public void addProductsToListView() {
        for (Product product : shoppingCart.getProducts()) {
            listViewCartContents.getItems().add(product);
        }
    }
}

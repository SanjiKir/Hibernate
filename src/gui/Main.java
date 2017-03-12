/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;

/**
 *
 * @author wooow
 */
public class Main extends Application {

    private BorderPane borderPane;
    private MenuBar menuBar;
    private VBox vbox;

    @Override
    public void start(Stage primaryStage) {
        menuBar = new MenuBar();
        vbox = new VBox();
        borderPane = new BorderPane();
        
        Menu menu = new Menu("Add data");
        MenuItem menuItem = new MenuItem("Add owner");
        MenuItem menuItem1 = new MenuItem("Add auto");
        MenuItem menuItem2 = new MenuItem("Add insurance");
        menu.getItems().addAll(menuItem, menuItem1, menuItem2);

        Menu menu2 = new Menu("Update data");
        MenuItem menuItemUpdate1 = new MenuItem("Update owner");
        MenuItem menuItemUpdate2 = new MenuItem("Update car");
        MenuItem menuItemUpdate3 = new MenuItem("Updare insurance");
        menu2.getItems().addAll(menuItemUpdate1, menuItemUpdate2, menuItemUpdate3);

        Menu menu3 = new Menu("View data");
        MenuItem menuItemView1 = new MenuItem("View owners");
        MenuItem menuItemView2 = new MenuItem("View cars");
        MenuItem menuItemView3 = new MenuItem("View insurance");
        menu3.getItems().addAll(menuItemView1, menuItemView2, menuItemView3);

     

         ImageView img = new ImageView(new Image("/zdroje/08.jpg"));
         img.fitWidthProperty().bind(borderPane.widthProperty());
         menuBar.getMenus().addAll(menu, menu2, menu3);
         borderPane.setCenter(img);
          vbox.getChildren().addAll(menuBar, borderPane);
       /*
          menuItem.setOnAction(e->{
            FirmaTableAdmin firmaTable = new FirmaTableAdmin();
            Stage stage1 = new Stage();
            Scene scene1 = new Scene(firmaTable.getPane(), 1200, 500);
            stage1.setScene(scene1);
            stage1.setTitle("Tabulka firem");
            stage1.show();
        });
          */
       
        menuItem.setOnAction(e->{
            AddOwner addOwner = new AddOwner();
            Stage stage1 = addOwner.getStage();
            stage1.show();
              });
        
        menuItem1.setOnAction(e->{
            AutoAdd autoAdd = new AutoAdd();
            Stage stage2 = autoAdd.getStage();
            stage2.show();
              });
        
         menuItem2.setOnAction(e->{
            PojisteniAdd pojisteniAdd = new PojisteniAdd();
            Stage stage3 = pojisteniAdd.getStage();
            stage3.show();
              });
         
          menuItemView1.setOnAction(e->{
            OwnerTable ownerTable = new OwnerTable();
            VBox vBox = ownerTable.getPane();
            Stage stage = new Stage();
            Scene scene = new Scene(vBox, 800, 600);
            stage.setScene(scene);
            stage.show();
              });
          
            menuItemView2.setOnAction(e->{
            AutoTable autoTable = new AutoTable();
            VBox vBox = autoTable.getPane();
            Stage stage = new Stage();
            Scene scene = new Scene(vBox, 800, 600);
            stage.setScene(scene);
            stage.show();
              });
            
            menuItemView3.setOnAction(e->{
            InsuranceTable insuranceTable = new InsuranceTable();
            VBox vBox = insuranceTable.getPane();
            Stage stage = new Stage();
            Scene scene = new Scene(vBox, 800, 600);
            stage.setScene(scene);
            stage.show();
              });
            
             menuItemUpdate1.setOnAction(e->{
            UpdateOwner updateOwner = new UpdateOwner();
            Stage stage = updateOwner.getStage();
            stage.show();
              });
            
            menuItemUpdate2.setOnAction(e->{
            UpdateCar updateCar = new UpdateCar();
            Stage stage = updateCar.getStage();
            stage.show();
              });
            
             menuItemUpdate3.setOnAction(e->{
            UpdateInsurance updateInsurance = new UpdateInsurance();
            Stage stage = updateInsurance.getStage();
            stage.show();
              });
            
        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setTitle("Car insurance");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

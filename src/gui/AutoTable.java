/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import hibernate.Majitel;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import hibernate.*;
/**
 *
 * @author shak01
 */
public class AutoTable {

    TableView<Auto> table;
  //  TextField id, nameInput, lastNameInput, emailInput, phoneNumberInput, autoIDInput;
    private FlowPane flowPane;
    private VBox vbox;
    
    public AutoTable() {
        flowPane = new FlowPane();
        init();
    }

    private void init() {
        //idColumn
        TableColumn<Auto, String> idColumn = new TableColumn<>("CarId");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("autoId"));
        
        
        //Name column
        TableColumn<Auto, String> nameColumn = new TableColumn<>("CarName");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nazev"));

        //LastName column
        TableColumn<Auto, String> ownerColumn = new TableColumn<>("Owner");
        ownerColumn.setMinWidth(100);
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("majitel"));

        

       
        table = new TableView<>();
        table.setItems(getAuto());
        table.getColumns().addAll(idColumn, nameColumn, ownerColumn );

        vbox = new VBox();
        vbox.getChildren().addAll(table);

       
    }

    public ObservableList<Auto> getAuto() {
         SessionFactory sessionFactory = 
		new Configuration().configure()
		.buildSessionFactory();
        Session session = sessionFactory.openSession();
        
        Transaction tx = session.beginTransaction();
        ObservableList<Auto> auta = FXCollections.observableArrayList();
         List <Auto> seznam = session.createQuery("from Auto").list();
        for (Auto auto : seznam) {
            auta.add(auto);
        }

        return auta;

    }
    
    public VBox getPane(){
        return vbox;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import hibernate.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author shak01
 */
public class OwnerTable {

    TableView<Majitel> table;
  //  TextField id, nameInput, lastNameInput, emailInput, phoneNumberInput, autoIDInput;
    private FlowPane flowPane;
    private VBox vbox;
    
    public OwnerTable() {
        flowPane = new FlowPane();
        init();
    }

    private void init() {
        //idColumn
        TableColumn<Majitel, String> idColumn = new TableColumn<>("OwnerID");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idMajitel"));
        
        
        //Name column
        TableColumn<Majitel, String> nameColumn = new TableColumn<>("FisrtName");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //LastName column
        TableColumn<Majitel, String> lastNameColumn = new TableColumn<>("LastName");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //eMail column
        TableColumn<Majitel, String> eMailColumn = new TableColumn<>("eMail");
        eMailColumn.setMinWidth(100);
        eMailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        //phoneNumber column
        TableColumn<Majitel, String> phoneNumberColumn = new TableColumn<>("PhoneNumber");
        phoneNumberColumn.setMinWidth(100);
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
/*
        //autoId column
        TableColumn<Majitel, String> autoIdColumn = new TableColumn<>("AutoId");
        autoIdColumn.setMinWidth(200);
        autoIdColumn.setCellValueFactory(new PropertyValueFactory<>("autoId"));
*/
       
        table = new TableView<>();
        table.setItems(getOwner());
        table.getColumns().addAll(idColumn, nameColumn, lastNameColumn, eMailColumn, phoneNumberColumn);

        vbox = new VBox();
        vbox.getChildren().addAll(table);

       
    }

    public ObservableList<Majitel> getOwner() {
         SessionFactory sessionFactory = 
		new Configuration().configure()
		.buildSessionFactory();
        Session session = sessionFactory.openSession();
        
        Transaction tx = session.beginTransaction();
        ObservableList<Majitel> majitely = FXCollections.observableArrayList();
         List <Majitel> seznam = session.createQuery("from Majitel").list();
        for (Majitel majitel : seznam) {
            majitely.add(majitel);
        }

        return majitely;

    }
    
    public VBox getPane(){
        return vbox;
    }
}

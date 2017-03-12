/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import hibernate.Auto;
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
public class InsuranceTable {

    TableView<Pojisteni> table;
  //  TextField id, nameInput, lastNameInput, emailInput, phoneNumberInput, autoIDInput;
    private FlowPane flowPane;
    private VBox vbox;
    
    public InsuranceTable() {
        flowPane = new FlowPane();
        init();
    }

    private void init() {
        //idColumn
        TableColumn<Pojisteni, String> idColumn = new TableColumn<>("InsuranceCompanyId");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("pojisteniId"));
        
        
        //Name column
        TableColumn<Pojisteni, String> nameColumn = new TableColumn<>("NameOfInsuranceCompany");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nazevPojisteni"));

        //LastName column
        TableColumn<Pojisteni, String> ownerColumn = new TableColumn<>("TypeOfInsurance");
        ownerColumn.setMinWidth(100);
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("typPojisteni"));

        

       
        table = new TableView<>();
        table.setItems(getPojisteni());
        table.getColumns().addAll(idColumn, nameColumn, ownerColumn );

        vbox = new VBox();
        vbox.getChildren().addAll(table);

       
    }

    public ObservableList<Pojisteni> getPojisteni() {
         SessionFactory sessionFactory = 
		new Configuration().configure()
		.buildSessionFactory();
        Session session = sessionFactory.openSession();
        
        Transaction tx = session.beginTransaction();
        ObservableList<Pojisteni> pojisteni = FXCollections.observableArrayList();
         List <Pojisteni> seznam = session.createQuery("from Pojisteni").list();
        for (Pojisteni pojist : seznam) {
            pojisteni.add(pojist);
        }

        return pojisteni;

    }
    
    public VBox getPane(){
        return vbox;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import hibernate.Auto;
import hibernate.Majitel;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author wooow
 */
public class UpdateOwner {

    private Stage primaryStage;
    private TextField fName, lName, email, mobileNo;
    private ComboBox<String> ownerBox;
    private Button savebtn, deletebtn;

    public UpdateOwner() {
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Java klient server");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        fName = new TextField();
        fName.setTooltip(new Tooltip("Enter First Name"));
        fName.setFont(Font.font("SanSerif", 15));
        fName.setPromptText("First Name");
        fName.setMaxWidth(200);

        lName = new TextField();
        lName.setTooltip(new Tooltip("Enter Last Name"));
        lName.setFont(Font.font("SanSerif", 15));
        lName.setPromptText("Last Name");
        lName.setMaxWidth(200);

        email = new TextField();
        email.setTooltip(new Tooltip("Enter Email"));
        email.setFont(Font.font("SanSerif", 15));
        email.setPromptText("Email");
        email.setMaxWidth(200);

        mobileNo = new TextField();
        mobileNo.setTooltip(new Tooltip("Enter Mobile Number"));
        mobileNo.setFont(Font.font("SanSerif", 15));
        mobileNo.setPromptText("Mobile No");
        mobileNo.setMaxWidth(200);

        ownerBox = new ComboBox<>();

        SessionFactory sessionFactory
                = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        ObservableList<Majitel> majitely = FXCollections.observableArrayList();
        List<Majitel> seznamMajitelu = session.createQuery("from Majitel").list();
        for (Majitel majitel : seznamMajitelu) {
            majitely.add(majitel);
            ownerBox.getItems().add(majitel.getId() + " " + majitel.getFirstName() + " " + majitel.getLastName());
        }

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save the owner Details"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            for (Majitel majitel : majitely) {
                if ((majitel.getId() + " " + majitel.getFirstName() + " " + majitel.getLastName()).equals(ownerBox.getValue())) {
                    if (!fName.getText().equals("")) {
                        majitel.setFirstName(fName.getText());
                    }
                    if (!lName.getText().equals("")) {
                        majitel.setLastName(lName.getText());
                    }
                    if (!email.getText().equals("")) {
                        majitel.setEmail(email.getText());
                    }
                    if (!mobileNo.getText().equals("")) {
                        majitel.setMobileNumber(mobileNo.getText());
                    }

                    session.save(majitel);
                    session.getTransaction().commit();
                    session.close();
                }
            }

        });
        
        
        deletebtn = new Button("Delete");
        deletebtn.setTooltip(new Tooltip("Dekete the Owner Details"));
        deletebtn.setFont(Font.font("SanSerif", 15));
        deletebtn.setOnAction(e -> {
            for (Majitel majitel : majitely) {
                if ((majitel.getId() + " " + majitel.getFirstName() + " " + majitel.getLastName()).equals(ownerBox.getValue())) {
                   
                     Session session1 = sessionFactory.openSession();
                    Transaction tx1 = session1.beginTransaction();

                    
                    session1.delete(majitel);
                    session1.getTransaction().commit();
                    session1.close();
                }
            }

        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(ownerBox, fName, lName, email, mobileNo, savebtn, deletebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import hibernate.Majitel;
import hibernate.Pojisteni;
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
public class UpdateInsurance {
    
    private Stage primaryStage;
    private TextField name, type;
    private ComboBox<String> insuranceBox;
    private Button savebtn, deleteButton;

    public UpdateInsurance() {
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Java klient server");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        name = new TextField();
        name.setTooltip(new Tooltip("Enter name of  Insurance Company"));
        name.setFont(Font.font("SanSerif", 15));
        name.setPromptText("Insurance name");
        name.setMaxWidth(200);

        type = new TextField();
        type.setTooltip(new Tooltip("Enter auto type of Insurance"));
        type.setFont(Font.font("SanSerif", 15));
        type.setPromptText("Insutrance type");
        type.setMaxWidth(200);

        insuranceBox = new ComboBox<>();

        SessionFactory sessionFactory
                = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        ObservableList<Pojisteni> pojisteni = FXCollections.observableArrayList();
        List<Pojisteni> seznamPojistnei = session.createQuery("from Pojisteni").list();
        for (Pojisteni pojist : seznamPojistnei) {
            pojisteni.add(pojist);
            insuranceBox.getItems().add(pojist.getPojisteniId()+ " " + pojist.getNazevPojisteni());
        }

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save the Auto Details"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            for (Pojisteni pojist : pojisteni) {
                if ((pojist.getPojisteniId() + " " + pojist.getNazevPojisteni()).equals(insuranceBox.getValue())) {
                    if (!name.getText().equals("")) {
                        pojist.setNazevPojisteni(name.getText());
                    }
                    if (!type.getText().equals("")) {
                        pojist.setTypPojisteni(type.getText());
                    }
                    

                    session.save(pojist);
                    session.getTransaction().commit();
                    session.close();
                }
            }

        });

        deleteButton = new Button("Delete");
        deleteButton.setTooltip(new Tooltip("Delete the Insurance Details"));
        deleteButton.setFont(Font.font("SanSerif", 15));
        deleteButton.setOnAction(e -> {
            for (Pojisteni pojist : pojisteni) {
                if ((pojist.getPojisteniId() + " " + pojist.getNazevPojisteni()).equals(insuranceBox.getValue())) {
                   
                    

                    session.delete(pojist);
                    session.getTransaction().commit();
                    session.close();
                }
            }

        });
        
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(insuranceBox, name, type, savebtn, deleteButton);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import hibernate.Auto;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernate.*;
import static javafx.application.Application.launch;

/**
 *
 * @author shak01
 */
public class PojisteniAdd {

    private Stage primaryStage;
    private TextField name;
    private TextField type;
    private Button savebtn;

    public PojisteniAdd(){
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

        //Hibernate Configuration
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();

        Pojisteni pojisteni = new Pojisteni();

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save Insurance Details"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            pojisteni.setNazevPojisteni(name.getText());
            pojisteni.setTypPojisteni(type.getText());

            Session session = sf.openSession();
            session.beginTransaction();
            session.save(pojisteni);
            session.getTransaction().commit();
            session.close();

            clearFields();

        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(name, type, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields() {
        // TODO Auto-generated method stub
        name.clear();
        type.clear();
    }

   public Stage getStage(){
       return primaryStage;
   }
}

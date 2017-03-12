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
 * @author shak01
 */
public class UpdateCar {

    private Stage primaryStage;
    private ComboBox<String> autoBox;
    private ComboBox<String> ownerBox;
    private Button savebtn;
    

    public UpdateCar() {
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Java klient server");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        autoBox = new ComboBox<>();
        ownerBox = new ComboBox<>();

        SessionFactory sessionFactory
                = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        ObservableList<Auto> auta = FXCollections.observableArrayList();
        List<Auto> seznamAut = session.createQuery("from Auto").list();
        for (Auto auto : seznamAut) {
            auta.add(auto);
            autoBox.getItems().add(auto.getAutoId() + " " + auto.getNazev());
        }

        ObservableList<Majitel> majitely = FXCollections.observableArrayList();
        List<Majitel> seznamMajitelu = session.createQuery("from Majitel").list();
        for (Majitel majitel : seznamMajitelu) {
            majitely.add(majitel);
            ownerBox.getItems().add(majitel.getId() + " " + majitel.getFirstName() + " " + majitel.getLastName());
        }

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save the Auto Details"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            for (Auto auto : auta) {
                if ((auto.getAutoId() + " " + auto.getNazev()).equals(autoBox.getValue())) {
                    for (Majitel majitel : majitely) {
                        if ((majitel.getId() + " " + majitel.getFirstName() + " " + majitel.getLastName()).equals(ownerBox.getValue())) {
                            auto.setMajitel(majitel);
                            majitel.pridejAuto(auto);
                            session.save(auto);
                            session.save(majitel);
                            session.getTransaction().commit();
                            session.close();
                        }
                    }
                }
            }

          

        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(autoBox, ownerBox, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    

    public Stage getStage() {
        return primaryStage;
    }
}

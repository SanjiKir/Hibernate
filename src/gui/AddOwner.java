package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
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

/**
 *
 * @author shak01
 */
public class AddOwner {

    private Stage primaryStage;
    private TextField fName, lName, email, mobileNo;
    private Button savebtn;

    public AddOwner() {
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

        //Hibernate Configuration
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();

        Majitel majitel = new Majitel();

        savebtn = new Button("Save");
        savebtn.setTooltip(new Tooltip("Save the User Details"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            majitel.setFirstName(fName.getText());
            majitel.setLastName(lName.getText());
            majitel.setEmail(email.getText());
            majitel.setMobileNumber(mobileNo.getText());

            Session session = sf.openSession();
            session.beginTransaction();
            session.save(majitel);
            session.getTransaction().commit();
            session.close();

            clearFields();

        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(fName, lName, email, mobileNo, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields() {
        // TODO Auto-generated method stub
        fName.clear();
        lName.clear();
        email.clear();
        mobileNo.clear();
    }

    public Stage getStage() {
        return primaryStage;
    }

}

package com.example.cookieclicker;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class main extends Application {
    int clicsTotals = 0;
    int monnaie = 0;
    double frequence = 10;
    Timeline horloge = new Timeline();
    int valeurParClic = 1;
    double coutPourAmelioration = 30;
    int coutPourAmeliorer = 15;
    Button bouton;
    Label nbClics;
    Label portefeuille;
    Button valeurClic;
    Button clickerAutomatique;
    Button coutParAmelioration;
    Button debloquerImage;
    Button debloquerAmelioration;
    Group root = new Group();
    Label coutAmelioration;
    @Override
    public void start(Stage stage)  {
        bouton = new Button("Clique moi!");
        nbClics = new Label("Nombre de clics : " + clicsTotals);
        portefeuille = new Label("Argent : " + monnaie);
        root.getChildren().addAll(bouton, nbClics, portefeuille);
        coutAmelioration = new Label("Coût pour améliorer : " + coutPourAmeliorer + " (" + (int) coutPourAmelioration  + " pour l'amélioration qui diminue les coûts)");
        root.getChildren().add(coutAmelioration);
        coutAmelioration.setTranslateY(250);
        coutAmelioration.setTranslateX(15);

        bouton.setTranslateX(142);
        bouton.setTranslateY(200);
        portefeuille.setTranslateY(30);
        portefeuille.setTranslateX(150);
        nbClics.setTranslateX(130);

        //upgrades
        
        //valeur par clic
        valeurClic = new Button("Augmentez argent par clic pour");
        root.getChildren().add(valeurClic);

        valeurClic.setOnAction( (event) -> {
            if (monnaie >= coutPourAmeliorer) {
                monnaie -= coutPourAmeliorer;
                coutPourAmeliorer *= 2;
                portefeuille.setText("Argent : " + monnaie);
                valeurParClic *= 2;
                coutAmelioration.setText("Coût pour améliorer : " + coutPourAmeliorer + " (" + (int) coutPourAmelioration + " pour l'amélioration qui diminue les coûts)");
            }
        });
        valeurClic.setTranslateY(100);
        valeurClic.setTranslateX(90);
        
        //clic automatique
        clickerAutomatique = new Button("Clickeur automatique");
        root.getChildren().add(clickerAutomatique);

        horloge = new Timeline(new KeyFrame(Duration.seconds(frequence), (e) -> {
            clicsTotals++;
            monnaie += valeurParClic;
            nbClics.setText("Nombre de clis : " + clicsTotals);
            portefeuille.setText("Argent : " + monnaie);
        }));
        horloge.setCycleCount(Animation.INDEFINITE);

        clickerAutomatique.setOnAction( (event) ->  {
            if (monnaie >= coutPourAmeliorer) {
                monnaie -= coutPourAmeliorer;
                coutPourAmeliorer *= 2;
                portefeuille.setText("Argent : " + monnaie);
                frequence = frequence / 2;
                horloge.getKeyFrames().set(0, new KeyFrame(Duration.seconds(frequence), (e) -> {
                    clicsTotals++;
                    monnaie += valeurParClic;
                    nbClics.setText("Nombre de clis : " + clicsTotals);
                    portefeuille.setText("Argent : " + monnaie);
                }));
                horloge.stop();
                horloge.play();
                coutAmelioration.setText("Coût pour améliorer : " + coutPourAmeliorer + " (" + (int) coutPourAmelioration + " pour l'amélioration qui diminue les coûts)");
            }
        });
        clickerAutomatique.setTranslateY(50);
        clickerAutomatique.setTranslateX(115);


        coutParAmelioration = new Button("Diminuez coûts des améliorations");
        root.getChildren().add(coutParAmelioration);

        coutParAmelioration.setOnAction( (event) -> {
            if (monnaie >= coutPourAmelioration) {
                monnaie -= coutPourAmelioration;
                portefeuille.setText("Argent : " + monnaie);
                coutPourAmeliorer *= 0.80;
                coutPourAmelioration *= 2;
                coutAmelioration.setText("Coût pour améliorer : " + coutPourAmeliorer + " (" + (int) coutPourAmelioration + " pour l'amélioration qui diminue les coûts)");
            }

        });
        coutParAmelioration.setTranslateY(75);
        coutParAmelioration.setTranslateX(85);

        //clicker le cookie
        bouton.setOnAction( (event) -> {
            clicsTotals++;
            monnaie += valeurParClic;
            nbClics.setText("Nombre de clis : " + clicsTotals);
            portefeuille.setText("Argent : " + monnaie);
        } );

        //Debloquer amélioration
        debloquerImage = new Button("Debloquer image");
        ImageView view = new ImageView(new Image("https://cdn-icons-png.flaticon.com/128/6247/6247166.png"));
        view.setFitHeight(50);
        view.setPreserveRatio(true);

        debloquerAmelioration = new Button("Debloquer amélioration secrete");
        root.getChildren().add(debloquerAmelioration);
        debloquerAmelioration.setTranslateY(125);
        debloquerAmelioration.setTranslateX(90);

        debloquerAmelioration.setOnAction( (e) -> {
            if (monnaie >= coutPourAmeliorer) {
                monnaie -= coutPourAmeliorer;
                coutPourAmeliorer *= 2;
                portefeuille.setText("Argent : " + monnaie);
                root.getChildren().add(debloquerImage);
                root.getChildren().removeAll(debloquerAmelioration);
                coutAmelioration.setText("Coût pour améliorer : " + coutPourAmeliorer + " (" + (int) coutPourAmelioration + " pour l'amélioration qui diminue les coûts)");
            }
        });

        debloquerImage.setTranslateY(150);
        debloquerImage.setTranslateX(125);
        debloquerImage.setOnAction( (e) -> {
            if (monnaie >= coutPourAmeliorer) {
                monnaie -= coutPourAmeliorer;
                coutPourAmeliorer *= 2;
                portefeuille.setText("Argent : " + monnaie);
                bouton.setPrefSize(100, 100);
                bouton.setGraphic(view);
                bouton.setDefaultButton(true);
                bouton.setText("");
                bouton.setTranslateX(132);
                bouton.setTranslateY(135);
                root.getChildren().removeAll(debloquerImage);
                coutAmelioration.setText("Coût pour améliorer : " + coutPourAmeliorer + " (" + (int) coutPourAmelioration + " pour l'amélioration qui diminue les coûts)");
            }
        });

        //affichage

        stage.setScene(new Scene(root));
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.setFullScreen(false);
        stage.show();




    }

    public static void main(String[] args) {
        launch();
    }
}
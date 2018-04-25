//Michael Newsome
//Homework - College Expenses
//April 2018

package Classwork;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class CollegeExpenses extends Application implements EventHandler<ActionEvent> {

    Stage window;
    Scene scene;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    //buttons
    private Button resetButton = new Button("Reset");
    private Button calcButton = new Button("Calculate Total");
    private Button exitButton = new Button("Exit");

    //labels
    private Label monthsLabel = new Label("Number of months student is away: ");
    private Label rentLabel = new Label("Cost of rent per month: ");
    private Label utilLabel = new Label("Cost of utilities per month: ");
    private Label foodLabel = new Label("Cost of food per month: ");
    private Label carLabel = new Label("Cost of car rental or taxi fees per month: ");
    private Label gasLabel = new Label("Cost of gas per month: ");
    private Label entLabel = new Label("Cost of entertainment per month: ");
    private Label intLabel = new Label("Select internet package");
    private Label eduLabel = new Label("Cost of education for the year: ");
    private Label rateLabel = new Label("Rate this application 1 to 5");

    private RadioButton carCheck = new RadioButton("Personal vehicle used?");

    //text fields
    private TextField monthsField = new TextField();
    private TextField rentField = new TextField();
    private TextField utilField = new TextField();
    private TextField foodField = new TextField();
    private TextField carField = new TextField();
    private TextField gasField = new TextField();
    private TextField entField = new TextField();
    private ComboBox<String> intBox = new ComboBox<>();
    private TextField eduField = new TextField();

    private CheckBox verifyBox = new CheckBox("I verify the information above is correct");
    private Slider rateSlider = new Slider(1,5,1);

    @Override
    public void start(Stage primaryStage) throws Exception {

        //new window (Stage)
        window = primaryStage;
        window.setTitle("College Expenses");

        //create the grid layout
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setHgap(10);
        layout.setVgap(10);

        //set the scene
        scene = new Scene(layout, 700, 500);
        window.setScene(scene);
        window.show();

        intBox.getItems().addAll("None", "Basic", "Premium");

        //slider settings
        rateSlider.setShowTickMarks(true);
        rateSlider.setShowTickLabels(true);
        rateSlider.setSnapToTicks(true);
        rateSlider.setMajorTickUnit(1);
        rateSlider.setMinorTickCount(0);


        //grid parameters
        layout.add(monthsLabel, 0, 0);
        layout.add(monthsField, 1, 0);
        layout.add(rentLabel, 0, 1);
        layout.add(rentField, 1, 1);
        layout.add(utilLabel, 0, 2);
        layout.add(utilField, 1, 2);
        layout.add(foodLabel, 0, 3);
        layout.add(foodField, 1, 3);
        layout.add(carLabel, 0, 4);
        layout.add(carField, 1, 4);
        layout.add(carCheck, 2,4);
        layout.add(gasLabel, 0, 5);
        layout.add(gasField, 1, 5);
        layout.add(entLabel, 0, 6);
        layout.add(entField, 1, 6);
        layout.add(intLabel, 0, 7);
        layout.add(intBox, 1, 7);
        layout.add(eduLabel, 0, 8);
        layout.add(eduField, 1, 8);
        layout.add(verifyBox, 1,9);
        layout.add(resetButton,0,10);
        layout.add(calcButton,1,10);
        layout.add(exitButton,2,10);
        layout.add(rateLabel,0,11);
        layout.add(rateSlider,1,11);

        //button actions
        resetButton.setOnAction(this);
        calcButton.setOnAction(this);
        exitButton.setOnAction(this);

        //cannot figure out how to get the text field disabled
        /*
        if(!carCheck.isSelected()) {
            carField.setEditable(false);
        }
        */

    }

    @Override
    public void handle(ActionEvent event) {

        //reset the fields if the reset button is pressed
        if(event.getSource() == resetButton) {
            monthsField.setText("");
            rentField.setText("");
            utilField.setText("");
            foodField.setText("");
            carField.setText("");
            gasField.setText("");
            entField.setText("");
            intBox.valueProperty().set(null);
            eduField.setText("");
        }

        //calculate the results of the inputs if the calculate button is pressed
        if(event.getSource() == calcButton) {

            //convert the text fields to doubles
            Double months = Double.valueOf(monthsField.getText());
            Double rent = Double.valueOf(rentField.getText());
            Double util = Double.valueOf(utilField.getText());
            Double food = Double.valueOf(foodField.getText());
            Double car = Double.valueOf(carField.getText());
            Double gas = Double.valueOf(gasField.getText());
            Double ent = Double.valueOf(entField.getText());
            Double edu = Double.valueOf(eduField.getText());
            Double internet;
            String intPlan = intBox.getValue();

            if (intPlan.equals("None")) {
                internet = 0.0;
            } else if (intPlan.equals("Basic")) {
                internet = 20.0;
            } else if (intPlan.equals("Premium")) {
                internet = 50.0;
            } else {
                internet = 0.0;
            }

            //error handling
            if (months > 12 || months < 1) {
                alert.setTitle("Error");
                alert.setContentText("Months must be between 1 - 12");
                alert.showAndWait();
            }
            //validation handling
            else if(!verifyBox.isSelected()) {
                alert.setTitle("Error");
                alert.setContentText("Please verify the information is correct");
                alert.showAndWait();
            }
            else {
                Double totalExpense = months * (rent + util + food + car + gas + ent + internet + edu);

                Double allowedExpense = 0.0;
                Double savedExpense = 0.0;

                if (rent > 400) {
                    allowedExpense += 400;
                } else {
                    allowedExpense += rent;
                    savedExpense += 400 - rent;
                }
                if (util > 100) {
                    allowedExpense += 100;
                } else {
                    allowedExpense += util;
                    savedExpense += 100 - util;
                }
                if (food > 150) {
                    allowedExpense += 150;
                } else {
                    allowedExpense += food;
                    savedExpense += 150 - food;
                }
                if (car > 40) {
                    allowedExpense += 40;
                } else {
                    allowedExpense += car;
                    savedExpense += 40 - car;
                }
                if (gas > 60) {
                    allowedExpense += 60;
                } else {
                    allowedExpense += gas;
                    savedExpense += 60 - gas;
                }
                if (ent > 50) {
                    allowedExpense += 50;
                } else {
                    allowedExpense += ent;
                    savedExpense += 50 - ent;
                }
                if (internet > 20) {
                    allowedExpense += 20;
                } else {
                    allowedExpense += internet;
                    savedExpense += 20 - internet;
                }

                allowedExpense += edu;

                allowedExpense *= months;

                Double owedExpense = totalExpense - allowedExpense;

                //display the results of the calculations
                alert.setTitle("Results");
                alert.setContentText("Total charges: $" + totalExpense +
                        "\nTotal allowable expenses: $" + allowedExpense +
                        "\nTotal balance student must pay: $" + owedExpense +
                        "\nMoney parents saved: $" + savedExpense);
                alert.showAndWait();
            }
        }

        //close the application if the exit button is pressed
        if(event.getSource() == exitButton) {
            window.close();
        }

    }

    public static void main(String[] args) {

        //launch the application class
        launch(args);
    }

}

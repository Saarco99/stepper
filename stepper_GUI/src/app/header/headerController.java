package app.header;

import app.MVC_controller.MVC_controller;
import app.management.mainController;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import modules.DataManeger.DataManager;
import modules.DataManeger.GetDataFromXML;
import modules.stepper.Stepper;

import java.io.File;
import java.nio.file.Path;

public class headerController {

    @FXML
    private Button path;
    @FXML
    private Button FlowsDefinition;
    @FXML
    private Label flow1ProgressLabel;
    boolean subscription = false;
    @FXML
    private Label flow2ProgressLabel;
    @FXML
    private Label flow3ProgressLabel;
    @FXML
    private Label flow4ProgressLabel;
    @FXML
    private Button saveData;

    @FXML
    private Button loadData;
    Stepper stepperData;

    @FXML
    private ProgressBar flow1ProgressBar;
    @FXML
    private ProgressBar flow2ProgressBar;
    @FXML
    private ProgressBar flow3ProgressBar;
    @FXML
    private ProgressBar flow4ProgressBar;
    @FXML
    private GridPane progressGrid;

    @FXML
    private ToggleButton themeToggle;
    private RotateTransition rotateTransition;
    @FXML
    private Button Statistics;
    private MVC_controller controller;
    @FXML
    private Button FlowsExecution;
    @FXML
    private Button ExecutionsHistory;
    private mainController main;
    @FXML
    private Label loaded;

    @FXML
    private Button buypremiumBtn;
    
    @FXML
    void FlowsDefinitionPresent(ActionEvent event) {
        main.showFlowDefinition();
    }

    void StatsScreenPresent(){
        main.showStats();
    }
    public void setMVCController(MVC_controller controller){
        this.controller = controller;
    }
    public void setMainController(mainController main){
        this.main =main;
    }
    @FXML
    void FlowsExecutionFunc(ActionEvent event) {
        main.FlowsExecutionInMenu();
    }
    @FXML
    void ExecutionsHistoryFunc(ActionEvent event) {
        main.showHistoryExe();
    }

    @FXML
    void initialize() {
        assert flow3ProgressBar != null : "fx:id=\"flow3ProgressBar\" was not injected: check your FXML file 'header.fxml'.";
        assert flow2ProgressBar != null : "fx:id=\"flow2ProgressBar\" was not injected: check your FXML file 'header.fxml'.";
        assert Statistics != null : "fx:id=\"Statistics\" was not injected: check your FXML file 'header.fxml'.";
        assert flow4ProgressLabel != null : "fx:id=\"flow4ProgressLabel\" was not injected: check your FXML file 'header.fxml'.";
        assert flow2ProgressLabel != null : "fx:id=\"flow2ProgressLabel\" was not injected: check your FXML file 'header.fxml'.";
        assert loaded != null : "fx:id=\"loaded\" was not injected: check your FXML file 'header.fxml'.";
        assert buypremiumBtn != null : "fx:id=\"buypremiumBtn\" was not injected: check your FXML file 'header.fxml'.";
        assert flow1ProgressLabel != null : "fx:id=\"flow1ProgressLabel\" was not injected: check your FXML file 'header.fxml'.";
        assert path != null : "fx:id=\"path\" was not injected: check your FXML file 'header.fxml'.";
        assert themeToggle != null : "fx:id=\"themeToggle\" was not injected: check your FXML file 'header.fxml'.";
        assert flow4ProgressBar != null : "fx:id=\"flow4ProgressBar\" was not injected: check your FXML file 'header.fxml'.";
        assert flow3ProgressLabel != null : "fx:id=\"flow3ProgressLabel\" was not injected: check your FXML file 'header.fxml'.";
        assert FlowsDefinition != null : "fx:id=\"FlowsDefinition\" was not injected: check your FXML file 'header.fxml'.";
        assert ExecutionsHistory != null : "fx:id=\"ExecutionsHistory\" was not injected: check your FXML file 'header.fxml'.";
        assert progressGrid != null : "fx:id=\"ProgressGrid\" was not injected: check your FXML file 'header.fxml'.";
        assert FlowsExecution != null : "fx:id=\"FlowsExecution\" was not injected: check your FXML file 'header.fxml'.";
        assert flow1ProgressBar != null : "fx:id=\"flow1ProgressBar\" was not injected: check your FXML file 'header.fxml'.";
        assert saveData != null : "fx:id=\"saveData\" was not injected: check your FXML file 'header.fxml'.";
        assert loadData != null : "fx:id=\"loadData\" was not injected: check your FXML file 'header.fxml'.";
        Events();
        screensToggleGrouping();
        setCssScreenButtons();


    }

    private void setCssScreenButtons() {
        FlowsDefinition.getStyleClass().add("screenButton");
        FlowsExecution.getStyleClass().add("screenButton");
        ExecutionsHistory.getStyleClass().add("screenButton");
        Statistics.getStyleClass().add("screenButton");
        FlowsDefinition.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), FlowsDefinition);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
            FlowsDefinition.setStyle("-fx-background-color: rgb(139,0,201);-fx-border-color: white");
        });
        FlowsDefinition.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), FlowsDefinition);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            FlowsDefinition.setStyle("-fx-background-color: transparent;-fx-border-color: white");
        });
        FlowsExecution.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), FlowsExecution);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
            FlowsExecution.setStyle("-fx-background-color: rgb(139,0,201);-fx-border-color: white");
        });
        FlowsExecution.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), FlowsExecution);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            FlowsExecution.setStyle("-fx-background-color: transparent;-fx-border-color: white");
        });
        ExecutionsHistory.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), ExecutionsHistory);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
            ExecutionsHistory.setStyle("-fx-background-color: rgb(139,0,201);-fx-border-color: white");
        });
        ExecutionsHistory.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), ExecutionsHistory);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            ExecutionsHistory.setStyle("-fx-background-color: transparent;-fx-border-color: white");
        });
        Statistics.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), Statistics);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
            Statistics.setStyle("-fx-background-color: rgb(139,0,201);-fx-border-color: white");
        });
        Statistics.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), Statistics);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            Statistics.setStyle("-fx-background-color: transparent;-fx-border-color: white");
        });

    }

    @FXML
    void changeTheme(ActionEvent event) {
        if (themeToggle.isSelected()) {
            main.changeTheme("light");
        } else {
            main.changeTheme("dark");
        }

    }
    @FXML
    void SaveData(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName("StepperData");
        File file=fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                DataManager.saveDataGui(file.getPath());
            }catch (Exception e){//todo need to include all the exceptions from ex1
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Error saving data");
                alert.showAndWait();
            }
        }


    }

    @FXML
    void loadData(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Data");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file=fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                DataManager.loadDataGui(file.getPath());
                DataManager.getData().setXmlPath(file.getPath());
                ActivateMenuButtons();
                path.setText("Loaded:");
                loaded.setText(DataManager.getData().getXmlPath());
                initializedData();
            }catch (Exception e){//todo need to include all the exceptions from ex1
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Error loading data");
                alert.showAndWait();
            }
        }

    }
    private void screensToggleGrouping() {
       //todo change screens to be in toggle group and maybe change components to be in toggle group
        //update progress grid

    }
    @FXML
    void BuyPremium(ActionEvent event) {
        if(!subscription) {
            subscription = true;
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), buypremiumBtn);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
            RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(0.3), buypremiumBtn);
            rotateTransition2.setByAngle(360); // Rotate by 360 degrees
            rotateTransition2.setCycleCount(2); // Perform the rotation once
            rotateTransition2.setAutoReverse(false); // Disable auto-reverse
            rotateTransition2.play(); // Start the rotation animation
            buypremiumBtn.setRotate(0);
            scaleTransition.setToX(0.9);
            scaleTransition.setToY(0.9);
            scaleTransition.stop();
            scaleTransition.play();
            buypremiumBtn.setLayoutX(buypremiumBtn.getLayoutX() - 150);
            buypremiumBtn.setText("You are now Subscribed to Premium!");
            buypremiumBtn.setPrefWidth(Region.USE_COMPUTED_SIZE);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("I LOVE YOU <3");
            alert.setHeaderText(null);
            alert.setContentText("⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⢀⣾⡿⠋⠀⠿⠇⠉⠻⣿⣄⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⢠⣿⠏⠀⠀⠀⠀⠀⠀⠀⠙⣿⣆⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢠⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣆⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢸⣿⡄⠀⠀⠀⢀⣤⣀⠀⠀⠀⠀⣿⡿⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠻⣿⣶⣶⣾⡿⠟⢿⣷⣶⣶⣿⡟⠁⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡏⠉⠁⠀⠀⠀⠀⠉⠉⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⣸⣿⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⣿⡇⢀⣴⣿⠇⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⣠⣴⣿⣷⣿⠟⠁⠀⠀⠀⠀⠀⣿⣧⣄⡀⠀⠀⠀\n" +
                    "⠀⢀⣴⡿⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⢿⣷⣄⠀\n" +
                    "⢠⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣆\n" +
                    "⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿\n" +
                    "⣿⣇⠀⠀⠀⠀⠀⠀⢸⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿\n" +
                    "⢹⣿⡄⠀⠀⠀⠀⠀⠀⢿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡿\n" +
                    "⠀⠻⣿⣦⣀⠀⠀⠀⠀⠈⣿⣷⣄⡀⠀⠀⠀⠀⣀⣤⣾⡟⠁\n" +
                    "⠀⠀⠈⠛⠿⣿⣷⣶⣾⡿⠿⠛⠻⢿⣿⣶⣾⣿⠿⠛⠉⠀⠀");
            Window window = buypremiumBtn.getScene().getWindow();
            alert.initOwner(window instanceof Stage ? (Stage) window : null);
            alert.showAndWait();
        }

    }
    private void Events() {
        path.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), path);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
            path.setStyle("-fx-background-color: rgb(139,0,201);-fx-border-color: white");

        });
        path.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), path);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            path.setStyle("-fx-background-color: transparent;-fx-border-color: white");

        });
        path.setOnMousePressed( event -> {
            rotateTransition = new RotateTransition(Duration.seconds(1), path);
            rotateTransition.setByAngle(360); // Rotate by 360 degrees
            rotateTransition.setCycleCount(800); // Perform the rotation once
            rotateTransition.setAutoReverse(false); // Disable auto-reverse
            rotateTransition.play(); // Start the rotation animation
        });
        buypremiumBtn.setStyle("fx-border-color: #ffffff");
        buypremiumBtn.setStyle("-fx-border-width: 2px");
        buypremiumBtn.setStyle("-fx-text-fill: #ffff00");
        buypremiumBtn.setStyle("-fx-background-color:  #36393e");

        loadData.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), loadData);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
            loadData.setStyle("-fx-background-color: rgb(139,0,201);-fx-border-color: white");
        });
        loadData.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), loadData);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            loadData.setStyle("-fx-background-color: transparent;-fx-border-color: white");

        });
        saveData.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), saveData);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
            saveData.setStyle("-fx-background-color: rgb(139,0,201);-fx-border-color: white");
        });
        saveData.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), saveData);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
            saveData.setStyle("-fx-background-color: transparent;-fx-border-color: white");
        });
    }

    @FXML
    void StatisticsFunc(ActionEvent event) {
        main.showStats();

    }
    @FXML
    void loadDataXML(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        File selectedFile = fileChooser.showOpenDialog(null);
        stopRotate();
        path.setRotate(0);
        main.initialize();
        if (selectedFile != null) {
            try {
                GetDataFromXML.fromXmlFileToObject(selectedFile.getAbsolutePath());
                ActivateMenuButtons();
                DataManager.getData().setXmlPath(selectedFile.getPath());
                FlowsExecution.setDisable(false);
                FlowsDefinition.setDisable(false);
                ExecutionsHistory.setDisable(false);//***
               // ExecutionsHistory.setDisable(false);//***
                path.setText("Loaded:");
                loaded.setText(DataManager.getData().getXmlPath());
                initializedData();
                stopRotate();
                path.setRotate(0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void stopRotate() {
        rotateTransition.stop();
    }

    private void ActivateMenuButtons() {
        FlowsExecution.setDisable(false);
        FlowsDefinition.setDisable(false);
        Statistics.setDisable(false);
        if (DataManager.getData()!=null) {
            if (DataManager.getData().getFlowExecutions().size() > 0)
                ExecutionsHistory.setDisable(false);
            else
                ExecutionsHistory.setDisable(true);
        }

    }


    public void setDisableOnExecutionsHistory(){
        ExecutionsHistory.setDisable(false);
    }
    private void initializedData(){
        Stepper stepperData = DataManager.getData();
        main.setFlows(stepperData.getFlows());
    }
}



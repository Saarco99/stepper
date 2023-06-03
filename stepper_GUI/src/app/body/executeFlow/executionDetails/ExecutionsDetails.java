package app.body.executeFlow.executionDetails;

import app.body.bodyController;
import app.body.executeFlow.executionDetails.DataViewer.DataViewerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import modules.DataManeger.DataManager;
import modules.dataDefinition.impl.relation.RelationData;
import modules.flow.definition.api.StepUsageDeclaration;
import modules.flow.execution.FlowExecution;
import modules.stepper.Stepper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecutionsDetails {
    @FXML
    private VBox stepTree;
    @FXML
    private Pane logsPane;
    @FXML
    private VBox inputsVbox4Value;
    @FXML
    private VBox inputsVbox;
    @FXML
    private VBox outputsVbox4Value;
    @FXML
    private Label flowOutputsLabel;

    @FXML
    private VBox outputsVbox;
    @FXML
    private VBox logsVbox;
    @FXML
    private Label logsLabel;
    @FXML
    private Pane logScrollPane;
    @FXML
    private AnchorPane mainPane;
    private bodyController body;
    @FXML
    private Label executionCounterLabel;
    @FXML
    private Label exeTime;
    private static final String LOG_LINE_STYLE = "-fx-text-fill: #24ff21;";
    private static final String ERROR_LINE_STYLE = "-fx-text-fill: #ff0000;";
    private FlowExecution theFlow=null;

    //ctor
    @FXML
    void initialize() {
        Stepper stepperData = DataManager.getData();
        asserts();
        logsLabel.setText(". . .");
        ScrollPane scrollPane = new ScrollPane(logsVbox);
        scrollPane.setFitToWidth(true);
        if (stepperData.getFlowExecutions().size()!=0)
            theFlow = getLastFlowExecution(stepperData);
        if (theFlow != null) {
        updateLogs(theFlow, stepperData);
        updateLogsTree(theFlow);
        updateInputs(theFlow);
        updateOutputs(theFlow);
        }
    }

    private static FlowExecution getLastFlowExecution(Stepper stepperData) {
        return stepperData.getFlowExecutions().get(stepperData.getFlowExecutions().size() - 1);
    }

    private void asserts() {
        assert stepTree != null : "fx:id=\"stepTree\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert logsPane != null : "fx:id=\"logsPane\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert inputsVbox4Value != null : "fx:id=\"inputsVbox4Value\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert inputsVbox != null : "fx:id=\"inputsVbox\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert outputsVbox != null : "fx:id=\"outputsVbox\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert outputsVbox4Value != null : "fx:id=\"outputsVbox4Value\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert flowOutputsLabel != null : "fx:id=\"flowOutputsLabel\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert logsLabel != null : "fx:id=\"logsLabel\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert executionCounterLabel != null : "fx:id=\"executionCounterLabel\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";
        assert logsVbox != null : "fx:id=\"logsVbox\" was not injected: check your FXML file 'ExecutionsHistory.fxml'.";

    }
    private void updateLogs(FlowExecution flowExecution,Stepper stepperData) {
        logsVbox.getChildren().clear();
        Label logsLabel = new Label();
        logsLabel.setText("logs for flow with id : "+flowExecution.getUniqueId());
        logsLabel.setStyle("-fx-font-size: 14;"+LOG_LINE_STYLE);
        logsVbox.getChildren().add(logsLabel);
        logsVbox.getChildren().add(stepTree);
    }
    private void addLog(Pair<String, String> log) {
        Label newLog = new Label(log.getValue() + " : " + log.getKey());
        if(log.getValue().contains("ERROR"))
            newLog.setStyle(ERROR_LINE_STYLE+";-fx-font-size: 12;");
        else
            newLog.setStyle(LOG_LINE_STYLE+";-fx-font-size: 12;");
        logsVbox.getChildren().add(newLog);
    }
    private void updateLogsTree(FlowExecution selectedFlow) {
        stepTree.getChildren().clear();
        TreeView<String> stepTreeView = new TreeView<>();
        TreeItem<String> root = new TreeItem<>("Steps");
        stepTreeView.setRoot(root);
        for (StepUsageDeclaration step : selectedFlow.getFlowDefinition().getFlowSteps()) {
            TreeItem<String> stepRoot = new TreeItem<>(step.getFinalStepName());
            List<Pair<String, String>> logsPerStep = selectedFlow.getLogs().get(step.getFinalStepName());
            if (logsPerStep != null) {
                for (Pair<String, String> log : logsPerStep) {
                    if (log != null) {
                        TreeItem<String> logItem = new TreeItem<>(log.getValue() + " : " + log.getKey());
                        stepRoot.getChildren().add(logItem);
                    }
                }
            }

            stepTreeView.getRoot().getChildren().add(stepRoot);
            stepTreeView.setCellFactory(treeView -> {
                TreeCell<String> cell = new TreeCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item);
                            setStyle("-fx-background-color: transparent; -fx-text-fill: green;"); // Set the style of each tree component
                        }
                    }
                };

                cell.setOnMouseClicked(event -> {
                    if (!cell.isEmpty()) {
                        TreeItem<String> treeItem = cell.getTreeItem();
                        if (treeItem != null) {
                            if (treeItem.isExpanded()) {
                                treeItem.setExpanded(false);
                            } else {
                                treeItem.setExpanded(true);
                            }
                        }
                    }
                });

                return cell;
            });
            stepTreeView.setShowRoot(false);
            stepTreeView.getStyleClass().add("logsTree");

        }
        stepTree.getChildren().add(stepTreeView);
        //logScrollPane.setStyle("-fx-background-color: transparent;");
    }
    private void addLogToTree(TreeView<String> stepItem, Pair<String, String> log) {
        TreeItem<String> logItem = new TreeItem<>(log.getValue() + " : " + log.getKey());
        stepItem.getRoot().getChildren().add(logItem);
    }

    public void setFlowExecution(FlowExecution flow) {
        theFlow = flow;
    }
    private void updateTime(FlowExecution selectedFlow) {
        exeTime.setDisable(false);
        exeTime.setVisible(true);
        exeTime.setText("Total-Time: "+selectedFlow.getTotalTime().toMillis()+" MS ");
    }

    private void updateOutputs(FlowExecution selectedFlow) {
        Label title= (Label) this.outputsVbox.getChildren().get(0);//todo 333
        Label title2= (Label) this.outputsVbox4Value.getChildren().get(0);//todo 333
        this.outputsVbox4Value.getChildren().clear();
        this.outputsVbox.getChildren().clear();
        this.outputsVbox.getChildren().add(title);
        this.outputsVbox4Value.getChildren().add(title2);
        Map<String, Object> outputs=selectedFlow.getAllExecutionOutputs();
        if(outputs!=null) {
            for (Map.Entry<String, Object> entry : outputs.entrySet()) {
                Label newOutput = new Label(entry.getKey());
                Label outputValue = setLabelForOutput(entry.getValue(),entry.getKey());//todo implement
                newOutput.getStyleClass().add("DDLabel");
                outputValue.getStyleClass().add("DDLabel");
                newOutput.setPrefWidth(outputsVbox.getPrefWidth());
                outputValue.setPrefWidth(outputsVbox4Value.getPrefWidth());
                newOutput.setPrefHeight(28);
                outputValue.setPrefHeight(28);
                this.outputsVbox4Value.getChildren().add(outputValue);
                this.outputsVbox.getChildren().add(newOutput);
            }
        }

    }

    private Label setLabelForOutput(Object value, String name) {
        Label result = new Label();
        if (value instanceof RelationData) {
            result.setText("relation");
        } else if (value instanceof ArrayList) {//print an arraylist
            result.setText("arraylist");
        }
        if (value instanceof List) {
            result.setText("list");
        } else {
            result.setText(value.toString());
        }
        result.setOnMouseEntered(event -> {
            result.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
        });
        result.setOnMouseExited(event -> {
            result.setStyle("-fx-border-color: #ffff00; -fx-border-width: 1px;");
        });
        result.setOnMouseClicked(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataViewer/DataViewer.fxml"));
                        Parent root = (Parent) loader.load();
                        DataViewerController controller = loader.getController();

                        if (controller!=null ) {
                            controller.setData(value, name);
                            Stage stage = new Stage();
                            stage.setTitle("Data Viewer");
                            stage.setScene(new Scene(root, 500, 300));
                            stage.showAndWait();
                        }
                    } catch (IOException e) {
                        System.out.println("failed to load data viewer");
                    }
                }
        );
        return result;
    }

    private void updateInputs(FlowExecution selectedFlow) {
        Label title = (Label) this.inputsVbox.getChildren().get(0);
        Label title2 = (Label) this.inputsVbox4Value.getChildren().get(0);
        this.inputsVbox.getChildren().clear();
        this.inputsVbox4Value.getChildren().clear();
        this.inputsVbox.getChildren().add(title);
        this.inputsVbox4Value.getChildren().add(title2);
        List<Pair<String, String>> inputs = selectedFlow.getUserInputs();
        if (inputs != null) {
            for (Pair<String, String> entry : inputs) {
                Label newInput = new Label(entry.getKey());
                Label inputValue = new Label(entry.getValue());
                inputValue.setOnMouseEntered(event -> {
                    inputValue.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
                });
                inputValue.setOnMouseExited(event -> {
                    inputValue.setStyle("-fx-border-color: #ffff00; -fx-border-width: 1px;");
                });
                inputValue.setOnMouseClicked(event -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DataViewer/DataViewer.fxml"));
                            try {
                                Parent root = loader.load();
                                DataViewerController controller = loader.getController();
                                controller.setData(entry.getValue(), entry.getKey());
                                Stage stage = new Stage();
                                stage.setTitle("Data Viewer");
                                stage.setScene(new Scene(root, 500, 300));
                                stage.showAndWait();
                            } catch (IOException e) {
                                //giveup
                            }
                        }
                );

                newInput.getStyleClass().add("DDLabel");
                inputValue.getStyleClass().add("DDLabel");
                newInput.setPrefWidth(inputsVbox.getPrefWidth());
                inputValue.setPrefWidth(inputsVbox4Value.getPrefWidth());
                newInput.setPrefHeight(28);
                inputValue.setPrefHeight(28);
                this.inputsVbox4Value.getChildren().add(inputValue);
                this.inputsVbox.getChildren().add(newInput);
                //todo set them as pressable and get extra info
            }
        }
    }
}


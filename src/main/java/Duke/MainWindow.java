package Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hi! My name is Duke and I will be your task manager for today :)\n" +
                        "checkout https://prgj.github.io/ip/ for a list of commands I can manage", dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
//     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
//    @FXML
//    private void handleUserInput() throws DukeException {
//        Label userText = new Label(userInput.getText());
//////        Label dukeText = new Label(commandParser.parseCommand(userInput.getText(), taskList, uiPrinter, storage));
//        Label dukeText = new Label(duke.getResponse(userInput.getText()));
////        String userText = userInput.getText();
////        String dukeText = commandParser.parseCommand(userText, taskList, uiPrinter, storage);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(userImage)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
////                DialogBox.getUserDialog(userText, user),
////                DialogBox.getDukeDialog(dukeText, duke)
//        );
//        userInput.clear();
//    }
}
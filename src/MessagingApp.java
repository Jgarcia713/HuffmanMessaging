import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MessagingApp extends Application {

	private BorderPane pane;
	private TextArea[] messages;

	@Override
	public void start(Stage stage) throws Exception {
		pane = new BorderPane();
		messages = new TextArea[4];
		initializeDisplay();

		Scene scene = new Scene(pane, 400, 600);
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
	}

	public void initializeDisplay() {
		GridPane topGrid = new GridPane();
		GridPane botGrid = new GridPane();
		pane.setTop(topGrid);
		pane.setBottom(botGrid);

		TextArea field = new TextArea();
		field.setPrefWidth(320);
		field.setMaxHeight(100);
		field.setWrapText(true);
		botGrid.setPadding(new Insets(0, 0, 20, 0));
		botGrid.setHgap(10);
		botGrid.add(field, 4, 0);

		field.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				System.out.println(field.getText().trim());
				field.clear();
			}
		});

		topGrid.setHgap(10);
		topGrid.setVgap(10); // x y

		TextArea message = new TextArea(
				"This is filler text that is being stored into this area to see how it is formatted");
		message.setMaxSize(175, 105);
		message.setEditable(false);
		message.setWrapText(true);

		TextArea message2 = new TextArea(
				"This is filler text that is being stored into this area to see how it is formatted");
		message2.setMaxSize(175, 105);
		message2.setEditable(false);
		message2.setWrapText(true);

		TextArea message3 = new TextArea(
				"This is filler text that is being stored into this area to see how it is formatted");
		message3.setMaxSize(175, 105);
		message3.setEditable(false);
		message3.setWrapText(true);

		TextArea message4 = new TextArea(
				"This is filler text that is being stored into this area to see how it is formatted");
		message4.setMaxSize(175, 105); // can change to 100 if I want more space on the bottom
		message4.setEditable(false);
		message4.setWrapText(true);

		topGrid.add(message, 2, 1); // max
		topGrid.add(message2, 3, 2); // max
		topGrid.add(message3, 3, 3); // max
		topGrid.add(message4, 2, 4); // max

	}
}

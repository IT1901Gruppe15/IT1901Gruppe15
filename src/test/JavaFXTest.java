package test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXTest extends Application{

	//private Text centerText;
	//private TextField textField;
	//private Rectangle rect;
	BorderPane root = new BorderPane(); // Root of the scene graph
	Pane login = new Pane();
	Pane main = new Pane();
	StackPane list = new StackPane();
	Text mainText = new Text();


	public void start(Stage stage) throws Exception {



		Button loginBtn = new Button();
		loginBtn.setText("Log in");
		loginBtn.setTranslateY(loginBtn.getTranslateY() + 50);
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				root.setCenter(main);
			}
		});

		Button koieOversikt = new Button();
		koieOversikt.setText("Oversikt");
		koieOversikt.setTranslateY(koieOversikt.getTranslateY() + 50);
		koieOversikt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainText.setText("Oversikt");
			}
		});

		Button bestillinger = new Button();
		bestillinger.setText("Bestillinger");
		bestillinger.setTranslateY(bestillinger.getTranslateY() + 50);
		bestillinger.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainText.setText("Bestillinger");;
			}
		});
		bestillinger.setTranslateX(bestillinger.getTranslateX() + 100);

		Button skaderMangler = new Button();
		skaderMangler.setText("Skader/Mangler");
		skaderMangler.setTranslateY(skaderMangler.getTranslateY() + 50);
		skaderMangler.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainText.setText("Skader og mangler");;
			}
		});
		skaderMangler.setTranslateX(skaderMangler.getTranslateX() + 200);

		/*textField = new TextField("center");
        textField.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent actionEvent) {
        		centerText.setText(textField.getText().toUpperCase());
        	}
        });
        textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> value, String oldText, String newText) {
				centerText.setText(textField.getText());
			}
        });*/
		//centerText = new Text(180, 180, textField.getText()); // x, y , text

		//root.setTop(textField);
		//root.setTop(new Text("top"));
		Text title = new Text("NTNUI koier");
		title.setFont(new Font("Times New Roman", 32));
		root.setTop(title);
		//root.setBottom(new Text("Bottom"));
		//root.setLeft(new Text("Left"));
		//root.setRight(new Text("Right"));
		//root.setCenter(new Text("center"));
		//centerText = new Text(180, 180, "center");
		//List<String> fonts = Font.getFamilies();
		//centerText.setFont(new Font(fonts.get((int) (Math.random() * fonts.size())), 32));


		/*Shape[] shapes = {
        		new Line(10, 10, 100, 100),
        		new Rectangle(150, 10, 30, 40),
        		new Ellipse(40, 140, 49, 30),
        		//new Text(200, 200, "center")
        };
        shapes[1].setFill(Color.BLUE);
        shapes[2].setFill(Color.GREEN);
        shapes[2].setStroke(Color.YELLOW);
        shapes[2].setStrokeWidth(10);
        pane.getChildren().addAll(shapes);*/
		//root.setCenter(centerText);

		/*pane.getChildren().addAll(
        		new Line(10, 10, 100, 100),
        		new Rectangle(150, 10, 30, 40),
        		new Ellipse(40, 140, 49, 30),
        		new Text(200, 200, "center")
        );*/
		//rect = new Rectangle(150, 10, 30, 40);
		//pane.getChildren().add(rect);
		//pane.getChildren().add(centerText);
		TextField pass = new TextField("Passord");
		pass.setTranslateY(pass.getTranslateY() + 25);
		login.getChildren().add(new TextField("Brukernavn"));
		login.getChildren().add(pass);
		login.getChildren().add(koieOversikt);
		login.getChildren().add(loginBtn);
		main.getChildren().add(koieOversikt);
		main.getChildren().add(bestillinger);
		main.getChildren().add(skaderMangler);
		main.getChildren().add(mainText);
		mainText.setTranslateX(mainText.getTranslateX() + 100);
		mainText.setTranslateY(mainText.getTranslateY() + 100);
		root.setCenter(login);

		Scene scene = new Scene(root, 500, 500);

		stage.setScene(scene);
		stage.setTitle("NTNUI koier");
		stage.show();
	}
	public static void main(String[] args) {
		launch(JavaFXTest.class, args);
	}
}

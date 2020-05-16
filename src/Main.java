import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;



public class Main extends Application {

    static Controller controller_a;
    static Controller controller_b;
    public final static int scale = 100;
    private final static int floors_num = 5;
    public void inputFloor(Controller controller, int query){
        if (query >= 1 && query <= floors_num){
            controller.add(query);
        }
    }
    public static void main(String[] args){
        controller_a = new Controller(scale);
        controller_a.start();
        controller_b = new Controller(scale);
        controller_b.start();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Lifts Lab (Danilov Sergey)");
        Label floor_a = new Label("");
        Label floor_b = new Label("");
        floor_a.setTranslateX(100);
        floor_a.setFont(new Font("Arial", 18));
        floor_b.setTranslateX(300);
        floor_b.setFont(new Font("Arial", 18));

        Button[] buttons_a = new Button[floors_num];
        Button[] buttons_b = new Button[floors_num];
        for (int i = 0; i < floors_num; i++){
            buttons_a[i] = new Button(Integer.toString(i+1));
            buttons_a[i].setTranslateX(0);
            buttons_a[i].setTranslateY(500-100*i);

            buttons_b[i] = new Button(Integer.toString(i+1));
            buttons_b[i].setTranslateX(250);
            buttons_b[i].setTranslateY(500-100*i);

            int finalI = i;
            buttons_a[i].setOnAction(actionEvent -> inputFloor(controller_a, finalI +1));
            buttons_b[i].setOnAction(actionEvent -> inputFloor(controller_b, finalI +1));
        }

        Button[] panel_a = new Button[floors_num];
        Button[] panel_b = new Button[floors_num];

        for (int i = 0; i < floors_num; i++){
            panel_a[i] = new Button(Integer.toString(i+1));
            panel_a[i].setTranslateX(70 + 20*i);
            panel_a[i].setTranslateY(600);

            panel_b[i] = new Button(Integer.toString(i+1));
            panel_b[i].setTranslateX(320 + 20*i);
            panel_b[i].setTranslateY(600);

            int finalI = i;
            panel_a[i].setOnAction(actionEvent -> inputFloor(controller_a, finalI +1));
            panel_b[i].setOnAction(actionEvent -> inputFloor(controller_b, finalI +1));
        }

        stage.setWidth(500);
        stage.setHeight(800);

        Rectangle lift_a = new Rectangle(100,0,50,100);
        Rectangle lift_b = new Rectangle(350,0,50,100);

        lift_a.setFill(Color.RED);
        lift_b.setFill(Color.RED);

        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        pane.getChildren().addAll(buttons_a);
        pane.getChildren().addAll(buttons_b);
        pane.getChildren().addAll(lift_a, lift_b);
        pane.getChildren().addAll(floor_a, floor_b);
        pane.getChildren().addAll(panel_a);
        pane.getChildren().addAll(panel_b);

        stage.setScene(scene);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                lift_a.setY(550-controller_a.getLiftPosition());
                lift_b.setY(550-controller_b.getLiftPosition());
                floor_a.setText("FLOOR "+ controller_a.getLiftPosition() / scale);
                floor_b.setText("FLOOR "+ controller_b.getLiftPosition() / scale);
                for (int i = 0; i < panel_a.length; i++){
                    if (controller_a.up.contains(i+1) || controller_a.down.contains(i+1)) {
                        panel_a[i].setStyle("-fx-border-color: #ff0000; -fx-border-width: 1px;");
                    }
                    else{
                        panel_a[i].setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
                    }
                    if (controller_b.up.contains(i+1) || controller_a.down.contains(i+1)){
                        panel_b[i].setStyle("-fx-border-color: #ff0000; -fx-border-width: 1px;");
                    }
                    else{
                        panel_b[i].setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
                    }
                }
            };
        };



        timer.start();
        stage.show();
    }
}

package lab05_b;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Lab05_B extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Restaurant Menu");
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);        
        
        double[] beveragePrices = {2.5, 2, 1.75, 2.95, 1.5, 2.5};
        Label beverage = new Label("Beverage");
        ComboBox<String> beverages = new ComboBox<>();
        beverages.getItems().addAll("Coffee", "Tea", "Soft Drink", "Water", "Milk", "Juice");
        root.add(beverage, 0, 0);
        root.add(beverages, 1, 0);
        
        double[] appetizerPrices = {4.5, 3.75, 5.25, 3, 6.75};
        Label appetizer = new Label("Appetizer");
        ComboBox<String> appetizers = new ComboBox<>();
        appetizers.getItems().addAll("Soup", "Salad", "Spring Rolls", "Garlic Bread", "Chips and Salsa");
        root.add(appetizer, 0, 1);
        root.add(appetizers, 1, 1);
        
        double[] coursePrizes = {15, 13.5, 13.95, 11.9, 18.99, 11.75, 12.25};
        Label course = new Label("Main Course");
        ComboBox<String> courses = new ComboBox<>();
        courses.getItems().addAll("Steak", "Grilled Chicken", "Chicken Alfredo", "Turkey Club", "Shrimp Scampi", "Pasta", "Fish and Chips");
        root.add(course, 0, 2);
        root.add(courses, 1, 2);

        double[] dessertPrizes = {5.95, 4.50, 4.75, 3.25, 5.98};
        Label dessert = new Label("Desserts");
        ComboBox<String> desserts = new ComboBox<>();
        desserts.getItems().addAll("Apple Pie", "Carrot Cake", "Mud Pie", "Pudding", "Apple Crisp");
        root.add(dessert, 0, 3);
        root.add(desserts, 1, 3);

        Slider slider = new Slider(0.0, 20.0, 15.0);
        slider.setShowTickMarks(true);
        Label sliderLabel = new Label("15%");
        Label tip = new Label("Tip (%)");
        Label total = new Label("The total is 0.00$");
        root.add(tip, 0, 4);
        root.add(slider, 0, 5);
        root.add(sliderLabel, 0, 6);
        slider.setOnMouseReleased(e -> {
            sliderLabel.setText(trunc(slider.getValue()) + "%");
            double totalPrice = (1 + trunc(slider.getValue())/100) 
                                * (8);
            total.setText("The total is " + trunc(totalPrice) + "$");
        });
        root.add(total, 0, 7);
        
        Button clear = new Button("Clear Bill");
        
        root.add(clear, 1, 7);
        
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    public static double trunc(double value) {
        return Math.floor(value * 100) / 100;
    }
 
}

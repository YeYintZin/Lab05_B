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
        
        double[] beveragePrices = {2.5, 2, 1.75, 2.95, 1.5, 2.5, 0};
        Label beverage = new Label("Beverage");
        ComboBox<String> beverages = new ComboBox<>();
        beverages.getItems().addAll("Coffee", "Tea", "Soft Drink", "Water", "Milk", "Juice");
        root.add(beverage, 0, 0);
        root.add(beverages, 1, 0);
        
        double[] appetizerPrices = {4.5, 3.75, 5.25, 3, 6.75, 0};
        Label appetizer = new Label("Appetizer");
        ComboBox<String> appetizers = new ComboBox<>();
        appetizers.getItems().addAll("Soup", "Salad", "Spring Rolls", "Garlic Bread", "Chips and Salsa");
        root.add(appetizer, 0, 1);
        root.add(appetizers, 1, 1);
        
        double[] coursePrizes = {15, 13.5, 13.95, 11.9, 18.99, 11.75, 12.25, 0};
        Label course = new Label("Main Course");
        ComboBox<String> courses = new ComboBox<>();
        courses.getItems().addAll("Steak", "Grilled Chicken", "Chicken Alfredo", "Turkey Club", "Shrimp Scampi", "Pasta", "Fish and Chips");
        root.add(course, 0, 2);
        root.add(courses, 1, 2);

        double[] dessertPrizes = {5.95, 4.50, 4.75, 3.25, 5.98, 0};
        Label dessert = new Label("Desserts");
        ComboBox<String> desserts = new ComboBox<>();
        desserts.getItems().addAll("Apple Pie", "Carrot Cake", "Mud Pie", "Pudding", "Apple Crisp");
        root.add(dessert, 0, 3);
        root.add(desserts, 1, 3);
        System.out.println(desserts.getSelectionModel().getSelectedIndex());

        Slider slider = new Slider(0.0, 20.0, 15.0);
        slider.setShowTickMarks(true);
        Label sliderLabel = new Label("15%");
        Label tip = new Label("Tip (%)");
        Label tax = new Label("Taxes: ");
        Label total = new Label("The total is 0.0$");
        root.add(tip, 0, 4);
        root.add(slider, 0, 5);
        root.add(sliderLabel, 0, 6);
        root.add(tax, 0, 7);
        slider.setOnMouseReleased(e -> {
            sliderLabel.setText(trunc(slider.getValue()) + "%");
            int appIndex = appetizers.getSelectionModel().getSelectedIndex();
            int courseIndex = courses.getSelectionModel().getSelectedIndex();
            int beverageIndex = beverages.getSelectionModel().getSelectedIndex();
            int dessertIndex = desserts.getSelectionModel().getSelectedIndex();
            if (appIndex == -1) {
                appIndex = 5;
            }
            if (courseIndex == -1) {
                courseIndex = 7;
            }
            if (beverageIndex == -1) {
                beverageIndex = 6;
            }
            if (dessertIndex == -1) {
                dessertIndex = 5;
            }
            double totalPrice = (1 + trunc(slider.getValue())/100) 
                                * (
                                appetizerPrices[appIndex] +
                                coursePrizes[courseIndex] +
                                beveragePrices[beverageIndex] +
                                dessertPrizes[dessertIndex]
                                );
            tax.setText("Taxes: " + trunc(totalPrice * 0.15) + "$");
            total.setText("The total is " + trunc(totalPrice * 1.15) + "$");
        });
        root.add(total, 0, 8);
        
        Button clear = new Button("Clear Bill");
        clear.setOnAction(e -> {
            beverages.getSelectionModel().clearSelection();
            appetizers.getSelectionModel().clearSelection();
            courses.getSelectionModel().clearSelection();
            desserts.getSelectionModel().clearSelection();
            tax.setText("Taxes: ");
            total.setText("The total is 0.0$");
        });
        root.add(clear, 1, 8);
        
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    public static double trunc(double value) {
        return Math.floor(value * 100) / 100;
    }
 
}

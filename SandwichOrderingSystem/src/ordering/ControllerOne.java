package ordering;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerOne implements Initializable {

	
	Order order = new Order();
	
	@FXML
	private Button addSelected, clearButton, showOrder;
	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private ImageView image;
	
	 @FXML
	 private TextField priceDisplay;

	@FXML
	private ListView<String> includedIngredients,ingredientSelection, extraIngredients;

	private static final Image chickenSandwich = new Image("chicken.jpg");
	private static final Image beefSandwich = new Image("beef.jpg");
	private static final Image fishSandwich = new Image("fish.jpg");

	ObservableList<String> ingredientSelect=FXCollections.observableArrayList();
	ObservableList<String> extrasSelected = FXCollections.observableArrayList();
	
	
	//this method is to show the second window when the show order button is clicked
	//its not working idk 
    @FXML
    void sendToView2(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewTwo.fxml"));
    	
    	Parent root = loader.load();
    	
    	ControllerTwo controller2 = loader.getController();
    	controller2.randPrint();
    	
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root));
    	stage.setTitle("Scene 2");
    	stage.show();
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Intializes the display to start off with chicken as the selected item
		ObservableList<String> items = FXCollections.observableArrayList("Chicken", "Beef", "Fish");
		comboBox.setItems(items);
		comboBox.setValue("Chicken");
		ObservableList<String> chickenIngredients = FXCollections.observableArrayList("Chicken", "Spicy Sauce",
				"Pickles");
		includedIngredients.setItems(chickenIngredients);
		image.setImage(chickenSandwich);
		setThePrice();
		
		
		for(Extra ingredients: Extra.values()) {
			ingredientSelect.add(ingredients.toString());
		}
	
//		ingredientSelect.addAll("Bacon", "Cheddar", "Ketchup", "Lettuce", "Mayonaise",
	//					"Mushrooms", "Onion", "Peppers", "Spinach", "Tomatos");
		
		ingredientSelection.setItems(ingredientSelect);
		extraIngredients.setItems(extrasSelected);
		
		// this works when you press ctrl and select and item but it doesnt seem to work properly 
		//so lets jus ignore it for now
		//ingredientSelection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}
	
	

    @FXML
    void addToOrder(ActionEvent event) {
    	
    	String value = comboBox.getValue();
    	OrderLine ol;
    	switch (value) {
    	
    	case "Chicken":
    		
    		ChickenSandwich cs = new ChickenSandwich();
        	for(int i=0; i<extrasSelected.size();i++) {
        		cs.add(extrasSelected.get(i));
        	}
        	//cs.printArray();
        	//System.out.println(cs.price());
        	//System.out.println(cs.toString());
        	
        	ol = new OrderLine(order.size()+1,cs,cs.price());
        	//System.out.println(ol.toString());
        	order.add(ol);
        	System.out.println("This is the orde so far -- basically just gotta send this to the second controller");
        	System.out.println("--------------------------------------------------------------------------------");
        	System.out.println(order.printArr());
    		
    		break;
    		
    	case "Beef":
    		BeefSandwich bs = new BeefSandwich();
        	for(int i=0; i<extrasSelected.size();i++) {
        		bs.add(extrasSelected.get(i));
        	}
        	bs.printArray();
        	//System.out.println(bs.price());
        	//System.out.println(bs.toString());
        	
        	ol = new OrderLine(order.size()+1,bs,bs.price());
        	//System.out.println(ol.toString());
        	order.add(ol);
        	System.out.println(order.printArr());
    		break;
    	
    	case "Fish":
    		FishSandwich fs = new FishSandwich();
        	for(int i=0; i<extrasSelected.size();i++) {
        		fs.add(extrasSelected.get(i));
        	}
        	fs.printArray();
        	//System.out.println(fs.price());
        	//System.out.println(fs.toString());
        	
        	ol = new OrderLine(order.size()+1,fs,fs.price());
        	//System.out.println(ol.toString());
        	order.add(ol);
        	System.out.println(order.printArr());
    		break;
    	}
    	
    }

	@FXML
	void changeSandwich(ActionEvent event) {
		String value = comboBox.getValue();
		//System.out.println(value);
		switch (value) {

		case "Chicken":
			image.setImage(chickenSandwich);
			
			ObservableList<String> chickenIngredients = FXCollections.observableArrayList("Chicken", "Spicy Sauce",
					"Pickles");
			includedIngredients.setItems(chickenIngredients);
			setThePrice();
			
			//includedIngredients.setText("Chicken\nSpicy Sauce\nPickles");
			break;

		case "Beef":
			image.setImage(beefSandwich);
			
			ObservableList<String> beefIngredients = FXCollections.observableArrayList("Roast Beef", "Provolone Cheese",
					"Mustard");
			includedIngredients.setItems(beefIngredients);
			setThePrice();
			
			//includedIngredients.setText("Roast Beef\nProvolone Cheese\nMustard");
			break;

		case "Fish":
			image.setImage(fishSandwich);
			
			ObservableList<String> fishIngredients = FXCollections.observableArrayList("Grilled Snapper", "Cilantro",
					"Lime");
			includedIngredients.setItems(fishIngredients);
			setThePrice(); 
			
			//includedIngredients.setText("Grilled Snapper\nCilantro\nLime");
			break;
		}
	}

	//adds to the extra area when called
	@FXML
	void addtoExtra(ActionEvent event) {
		if (extrasSelected.contains(ingredientSelection.getSelectionModel().getSelectedItem()) != true && extrasSelected.size()<Sandwich.MAX_EXTRAS) {
			extrasSelected.add(ingredientSelection.getSelectionModel().getSelectedItem());
			setThePrice();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid");
			alert.setHeaderText("Duplicate Selection");
			alert.setContentText("This item has already been added");
			alert.showAndWait();
		}
	}
	
	//removes extras from the extras area when called
	@FXML
	void removeExtra(ActionEvent event) {
		extrasSelected.remove(extraIngredients.getSelectionModel().getSelectedItem());
		setThePrice();
		//System.out.println(extraIngredients.getItems());
	}
	
	//puts the price in the text entry
	void setThePrice() {
		int size = extrasSelected.size();
		
		if(comboBox.getValue().equals("Chicken")) {
			double price = 8.99 + size*1.99;
			priceDisplay.setText(String.valueOf(price));			
		}else if (comboBox.getValue().equals("Beef")) {
			double price = 10.99 + size*1.99;
			priceDisplay.setText(String.valueOf(price));
		}else {
			double price = 12.99 + size*1.99;
			priceDisplay.setText(String.valueOf(price));
		}
	}
	
	
	@FXML
	void clearAll(ActionEvent event) {
		extrasSelected.clear();
		setThePrice();
	}
	
}

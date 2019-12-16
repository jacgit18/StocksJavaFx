package com.red.stocks.fxml;

import java.awt.Checkbox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.red.stocks.dao.StockDBDAO;
import com.red.stocks.dao.CategoryDAO;
import com.red.stocks.dao.IQuery;
import com.red.stocks.dao.SectorDAO;
import com.red.stocks.fxml.model.Sector;
import com.red.stocks.fxml.model.Stock;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StockController implements Initializable {

	@FXML
	private AnchorPane apStock;

	@FXML
	private TableView<Stock> tvStock;

	@FXML
	private TableColumn<Stock, String> colSymbol;

	@FXML
	private TableColumn<Stock, String> colName;

	@FXML
	private TableColumn<Stock, Float> colPrice;
	
    @FXML
    private TableColumn<Stock, Float> colNet;

    @FXML
    private TableColumn<Stock, Float> colDividend;
    
    @FXML
    private TableColumn<Stock, Float>  colSector;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtField;

	@FXML
	private Button btnSearch;

	@FXML
	private Label lblMessage;

	@FXML
	private RadioButton rbSimple;

	@FXML
	private ToggleGroup tgSearchMode;

	@FXML
	private RadioButton rbAdvanced;
	
	@FXML
	private RadioButton rbAdvancedfilter;

	@FXML
	private ComboBox<String> cbCategroy;
	
	@FXML
	private FlowPane fpSector;
	
	// Pie Chart Skeleton
    @FXML
    private AnchorPane apLayout;

    @FXML
    private VBox vbLayout;

    @FXML
    private PieChart pieProduct;

	@FXML
	void onKeyReleased(KeyEvent event) {
		int size = txtField.getText().length();
		if (size > 0) {
			btnSearch.setDisable(false);
		} else {
			btnSearch.setDisable(true);
		}

	}

	@FXML
	void search(ActionEvent event) {
		if (rbAdvanced.isSelected()) {
			this.advanceSearch();
		} else if (rbSimple.isSelected()) {
			this.simpleSearch();

		}else if (rbAdvancedfilter.isSelected()) {
			this.simpleSearch();

		}else if (rbAdvancedfilter.isSelected()) {
			this.advanceSearch();

		}

		

	}

	private void simpleSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();

		tvStock.getItems().clear();

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText();

		map.put("symbol", "eq:" + symbol);

		List<Stock> allStocks = dao.findBy(map);

		for (Stock stock : allStocks) {
			stocks.add(stock);
		}
	}

	private void advanceSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();

		tvStock.getItems().clear();

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText(); // just added
		// think about changing data type
		String netIncome = txtField.getText(); // just added
		String dividendYield = txtField.getText(); // just added

		String price = txtField.getText();
		String category = cbCategroy.getValue();

		map.put("symbol", "eq:" + symbol); // just added
		map.put("price", "gt:" + price);
		map.put("category", "eq:" + category);

		List<Stock> allStocks = dao.findBy(map);

		for (Stock stock : allStocks) {
			stocks.add(stock);
		}
	}

	// why put sector as both types
	private static List<Sector> sectors = new ArrayList<Sector>();
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Caused by: java.lang.NullPointerException this error means we need to avoid
		// null so we created a if statement to do this
		if (cbCategroy != null) {

			CategoryDAO dao = new CategoryDAO();
			List<String> categories = dao.findAll();
			cbCategroy.getItems().addAll("All");
			cbCategroy.getItems().addAll(categories);
			cbCategroy.setValue("All");

		}
		// fix StockDao
		if (fpSector != null) {
			SectorDAO sectdao = new SectorDAO();
			sectors = sectdao.findAll();
			
			for (Sector sector : sectors) {
				CheckBox chkBox = new CheckBox(sector.getSectorinfo() + " - " + 
			sector.getSectorDescription()); 
				fpSector.getChildren().add(chkBox);

			}
			

		}
		
		
//		lblTitle.setText("1st FXML Project");
//		
//		productDAO dao = new productDAO();
//		List<Product> list = dao.findAll();
//		for (Product product : list) {
//			PieChart.Data slice1 = new PieChart.Data(product.getName(), product.getPrice());
//			pieProduct.getData().add(slice1);
//
//		}
	}

	@FXML
	void searchMode(ActionEvent event) throws IOException {

		Object o = event.getSource();
		String message = "";

		Stage stage = null;
		Parent root = null;

		if (o == rbSimple) {
			message = "simple mode selected ";
			stage = (Stage) rbAdvanced.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("StockView.fxml"));
		} else if (o == rbAdvanced) {
			message = "advanced mode selected ";
			stage = (Stage) rbAdvanced.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("StockAdvancedView.fxml"));
		}else if (o == rbAdvancedfilter) {
			message = "advanced filter mode selected ";
			stage = (Stage) rbAdvancedfilter.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("StockAdvancedWithCheckBoxesView.fxml"));
		}


		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		lblMessage.setText(message + Math.random());
	}

}

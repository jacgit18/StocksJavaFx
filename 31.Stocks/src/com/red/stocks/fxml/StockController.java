package com.red.stocks.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
import javafx.scene.Node;
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
	
	@FXML
    private CheckBox chkAll;
	
	  @FXML
	   private Button btnAll;

	    @FXML
	    private RadioButton rbDiv;

	
	// Pie Chart Skeleton
	
    @FXML
    private RadioButton rbPiechart;
	
    @FXML
    private AnchorPane apLayout;

    @FXML
    private VBox vbLayout;

    @FXML
    private PieChart pieProduct;
    
	// why put sector as both types
	private static List<Sector> sectors = new ArrayList<Sector>();
	
	private CheckBox chkBox;
	
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
		
//		StockDBDAO dao = new StockDBDAO();
//		List<Stock> net = dao.get
//		colNet.getItems();
	
		
//			colNet.getItems().addAll("All");
//		colNet.getItems().addAll(categories);
//		colNet.setValue("All");
		// fix StockDao
		if (fpSector != null) {
			SectorDAO sectdao = new SectorDAO();
			sectors = sectdao.findAll();
			

			
			for (Sector sector : sectors) {
				 this.chkBox = new CheckBox(sector.getSector() + " - " + 
			sector.getSectorDescription()); 
				// data type taken is an object meaning it takes in any data type
				// so when you retrieve the data downcast it
				chkBox.getStyleClass().add("sector");
				chkBox.setUserData(sector.getSector());
				fpSector.getChildren().add(chkBox);

			}
			CheckBox chBoxAll = new CheckBox();
			chBoxAll.setText("All");
			chBoxAll.setUserData("e-01,e-02,e-03,e-04,e-05,e-06,e-07,e-08,e-09,e-10,e-11,");
			fpSector.getChildren().addAll(chBoxAll);  
//			if (condition) {
//				
//			}



		}
		// Pie test
        
//		Map<String, String> map = new HashMap<>();
//		String symbol = colSymbol.getText(); // just added
//		String price = colPrice.getText();
//		map.put("sector", "eq:" + symbol); // just added
//		map.put("price", "gt:" + price);
//
//		StockDBDAO dao = new StockDBDAO();
//		List<Stock> list = dao.findBy(map);
//		for (Stock sec : list) {
//			PieChart.Data slice1 = 
//					new PieChart.Data(sec.getSymbol(),sec.getPrice());
//			pieProduct.getData().add(slice1);
//		
//		}
	}
    
    
    
	@FXML
	void onKeyReleased(KeyEvent event) {
		System.out.println(event);
		int size = txtField.getText().length();
		if (size == 0) {
			btnSearch.setDisable(true);
		} else {
			btnSearch.setDisable(false);
		}

	}
  
	@FXML
	void search(ActionEvent event) {
		if (rbSimple.isSelected()) {
			this.simpleSearch();
		} else if (rbAdvanced.isSelected()) {
			this.advanceSearch();

		}else if (rbAdvancedfilter.isSelected()) {
			this.advanceFilterSearch();

		}
	}
	

	@FXML
	void searchMode(ActionEvent event) throws IOException {

		Object o = event.getSource();
		String message = "";

		try {
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
		else if (o == rbPiechart) {
			message = "pie chart mode selected ";
			stage = (Stage) rbPiechart.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("PieChartView.fxml"));
		}


		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}

		lblMessage.setText(message + Math.random());
	}


	private void simpleSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();
		tvStock.getItems().clear();

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText();
		map.put("symbol", "eq:" + symbol);
		
		
		// not dynamic 
//		String price = txtField.getText();
//
//		
//		map.put("price", "gt:" + price);


		List<Stock> allStocks = dao.findBy(map);

//		stocks.addAll(allStocks);
			

			for (Stock stock : allStocks) {
				stocks.add(stock);
			}
			lblMessage.setText(" Number of stocks:  " +  allStocks.size());

	}

	private void advanceSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();
		tvStock.getItems().clear();

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText(); // just added
		
		String category = cbCategroy != null ? cbCategroy.getValue(): "All";
		// dropbox not used yet
		String price = txtField.getText();


//		map.put("symbol", "eq:" + symbol); // just added
		map.put("price", "gt:" + price);
		map.put("category", "eq:" + category);

		List<Stock> allStocks = dao.findBy(map);
		
//		stocks.addAll(allStocks);


		for (Stock stock : allStocks) {
			stocks.add(stock);

		}
		lblMessage.setText(" Number of stocks:  " +  allStocks.size());

	}
	
	private List<String> items = new ArrayList<>();// changed to list
	private CheckBox cb;
	int i = 0;

	
	private void advanceFilterSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();
		tvStock.getItems().clear();

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText(); // just added
		String price = txtField.getText();
//		String categories = "";

//		// think about changing data type
//		String netIncome = txtField.getText(); // just added
//		String dividendYield = txtField.getText(); // just added
		ObservableList<Node> selectedfilter = fpSector.getChildren();
		for (Node node : selectedfilter) {
//			System.out.println(node);// first test
			cb = (CheckBox) node;
//			CheckBox array = cb; 
			if (cb.isSelected()) {
				items.add(cb.getUserData().toString());
				
			}
			else if (!cb.isSelected()) {
				
				

			}
			
			
			System.out.println(cb.getUserData() + " " + cb.isSelected());
			// second test 
		}
		
		String [] selectedFilter = items.stream().toArray(String[]::new);
		
		System.out.println(items);
		
		String categories = String.join(",", selectedFilter);
		
//		map.put("symbol", "eq:" + symbol); // just added
		map.put("categories", categories); 
//		map.put("price", "gt:" + price);

		map.put("price", "lt:" + price);


		List<Stock> allStocks = dao.findBy(map);

//		for (Stock stock : allStocks) {
//			stocks.addAll(allStocks);
//		}
			
			for (Stock stock : allStocks) {
				stocks.add(stock);
				lblMessage.setText(" Number of stocks:  " +  allStocks.size());

			}

	}

	public void actionPerformed (ActionEvent e) {
		for (int i = 0; i < items.size(); i++) {
			if (!cb.isSelected()) {
				items.add(cb.getUserData().toString());


			}
		}
	}

}

package com.red.stocks.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.red.stocks.dao.IQuery;
import com.red.stocks.dao.StockDBDAO;
import com.red.stocks.fxml.model.Stock;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StockController implements Initializable {

	@FXML
	private AnchorPane ApStock;

	@FXML
	private TableView<Stock> tvStocks;

	@FXML
	private TableColumn<Stock, String> CoSymbol;

	@FXML
	private TableColumn<Stock, String> CoName;

	@FXML
	private TableColumn<Stock, Float> CoPrice;

	@FXML
	private Label LblTitle;

	@FXML
	private TextField TxTfd;

	@FXML
	private Button Search_btn;

	@FXML
	private Label lbl_Message;
	

    @FXML
    private RadioButton SimpleRadio;
    

    @FXML
    private RadioButton PriceSearch;
    
    @FXML
    private TextField TxTfdPrice;

    @FXML
    private ToggleGroup TGSearchMode;

    @FXML
    private RadioButton AdvancedRadio;


	@FXML
	void OnKeyReleased(KeyEvent event) {

		int size = TxTfd.getText().length();
		if (size > 0) {
			Search_btn.setDisable(false);

		} else {
			Search_btn.setDisable(true);

		}
	}

	@FXML
	void search(ActionEvent event) {
		ObservableList<Stock> stocks = tvStocks.getItems();
		tvStocks.getItems().clear();

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = TxTfd.getText();
		map.put("symbol", "eq:" + symbol);
		List<Stock> allStocks = dao.findBy(map);

		for (Stock stock : allStocks) {
			stocks.add(stock);

		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	@FXML
	void SearchMode(ActionEvent event) throws IOException {
		Object o = event.getSource();
		String message = "";
		 
		Stage stage = null;
		Parent root = null;
		
		if (o == SimpleRadio) {
			message = "Simple Search Mode ";
			stage = (Stage) AdvancedRadio.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("StockView.fxml"));
		}else if(o == AdvancedRadio ) {
			message =  "Advanced Search Mode ";
			stage = (Stage) AdvancedRadio.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("StockAdvancedView.fxml"));
		}
		else if(o == PriceSearch ) {
			message =  "Advanced Price Search Mode ";
			stage = (Stage) PriceSearch.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("StockAdvancedPriceSearchView.fxml"));
		}

		Scene scene = new Scene(root); 
		stage.setScene(scene);
	    stage.show();

		lbl_Message.setText(message + Math.random()); 
	}

}

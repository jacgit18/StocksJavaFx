package com.red.stocks.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.red.stocks.fxml.dao.StockDAO;
import com.red.stocks.fxml.model.Stock;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class StockController implements Initializable {

    @FXML
    private AnchorPane ApStock;

    @FXML
    private TableView<Stock> TB;

    @FXML
    private TableColumn<Stock, String> CoSymbol;

    @FXML
    private TableColumn<Stock, String> CoName;

    @FXML
    private TableColumn<Stock, Float> CoPrice;

    @FXML
    private Label LblTitle;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		ObservableList<Stock> stocks = TB.getItems();
		
		StockDAO dao = new StockDAO(); 
		List<Stock> allStocks = dao.findAll();
		
		for (Stock stock : allStocks) {
			stocks.add(stock);

		}
		
		
	}

}

package com.red.stocks.fxml;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    @FXML
    private TextField TxTfd;
    
    @FXML
    private Button Search_btn;

    

	@FXML
    void search(ActionEvent event) {
		ObservableList<Stock> stocks = TB.getItems();
		TB.getItems().clear();
		
		IQuery<Stock> dao = new StockDBDAO(); 
		
		Map<String, String> map = new HashMap<>();
		String symbol = TxTfd.getText();
		map.put("symbol","eq:" + symbol);
		List<Stock> allStocks = dao.findBy(map);


		
		for (Stock stock : allStocks) {
			stocks.add(stock);

		}
    }
    
    

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {


		
		
	}
	
	


}

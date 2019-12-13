package com.red.stocks.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.red.stocks.fxml.Product;
import com.red.stocks.fxml.productDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PieChartController implements Initializable {

    @FXML
    private AnchorPane apLayout;

    @FXML
    private VBox vbLayout;

    @FXML
    private PieChart pieProduct;

    @FXML
    private Label lblTitle;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		lblTitle.setText("1st FXML Project");
		
		productDAO dao = new productDAO();
		List<Product> list = dao.findAll();
		for (Product product : list) {
			PieChart.Data slice1 = new PieChart.Data(product.getName(), product.getPrice());
			pieProduct.getData().add(slice1);

		}
		
		
		
		
	}

}

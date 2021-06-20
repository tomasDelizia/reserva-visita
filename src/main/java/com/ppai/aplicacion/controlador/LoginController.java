package com.ppai.aplicacion.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ppai.aplicacion.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@Controller
public class LoginController {
	
	
	//@Autowired
	//private LoginService loginService;

	@Autowired
	private StageManager stageManager;

	private Stage mainStage;

	@FXML
	private Button btnLogin;
	@FXML
	private Button btnCancel;

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void loginEvent(ActionEvent event) throws IOException {

		//loginService.loginService();

		Parent root = stageManager.getLoader("homeView.fxml").load();

		Scene scene = new Scene(root);

		mainStage = stageManager.getMainStage();
		
		mainStage.setTitle("home");

		mainStage.setScene(scene);

		mainStage.show();

	}

}

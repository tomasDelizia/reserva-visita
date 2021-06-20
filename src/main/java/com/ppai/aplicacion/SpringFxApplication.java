package com.ppai.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringFxApplication extends Application {
	
	static ApplicationContext applicationContext; 

	public static void main(String[] args) {
		applicationContext=SpringApplication.run(SpringFxApplication.class, args);
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("SpringFx");
		
		StageManager stageManager=	applicationContext.getBean(StageManager.class);
				
		stageManager.setMainStage(primaryStage);
		FXMLLoader loader= stageManager.getLoader("reservaVisita.fxml");
		 
		 Parent root =loader.load();

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);

		primaryStage.show();
		
		
	}

}


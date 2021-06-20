package com.ppai.aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

@Component
public class StageManager {
	
	
	@Autowired
	private ApplicationContext applicationContext;

	private Stage mainStage;

	public FXMLLoader getLoader(String path) {
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(param -> applicationContext.getBean(param));
		loader.setLocation(getClass().getClassLoader().getResource(path));
		return loader;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
}

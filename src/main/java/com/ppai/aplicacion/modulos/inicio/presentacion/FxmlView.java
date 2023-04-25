package com.ppai.aplicacion.modulos.inicio.presentacion;

import java.util.ResourceBundle;

public enum FxmlView {
    MAIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("principal.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Principal.fxml";
        }
    }, 
    LOGIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    RESERVA_VISITA {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("reservaVisita.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ReservaVisita.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}//end FxmlView
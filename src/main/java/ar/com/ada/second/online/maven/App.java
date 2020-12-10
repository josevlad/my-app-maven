package ar.com.ada.second.online.maven;


import ar.com.ada.second.online.maven.controller.MainController;

public class App {

    public static void main(String[] args) {
        MainController mainController = MainController.getInstance();
        mainController.init();

    }
}

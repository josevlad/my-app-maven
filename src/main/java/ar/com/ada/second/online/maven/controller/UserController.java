package ar.com.ada.second.online.maven.controller;

import ar.com.ada.second.online.maven.view.UserView;

public class UserController {

    private static UserController userController;
    private MainController mainController = MainController.getInstance();
    private UserView userView = UserView.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        if (userController == null) userController = new UserController();
        return userController;
    }
}

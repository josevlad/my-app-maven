package ar.com.ada.second.online.maven.controller;

import ar.com.ada.second.online.maven.model.dao.JpaPostDAO;
import ar.com.ada.second.online.maven.view.MainView;
import ar.com.ada.second.online.maven.view.PostView;

public class PostController {

    private static PostController postController;
    private PostView postView = PostView.getInstance();
    private MainView mainView = MainView.getInstance();
    private JpaPostDAO jpaPostDAO = JpaPostDAO.getInstance();

    private PostController() {
    }

    public static PostController getInstance() {
        if (postController == null) postController = new PostController();
        return postController;
    }

    public void init() {
        boolean shouldItStay = true;
        postView.showTitlePostModule();

        while (shouldItStay) {
            Integer option = postView.postMenuSelectOption();
            switch (option) {
                case 1:
                    System.out.println("crear post");
                    break;
                case 2:
                    System.out.println("listar post");
                    break;
                case 3:
                    System.out.println("editar post");
                    break;
                case 4:
                    System.out.println("eliminar post");
                    break;
                case 5:
                    shouldItStay = false;
                    mainView.showTitleReturnMenu();
                    break;
                default:
                    mainView.invalidOption();
            }
        }
    }
}

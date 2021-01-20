package ar.com.ada.second.online.maven.controller;

import ar.com.ada.second.online.maven.model.dao.JpaPostDAO;
import ar.com.ada.second.online.maven.model.dao.JpaUserDAO;
import ar.com.ada.second.online.maven.model.dao.PostDAO;
import ar.com.ada.second.online.maven.model.dao.UserDAO;
import ar.com.ada.second.online.maven.model.dto.PostDTO;
import ar.com.ada.second.online.maven.model.dto.UserDTO;
import ar.com.ada.second.online.maven.view.MainView;
import ar.com.ada.second.online.maven.view.PostView;

public class PostController {

    private static PostController postController;
    private PostView postView = PostView.getInstance();
    private MainView mainView = MainView.getInstance();
    private JpaPostDAO jpaPostDAO = JpaPostDAO.getInstance();
    private JpaUserDAO jpaUserDAO = JpaUserDAO.getInstance();

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
                    createPost();
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

    private void createPost() {
        UserDTO authorUser = getAuthorUser();

        if (authorUser != null) {
            String postBody = postView.getDataPost();

            if (postBody.equalsIgnoreCase("q")) {
                postView.cancelledProcess();
                return;
            }

            PostDTO newPost = new PostDTO(postBody, authorUser);

            PostDAO toSave = PostDAO.toDAO(newPost);

            jpaPostDAO.save(toSave);

            newPost.setId(toSave.getId());

            postView.showNewPost(newPost);

        } else {
            postView.cancelledProcess();
        }

    }

    private UserDTO getAuthorUser() {
        String nickname = null;
        Boolean hasUserFound = null;
        UserDAO byNickname = null;

        while (byNickname == null) {
            if (hasUserFound != null)
                postView.userNotFound(nickname);

            nickname = postView.getNicknameToFind();

            if (nickname.equalsIgnoreCase("q"))
                break;

            byNickname = jpaUserDAO.findByNickname(nickname);
            hasUserFound = false;
        }

        return nickname.equalsIgnoreCase("q") ?
                null :
                UserDAO.toDTO(byNickname);
    }
}

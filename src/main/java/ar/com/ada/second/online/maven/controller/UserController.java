package ar.com.ada.second.online.maven.controller;

import ar.com.ada.second.online.maven.model.dao.JpaUserDAO;
import ar.com.ada.second.online.maven.model.dao.UserDAO;
import ar.com.ada.second.online.maven.model.dto.UserDTO;
import ar.com.ada.second.online.maven.view.MainView;
import ar.com.ada.second.online.maven.view.UserView;

import java.util.HashMap;

public class UserController {

    private static UserController userController;
    private UserView userView = UserView.getInstance();
    private MainView mainView = MainView.getInstance();
    private JpaUserDAO jpaUserDAO = JpaUserDAO.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        if (userController == null) userController = new UserController();
        return userController;
    }

    public void init() {
        boolean shouldItStay = true;
        userView.showTitleUserModule();

        while (shouldItStay) {
            Integer option = userView.userMenuSelectOption();
            switch (option) {
                case 1:
                    createNewUser();
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

    private void createNewUser() {
        HashMap<String, String> dataNewUser = userView.getDataNewUser();

        // 1ra
        String nickname = dataNewUser.get("nickname");
        String email = dataNewUser.get("email");

        UserDTO userDTO = new UserDTO(nickname, email);

        /**
        // 2da A
        String nickname = dataNewUser.get("nickname");
        String email = dataNewUser.get("email");

        UserDTO userDTO = new UserDTO();
        userDTO.setNickname(nickname);
        userDTO.setEmail(email);


        // 2da B
        UserDTO userDTO = new UserDTO();
        userDTO.setNickname(dataNewUser.get("nickname"));
        userDTO.setEmail(dataNewUser.get("email"));
        */

        // validacion de registro en la base de datos
        try {
            jpaUserDAO.findByEmailOrNickName(userDTO.getEmail(), userDTO.getNickname());
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            userView.existingUser();
            return;
        }

        UserDAO userDAO = UserDAO.toDao(userDTO);

        jpaUserDAO.save(userDAO);

        userDTO.setId(userDAO.getId());

        userView.showNewUser(userDTO);
    }
}

package ar.com.ada.second.online.maven.controller;

import ar.com.ada.second.online.maven.model.dao.JpaUserDAO;
import ar.com.ada.second.online.maven.model.dao.UserDAO;
import ar.com.ada.second.online.maven.model.dto.UserDTO;
import ar.com.ada.second.online.maven.utils.Keyboard;
import ar.com.ada.second.online.maven.utils.Paginator;
import ar.com.ada.second.online.maven.view.MainView;
import ar.com.ada.second.online.maven.view.UserView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
                case 2:
                    showAllUsers();
                    break;
                case 3:
                    editUser();
                    break;
                case 4:
                    deleteUser();
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

        UserDAO userDAO = UserDAO.toDAO(userDTO);

        jpaUserDAO.save(userDAO);

        userDTO.setId(userDAO.getId());

        userView.showNewUser(userDTO);
    }

    private void showAllUsers() {
        printRecordsPerPage(null, true);
    }

    private void editUser() {
        UserDAO userToEdit = getUserToEditOrDelete(Paginator.EDIT);
        if (userToEdit != null) {
            HashMap<String, String> dataEditUser = userView.getDataEditUser(userToEdit);

            if (!dataEditUser.get("nickname").isEmpty())
                userToEdit.setNickname(dataEditUser.get("nickname"));

            if (!dataEditUser.get("email").isEmpty())
                userToEdit.setEmail(dataEditUser.get("email"));

            jpaUserDAO.save(userToEdit);
            // jpaUserDAO.update(userToEdit);

            UserDTO userDTO = UserDAO.toDTO(userToEdit);

            userView.showUser(userDTO);
        }
    }

    private void deleteUser() {
        UserDAO userToDelete = getUserToEditOrDelete(Paginator.DELETE);
        if (userToDelete != null) {
            Boolean answer = userView.areYouSureToRemoveIt(userToDelete);
            if (answer) {
                Boolean hasDeleted = jpaUserDAO.delete(userToDelete);
                if (hasDeleted) {
                    userView.userHasBeenSuccessfullyRemoved();
                } else {
                    userView.errorWhenDeletingUser();
                }
            } else {
                userView.editOrDeleteUserCanceled(Paginator.DELETE);
            }
        } else {
            userView.editOrDeleteUserCanceled(Paginator.DELETE);
        }
    }

    private UserDAO getUserToEditOrDelete(String optionEditOrDelete) {
        boolean shouldGetOut = false;
        Optional<UserDAO> userToEdithOptional = Optional.empty();
        String actionInfo = Paginator.EDIT.equals(optionEditOrDelete) ? "editar" : "eliminar";
        userView.selectUserIdToEditOrDeleteInfo(actionInfo);

        Integer userIdToEdit = printRecordsPerPage(optionEditOrDelete, false);

        if (userIdToEdit != 0) {
            while (!shouldGetOut) {
                userToEdithOptional = jpaUserDAO.findById(userIdToEdit);
                if (!userToEdithOptional.isPresent()) {
                    userView.userNotExist(userIdToEdit);
                    userIdToEdit = userView.userIdSelection(optionEditOrDelete);
                    shouldGetOut = (userIdToEdit == 0);
                } else {
                    shouldGetOut = true;
                }
            }
        }

        return userToEdithOptional.isPresent() ? userToEdithOptional.get() : null;
    }

    private Integer printRecordsPerPage(String optionSelectEditOrDelete, boolean isHeaderShown) {
        int limit = 4,
                currentPage = 0,
                totalUsers,
                totalPages,
                usersIdSelected = 0;

        List<UserDAO> users;
        List<String> paginator;

        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            totalUsers = jpaUserDAO.getTotalRecords();
            totalPages = (int) Math.ceil((double) totalUsers / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);
            users = jpaUserDAO.findAll(currentPage * limit, limit);

            if (!users.isEmpty()) {
                String choice = userView.printUsersPerPage(users, paginator, optionSelectEditOrDelete, isHeaderShown);

                switch (choice) {
                    case "i":
                    case "I":
                        currentPage = 0;
                        break;
                    case "a":
                    case "A":
                        if (currentPage > 0) currentPage--;
                        break;
                    case "s":
                    case "S":
                        if (currentPage + 1 < totalPages) currentPage++;
                        break;
                    case "u":
                    case "U":
                        currentPage = totalPages - 1;
                        break;
                    case "q":
                    case "Q":
                        shouldGetOut = true;
                        break;
                    default:
                        if (optionSelectEditOrDelete != null) {
                            usersIdSelected = Integer.parseInt(choice);
                            shouldGetOut = true;
                        } else {
                            if (choice.matches("^-?\\d+$")) {
                                int page = Integer.parseInt(choice);
                                if (page > 0 && page <= totalPages) currentPage = page - 1;
                            } else Keyboard.invalidData();
                        }
                }
            } else {
                shouldGetOut = true;
                userView.usersListNotFound();
            }
        }

        return usersIdSelected;
    }
}

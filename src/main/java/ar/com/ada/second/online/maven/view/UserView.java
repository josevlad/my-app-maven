package ar.com.ada.second.online.maven.view;

import ar.com.ada.second.online.maven.model.dao.UserDAO;
import ar.com.ada.second.online.maven.model.dto.UserDTO;
import ar.com.ada.second.online.maven.utils.CommandLineTable;
import ar.com.ada.second.online.maven.utils.Keyboard;
import ar.com.ada.second.online.maven.utils.Paginator;

import java.util.HashMap;
import java.util.List;

public class UserView {
    private static UserView userView;

    private UserView() {
    }

    public static UserView  getInstance() {
        if (userView == null) userView = new UserView();
        return userView;
    }

    public void showTitleUserModule() {
        System.out.println("\n####################################");
        System.out.println("#   Ada Social Network: Usuarios   #");
        System.out.println("####################################\n");
    }

    public Integer userMenuSelectOption() {
        System.out.println("Que desea realizar: ");
        System.out.println("| 1 | Crear un usuario");
        System.out.println("| 2 | Lista de usuarios");
        System.out.println("| 3 | Editar usuario");
        System.out.println("| 5 | Regresar el menu principal");
        return Keyboard.getInputInteger();
    }

    public HashMap<String, String> getDataNewUser() {
        System.out.println("\n#########################################");
        System.out.println("#   Ada Social Network: Nuevo Usuario   #");
        System.out.println("#########################################\n");

        HashMap<String, String> data = new HashMap<>();

        System.out.println("Ingrese el nuevo nickname: ");
//        String nickname = Keyboard.getInputString();
//        data.put("nickname", nickname);
        data.put("nickname", Keyboard.getInputString());

        System.out.println("Ingrese el nuevo email: ");
//        String email = Keyboard.getInputString();
//        data.put("email", email);
        data.put("email", Keyboard.getInputEmail());

        return data;
    }

    public void existingUser() {
        System.out.println("Oops!! el usuario ya existe en la base de datos");
        Keyboard.pressEnterKeyToContinue();
    }

    public void showNewUser(UserDTO dto) {
        System.out.println("\nUsuario creado con exito:");
        System.out.printf("id: %d", dto.getId());
        System.out.printf("\nEmail: %s", dto.getEmail());
        System.out.printf("\nNickname: %s\n\n", dto.getNickname());
    }

    public String printUsersPerPage(List<UserDAO> users, List<String> paginator, String optionSelectEdithOrDelete, boolean isHeaderShown) {
        if (isHeaderShown) {
            System.out.println("\n###########################################");
            System.out.println("#   Ada Social Network: Lista de Usuario   #");
            System.out.println("###########################################\n");
        }

        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);

        st.setHeaders("ID", "Nickname", "Email");
        users.forEach(userDAO -> {
            st.addRow(
                    userDAO.getId().toString(),
                    userDAO.getNickname(),
                    userDAO.getEmail()
            );
        });
        st.print();

        if (optionSelectEdithOrDelete != null && !optionSelectEdithOrDelete.isEmpty())
            paginator.set(paginator.size() - 2, optionSelectEdithOrDelete);

        System.out.println("\n+----------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.println("\n+----------------------------------------+");


        return Keyboard.getInputString();
    }

    public void usersListNotFound() {
        System.out.println("No hay usuario registrados en la base de datos");
        Keyboard.pressEnterKeyToContinue();
    }

    public void selectUserIdToEditOrDeleteInfo(String action) {
        System.out.println("Seleccione el id para " + action + " de la siguiente lista de usuarios: ");
        Keyboard.pressEnterKeyToContinue();
    }

    public void userNotExist(Integer id) {
        System.out.println("No existe un usuario con el id " + id + " asociado");
        System.out.println("Selecciones un ID valido o 0 para cancelar");
    }

    public Integer userIdSelection(String action) {
        switch (action) {
            case Paginator.EDIT:
                action = "editar";
                break;
        }
        System.out.println("Ingrese el numero de ID del usuario para " + action + " ó 0 para cancelar: \n");

        return Keyboard.getInputInteger();
    }

    public HashMap<String, String> getDataEditUser(UserDAO dao) {
        System.out.println("\n#########################################");
        System.out.println("#   Ada Social Network: Editar Usuario   #");
        System.out.println("#########################################\n");

        HashMap<String, String> data = new HashMap<>();

        System.out.printf("Ingrese el nickname (%s): \n", dao.getNickname());
        data.put("nickname", Keyboard.getInputString());

        System.out.printf("Ingrese el email (%s): \n", dao.getEmail());
        data.put("email", Keyboard.getInputEmail());

        return data;
    }

    public void showUser(UserDTO dto) {
        System.out.println("\nDatos del Usuario:");
        System.out.printf("id: %d", dto.getId());
        System.out.printf("\nemail: %s", dto.getEmail());
        System.out.printf("\nNickname: %s\n", dto.getNickname());

        Keyboard.pressEnterKeyToContinue();
    }
}

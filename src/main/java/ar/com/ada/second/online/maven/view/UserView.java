package ar.com.ada.second.online.maven.view;

import ar.com.ada.second.online.maven.utils.Keyboard;

import java.util.HashMap;

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
}

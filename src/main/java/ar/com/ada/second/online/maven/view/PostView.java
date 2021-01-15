package ar.com.ada.second.online.maven.view;

import ar.com.ada.second.online.maven.utils.Keyboard;

public class PostView {
    private static PostView postView;

    private PostView() {
    }

    public static PostView getInstance() {
        if (postView == null) postView = new PostView();
        return postView;
    }

    public void showTitlePostModule() {
        System.out.println("\n####################################");
        System.out.println("#   Ada Social Network: Post   #");
        System.out.println("####################################\n");
    }

    public Integer postMenuSelectOption() {
        System.out.println("Que desea realizar: ");
        System.out.println("| 1 | Crear un post");
        System.out.println("| 2 | Lista de post");
        System.out.println("| 3 | Editar pont");
        System.out.println("| 4 | Eliminar post");
        System.out.println("| 5 | Regresar el menu principal");
        return Keyboard.getInputInteger();
    }
}

package ar.com.ada.second.online.maven.view;

import ar.com.ada.second.online.maven.model.dto.PostDTO;
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

    public String getNicknameToFind() {
        System.out.println("\n######################################");
        System.out.println("#   Ada Social Network: Nuevo Post   #");
        System.out.println("######################################\n");

        System.out.println("Ingrese el seudonimo del usuario autor del post [ presione Q|q para cancelar ]: ");

        return Keyboard.getInputAlphanumeric();
    }

    public void userNotFound(String nickname) {
        System.out.printf("\nOops! Usuario no encontrado con el seudonimo %s\n\n", nickname);
    }

    public void cancelledProcess() {
        System.out.println("Proceso cancelado");
        Keyboard.pressEnterKeyToContinue();
    }

    public String getDataPost() {
        System.out.println("Ingrese en contenido del post [ presione Q|q para cancelar ]: ");
        return Keyboard.getInputAlphanumeric();
    }

    public void showNewPost(PostDTO dto) {
        System.out.println("\nPost creado con exito:\n");
        System.out.printf("Post id: %d", dto.getId());
        System.out.printf("\nEmail: %s", dto.getUser().getEmail());
        System.out.printf("\nNickname: %s\n", dto.getUser().getNickname());
        System.out.printf("\nPost: %s\n\n", dto.getBody());
    }
}

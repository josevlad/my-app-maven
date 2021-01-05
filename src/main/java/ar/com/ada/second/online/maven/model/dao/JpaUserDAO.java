package ar.com.ada.second.online.maven.model.dao;

public class JpaUserDAO extends JPA implements DAO<UserDAO> {

    private static JpaUserDAO jpaUserDAO;

    private JpaUserDAO() {
    }

    public static JpaUserDAO getInstance() {
        if (jpaUserDAO == null) jpaUserDAO = new JpaUserDAO();
        return jpaUserDAO;
    }

    @Override
    public void save(UserDAO userDAO) {
        // Consumer<EntityManager> persisUser = entityManager -> entityManager.persist(userDAO);
        // executeInsideTransaction(persisUser);
        executeInsideTransaction(entityManager -> entityManager.persist(userDAO));
    }
}

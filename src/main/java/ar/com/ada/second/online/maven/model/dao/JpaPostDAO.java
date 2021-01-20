package ar.com.ada.second.online.maven.model.dao;

import java.util.Optional;

public class JpaPostDAO extends JPA implements DAO<PostDAO> {

    private static JpaPostDAO jpaPostDAO;

    private JpaPostDAO(){
    }

    public static JpaPostDAO getInstance() {
        if (jpaPostDAO == null) jpaPostDAO = new JpaPostDAO();
        return jpaPostDAO;
    }

    @Override
    public void save(PostDAO dao) {
        if (dao.getId() == null)
            executeInsideTransaction(entityManager -> entityManager.persist(dao));
        else
            executeInsideTransaction(entityManager -> entityManager.merge(dao));
    }

    @Override
    public Integer getTotalRecords() {
        return null;
    }

    @Override
    public Optional<PostDAO> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(PostDAO postDAO) {
        return null;
    }

}

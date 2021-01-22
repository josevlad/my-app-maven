package ar.com.ada.second.online.maven.model.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    void save(T t);

    List<T> findAll(Integer from, Integer limit);

    Integer getTotalRecords();

    Optional<T> findById(Integer id);

    Boolean delete(T t);

}

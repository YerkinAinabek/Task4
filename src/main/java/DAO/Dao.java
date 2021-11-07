package DAO;

import Model.Notificator;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, ID> {

    void add (T o) throws SQLException;
    List<Notificator> getListOfNotificatorsById(int id) throws SQLException;

}
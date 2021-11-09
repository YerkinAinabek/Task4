package DAO;

import Model.Notificator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface Dao<T, ID> {

    void add (int a, LocalDate b);
    List<Notificator> getListOfNotificatorsById(int id);
    List<Integer> getIdsWithThreeLostTracks();
}
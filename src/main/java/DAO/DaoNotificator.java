package DAO;

import Model.Notificator;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoNotificator implements IDaoNotificator {
    private final Connection connection;

    private static final String INSERT_USERS_SQL = "INSERT INTO notify" + "  (id, day) VALUES " +
            " (?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id,day FROM notify WHERE id=?";

    public DaoNotificator(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Notificator notificator) throws SQLException {

        System.out.println(INSERT_USERS_SQL);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setInt(1, notificator.getId());
        preparedStatement.setDate(2, Date.valueOf(java.time.LocalDate.now()));
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Notificator> getListOfNotificatorsById(int id) throws SQLException {
        List<Notificator> notificatorList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            Notificator nf = new Notificator();
            nf.setId(result.getInt("id"));
            nf.setDay(result.getObject("date", LocalDate.class));
            notificatorList.add(nf);
        }

        return notificatorList;
    }



}



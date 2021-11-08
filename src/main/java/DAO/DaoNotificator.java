package DAO;

import Model.Notificator;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoNotificator implements IDaoNotificator {
    private final Connection connection;

    private static final String INSERT_USERS_SQL = "INSERT INTO tracking.notify" + "  (id,day) VALUES " +
            " (?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT id,day FROM tracking.notify WHERE id=?";
    private static final String USERS_WITH_3_FAILED_TRACKS = "SELECT id,day FROM tracking.notify WHERE day = ?";

    public DaoNotificator(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(int id, LocalDate day) throws SQLException {

        System.out.println(INSERT_USERS_SQL);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setDate(2, Date.valueOf(day));
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
            notificatorList.add(nf);
        }

        return notificatorList;
    }

    public



}



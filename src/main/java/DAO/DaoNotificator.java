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
    private static final String USERS_WITH_3_FAILED_TRACKS = "SELECT id\n" +
            "FROM (SELECT id, count( * ) count\n" +
            "\t\tFROM tracking.notify\n" +
            "\t\tWHERE day > (current_date - interval '3' day)\n" +
            "\t\tGROUP BY id) t\n" +
            "WHERE t.count > 2;";

    public DaoNotificator(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(int id, LocalDate day) {

        System.out.println(INSERT_USERS_SQL);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, Date.valueOf(day));
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Notificator> getListOfNotificatorsById(int id) {
        List<Notificator> notificatorList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Notificator nf = new Notificator();
                nf.setId(rs.getInt("id"));
                notificatorList.add(nf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificatorList;
    }

    @Override
    public List<Integer> getIdsWithThreeLostTracks() {
        List<Integer> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(USERS_WITH_3_FAILED_TRACKS);

            while (rs.next()) {
                int id = rs.getInt(1);
                list.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}



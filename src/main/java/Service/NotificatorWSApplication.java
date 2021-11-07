package Service;

import DAO.DaoNotificator;
import Model.Notificator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService (endpointInterface = "Service.INotificatorWSApplication")
public class NotificatorWSApplication implements INotificatorWSApplication {
    private DaoNotificator dao;
    // запрашиваем у сервис команды активные айди из списка
    @WebMethod (operationName = "getTeamData")
    @Override
    public List<Integer> getTeamData() {
        List <Integer> listTeam = null;


        return listTeam;
    }

    // запрашиваем у бухгалтера id, у которых есть отчёт на сегодня
    @WebMethod (operationName = "getTrackData")
    @Override
    public List<Integer> getTrackData() {
        List <Integer> listTracked = null;


        return listTracked;
    }

    // Сравниваем пользователей из списка команды со списком бухгалтера, возвращаем тех, у которых есть отчёты
    @Override
    public List<Integer> comparingData() {


    }

    // Добавляем пользователей и отчёты в БД
    @Override
    public void addNotification(Notificator notificator) {
        try {
            dao.add(notificator);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // отправляем на роутер пользователей, у которых нет отчётов за сегодня
    @WebMethod
    @Override
    public List<Notificator> sendToRouter() {

    }
}

package Service;

import DAO.DaoNotificator;
import Model.Notificator;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService (endpointInterface = "Service.INotificatorWSApplication")
public class NotificatorWSApplication implements INotificatorWSApplication {
    public DaoNotificator dao;
    public List<Integer> listTeam = null;
    public List<Integer> listTracked = null;
    public List<Integer> listCompared = null;
    public List<Notificator> listTrackedData = null;

    public NotificatorWSApplication(){

    }

    // запрашиваем у сервиса роутера активные айди из списка
    @WebMethod (operationName = "getTeamData")
    @Override
    public List<Integer> getTeamData() {

    // дай список айди

        return listTeam;
    }

    // запрашиваем у роутера id пользователей, у которых есть отчёт на сегодня
    @WebMethod (operationName = "getTrackedId")
    @Override
    public List<Integer> getTrackedId() {

    // дай список айди
        return listTracked;
    }

    @WebMethod (operationName = "getTrackedData")
    public List<Notificator> getTrackedData() {
        return listTrackedData;
    }

    // Сравниваем пользователей из списка команды со списком бухгалтера, возвращаем тех, у которых нет отчётов
    @Override
    public List<Integer> comparingData() {
        listTeam.removeAll(listTracked);
        listCompared = listTracked;
    return listCompared;
    }

    // Добавляем пользователей и отчёты в БД
    @Override
    public void addNotification(List<Notificator> listTrackedData) {
            for (Notificator notificator : listTrackedData) {
                try {
                    dao.add(notificator);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    }

    // отправляем на роутер пользователей, у которых нет отчётов за сегодня
    @WebMethod
    @Override
    public List<Integer> sendToRouter(List<Integer> listCompared) {
        return listCompared;
    }
}

package Service;

import DAO.DaoNotificator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebService(endpointInterface = "Service.INotificatorWSApplication")
public class NotificatorWSApplication implements INotificatorWSApplication {
    public DaoNotificator dao;
    public List<Integer> listTeam = null;
    public List<Integer> listTracked = null;
    public List<Integer> listIdDay = null;

    public NotificatorWSApplication() {
    }

    // запрашиваем у сервиса роутера активные айди из списка
    @WebMethod(operationName = "getTeamData")
    @Override
    public List<Integer> getTeamData() {

        // дай список айди
        return listTeam;
    }

    @WebMethod(operationName = "getTrackedData")
    @Override
    public List<Integer> getTrackedData() {
        // дай объект с id и датой
        return listIdDay;
    }

    // запрашиваем у роутера id пользователей, у которых есть отчёт на сегодня
    @WebMethod(operationName = "getTrackedId")
    @Override
    public List<Integer> getTrackedId() {
        // дай список айди
        return listTracked;
    }


    // Сравниваем пользователей из списка команды со списком бухгалтера, возвращаем тех, у которых нет отчётов
    @WebMethod(operationName = "comparingData")
    @Override
    public List<Integer> comparingData() {
        listTeam.removeAll(listTracked);
        return listTeam;
    }

    // Добавляем id пользователей, не оставлявших отчёты в БД
    @Override
    public void addNotification(List<Integer> listTeam) {
        comparingData();
        for (Integer temp : listTeam) {
            dao.add(temp, LocalDate.now());

        }
    }

    // Если не было трекинга 3 дня подряд, отправить лектору в тг оповещение
    @WebMethod(operationName = "getIdsForLector")
    @Override
    public List<Integer> getIdsForLector() {
        return dao.getIdsWithThreeLostTracks();
    }

}

package Service;

import DAO.DaoNotificator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@WebService(endpointInterface = "Service.INotificatorWSApplication")
public class NotificatorWSApplication implements INotificatorWSApplication {
    public DaoNotificator dao;
//    public List<Integer> listTeam = null;
//    public List<Integer> listTracked = null;
//    public List<Integer> listIdDay = null;

    public NotificatorWSApplication() {
    }


    // Сравниваем пользователей из списка команды со списком бухгалтера, возвращаем тех, у которых нет отчётов
    @WebMethod(operationName = "comparingData")
    @Override
    public List<Integer> comparingData(List<Integer> listTeam, List<Integer> listTracked) {
        listTeam.removeAll(listTracked);
        return listTeam;
    }

    // Добавляем id пользователей, не оставлявших отчёты в БД
    @Override
    public void addNotification(List<Integer> listTeam, List<Integer> listTracked) {
        comparingData(listTeam, listTracked);
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

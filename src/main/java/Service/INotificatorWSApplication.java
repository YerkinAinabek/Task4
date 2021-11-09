package Service;

import Model.Notificator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface INotificatorWSApplication {

    @WebMethod(operationName = "getTeamData")
    List<Integer> getTeamData();

    // запрашиваем у бухгалтера id, у которых есть отчёт на сегодня (
    @WebMethod(operationName = "getTrackedData")
    List<Integer> getTrackedData();

    // запрашиваем у роутера id пользователей, у которых есть отчёт на сегодня
    @WebMethod(operationName = "getTrackedId")
    List<Integer> getTrackedId();

    @WebMethod(operationName = "comparingData")
    List<Integer> comparingData();

    void addNotification(List<Integer> listCompared);

    // Если не было трекинга 3 дня подряд, отправить лектору в тг оповещение
    @WebMethod(operationName = "getIdsForLector")
    List<Integer> getIdsForLector();
}

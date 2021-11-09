package Service;

import Model.Notificator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface INotificatorWSApplication {

    @WebMethod(operationName = "comparingData")
    List<Integer> comparingData(List<Integer> listTeam, List<Integer> listTracked);

    void addNotification(List<Integer> listCompared, List<Integer> listTracked);

    // Если не было трекинга 3 дня подряд, отправить лектору в тг оповещение
    @WebMethod(operationName = "getIdsForLector")
    List<Integer> getIdsForLector();
}

package Service;

import Model.Notificator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface INotificatorWSApplication {

    @WebMethod
    List<Integer> getTeamData();

    // запрашиваем у бухгалтера id, у которых есть отчёт на сегодня (
    @WebMethod
    List<Integer> getTrackData();

    //@WebMethod
    List<Integer> comparingData();


    void addNotification(Notificator notificator);

    @WebMethod
    List<Notificator> sendToRouter();
}

package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
public class Notificator {

    @XmlElement
    public int id;

    @XmlElement
    public LocalDate day;

    public Notificator(){}

    public Notificator(int id) {
        this.id = id;
    }

    public Notificator(int id, LocalDate day) {
        this.id = id;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notificator notificator = (Notificator) o;
        return Objects.equals(day, notificator.day) && Objects.equals(id, notificator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day);
    }
}

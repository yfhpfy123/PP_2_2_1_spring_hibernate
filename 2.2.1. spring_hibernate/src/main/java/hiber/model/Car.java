package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String model;

    @Column
    private int series;

    public Car () {}

    public Car(String model, int series) {
        this.series = series;
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return String.format("[model = %s, series = %d]", model, series);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, series);
    }
}

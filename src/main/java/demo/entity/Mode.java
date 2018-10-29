package demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author V
 */

@Entity
@Table(name = "MODE", uniqueConstraints = @UniqueConstraint(columnNames = {"WM_ID", "NAME"}))
public class Mode {

    // -----------------------------------------------------------------
    // Instance fields
    // -----------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WM_ID", nullable = false)
    @JsonIgnore
    private WashingMachine washingMachine;

    @Column(nullable = false)
    private String name;

    private int temperature;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    // required by JPA
    public Mode() {
    }

    public Mode(String name, int temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    // -----------------------------------------------------------------
    // instance methods
    // -----------------------------------------------------------------

    public String getName() {
        return name;
    }

    public int getTemperature() {
        return temperature;
    }

    public WashingMachine getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }
}

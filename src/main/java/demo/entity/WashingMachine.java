package demo.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author V
 */
@Entity
@Table(name = "WASHING_MACHINES")
public class WashingMachine {

    // -----------------------------------------------------------------
    // Instance fields
    // -----------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "washingMachine")
    private Collection<Mode> modes;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

     // required by JPA
    public WashingMachine() {
    }

    public WashingMachine(String name, String state, Collection<Mode> modes) {
        this.name = name;
        this.state = state;
        this.modes = modes;
    }

    // -----------------------------------------------------------------
    // instance methods
    // -----------------------------------------------------------------

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Mode> getModes() {
        return modes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

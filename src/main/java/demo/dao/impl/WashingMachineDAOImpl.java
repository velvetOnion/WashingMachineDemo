package demo.dao.impl;

import demo.state.State;
import demo.entity.Mode;
import demo.entity.WashingMachine;
import demo.dao.WashingMachineDAO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author V
 */
@Repository
@Transactional
public class WashingMachineDAOImpl implements WashingMachineDAO {

    // -----------------------------------------------------------------
    // Instance fields
    // -----------------------------------------------------------------

    @PersistenceContext
    private EntityManager entityManager;

    // -----------------------------------------------------------------
    // instance methods
    // -----------------------------------------------------------------

    @Override
    public List<WashingMachine> getWashingMachines() {
        return entityManager.unwrap(Session.class).createCriteria(WashingMachine.class).list();
    }

    @Override
    public WashingMachine getWashingMachine(long id) throws Exception {
        return Optional.of(entityManager.find(WashingMachine.class, id))
                .orElseThrow(() -> new Exception("No washing machine with id = " + id));
    }

    @Override
    public String addWashingMachine(WashingMachine machine) throws Exception {
        Arrays.stream(State.values()).filter(s -> s.name().equals(machine.getState())).findFirst()
                .orElseThrow(()-> new Exception("Unknown state"));
        Collection<Mode> modes = machine.getModes();
        if (modes == null || modes.isEmpty())
            throw new Exception("Washing machine cannot be without modes");
        // detached entities came from json
        modes.forEach(m -> m.setWashingMachine(machine));
        entityManager.merge(machine);
        return "Washing machine successfully added";
    }

    @Override
    public String deleteWashingMachine(long id) throws Exception {
        WashingMachine washingMachine = getWashingMachine(id);
        if (!washingMachine.getState().equals(State.READY.toString())) {
            throw new Exception("WashingMachine with id = " + id + " is not finished the current mode. " +
                    "Please stop it and try to delete it afterwards");
        }
        entityManager.remove(washingMachine);
        return "WashingMachine with id = " + id + " successfully deleted";
    }


    @Override
    public String run(long id, String modeName) throws Exception {
        WashingMachine washingMachine = getWashingMachine(id);
        if (State.RUNNING.toString().equals(washingMachine.getState())) {
            throw new Exception("Washing machine is already running");
        }
        washingMachine.getModes().stream().filter(x -> modeName.equals(x.getName())).findAny()
                .orElseThrow(() -> new Exception("WashingMachine with id = " + id
                        + " doesn't have the mode " + modeName));
        washingMachine.setState(State.RUNNING.toString());
        entityManager.flush();
        return "Started";
    }

    @Override
    public String stop(long id) throws Exception {
        WashingMachine washingMachine = getWashingMachine(id);
        if (State.READY.toString().equals(washingMachine.getState())) {
            throw new Exception("Washing machine is already stopped");
        }
        washingMachine.setState(State.READY.toString());
        entityManager.flush();
        return "Stopped";
    }

    @Override
    public String pause(long id) throws Exception {
        WashingMachine washingMachine = getWashingMachine(id);
        if (!State.RUNNING.toString().equals(washingMachine.getState())) {
            throw new Exception("Washing machine cannot be paused");
        }
        washingMachine.setState(State.PAUSED.toString());
        entityManager.flush();
        return "Paused";
    }
}
package demo.dao;

import demo.entity.WashingMachine;

import java.util.List;

/**
 * @author V
 */
public interface WashingMachineDAO {

    List<WashingMachine> getWashingMachines();

    WashingMachine getWashingMachine(long id) throws Exception;

    String addWashingMachine(WashingMachine machine) throws Exception;

    String deleteWashingMachine(long id) throws Exception;

    String run(long id, String modeName) throws Exception;

    String stop(long id) throws Exception;

    String pause(long id) throws Exception;
}

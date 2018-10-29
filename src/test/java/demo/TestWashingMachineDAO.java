package demo;

import demo.dao.impl.WashingMachineDAOImpl;
import demo.entity.Mode;
import demo.entity.WashingMachine;
import demo.state.State;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author V
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWashingMachineDAO {

    // -----------------------------------------------------------------
    // Instance fields
    // -----------------------------------------------------------------

    @Autowired
    WashingMachineDAOImpl machineDAO;

    // -----------------------------------------------------------------
    // instance methods
    // -----------------------------------------------------------------

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testAddEmptyWashingMachine() throws Exception {
        WashingMachine washingMachine = new WashingMachine("TestMachine", State.READY.toString(), Collections.emptyList());
        machineDAO.addWashingMachine(washingMachine);
        List<WashingMachine> washingMachines = machineDAO.getWashingMachines();
        Assert.assertEquals(washingMachine.getName(), washingMachines.get(washingMachines.size()-1).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddWashingMachine() throws Exception {
        Mode mode = new Mode("Mode1", 100);
        WashingMachine washingMachine = new WashingMachine("TestMachine", State.READY.toString(),
                Arrays.asList(mode));
        machineDAO.addWashingMachine(washingMachine);
        List<WashingMachine> washingMachines = machineDAO.getWashingMachines();
        Assert.assertEquals(washingMachine.getName(), washingMachines.get(washingMachines.size()-1).getName());
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testAddWashingMachineWithUnknownState() throws Exception {
        Mode mode = new Mode("Mode1", 100);
        WashingMachine washingMachine = new WashingMachine("TestMachine", "wtf",
                Arrays.asList(mode));
        machineDAO.addWashingMachine(washingMachine);
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testDeleteWashingMachine() throws Exception {
        WashingMachine washingMachine = new WashingMachine("TestMachine", State.READY.toString(), Collections.emptyList());
        int count = machineDAO.getWashingMachines().size();
        machineDAO.addWashingMachine(washingMachine);
        try {
            machineDAO.deleteWashingMachine(1);
            Assert.assertEquals(count, machineDAO.getWashingMachines().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testDeleteNotExistingWashingMachine() throws Exception {
        machineDAO.deleteWashingMachine(-1);
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testDeleteRunningWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        machine.setState(State.RUNNING.toString());
        machineDAO.deleteWashingMachine(1);
    }

    @Test
    @Transactional
    @Rollback
    public void testRunWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        Mode mode = machine.getModes().iterator().next();
        machine.setState(State.READY.toString());
        machineDAO.run(1, mode.getName());
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testRunNotExistingWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        Mode mode = machine.getModes().iterator().next();
        machine.setState(State.READY.toString());
        machineDAO.run(-1, mode.getName());
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testRunRunningWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        Mode mode = machine.getModes().iterator().next();
        machine.setState(State.RUNNING.toString());
        machineDAO.run(1, mode.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testStopWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        Mode mode = machine.getModes().iterator().next();
        machineDAO.run(1, mode.getName());
        machineDAO.stop(1);
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testStopNotExistingWashingMachine() throws Exception {
        machineDAO.stop(-1);
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testStopStoppedWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        machine.setState(State.READY.toString());
        machineDAO.stop(1);
    }

    @Test
    @Transactional
    @Rollback
    public void testPauseWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        Mode mode = machine.getModes().iterator().next();
        machineDAO.run(1, mode.getName());
        machineDAO.pause(1);
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testPauseNotExistingWashingMachine() throws Exception {
        machineDAO.pause(-1);
    }

    @Test(expected = Exception.class)
    @Transactional
    @Rollback
    public void testPauseStoppedWashingMachine() throws Exception {
        WashingMachine machine = machineDAO.getWashingMachine(1);
        machine.setState(State.READY.toString());
        machineDAO.pause(1);
    }
}

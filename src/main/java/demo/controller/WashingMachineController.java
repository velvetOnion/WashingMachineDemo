package demo.controller;

import demo.entity.WashingMachine;
import demo.dao.impl.WashingMachineDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author V
 */
@RestController
public class WashingMachineController {

    // -----------------------------------------------------------------
    // Instance fields
    // -----------------------------------------------------------------

    @Autowired
    protected WashingMachineDAOImpl machineDAO;

    // -----------------------------------------------------------------
    // instance methods
    // -----------------------------------------------------------------

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<WashingMachine> getWashingMachines() {
       return machineDAO.getWashingMachines();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public WashingMachine getWashingMachine(@PathVariable("id") long id) throws Exception {
        return machineDAO.getWashingMachine(id);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public String addWashingMachine(@RequestBody WashingMachine machine) throws Exception {
       return machineDAO.addWashingMachine(machine);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteWashingMachine(@PathVariable("id") long id) throws Exception {
      return machineDAO.deleteWashingMachine(id);
    }

    @RequestMapping(value = "/run/{id}", method = RequestMethod.PUT)
    public String run(@PathVariable("id") long id, @RequestParam("mode") String modeName) throws Exception {
        return machineDAO.run(id, modeName);
    }

    @RequestMapping(value = "/stop/{id}", method = RequestMethod.PUT)
    public String stop(@PathVariable("id") long id) throws Exception {
       return machineDAO.stop(id);
    }

    @RequestMapping(value = "/pause/{id}", method = RequestMethod.PUT)
    public String pause(@PathVariable("id") long id) throws Exception {
       return machineDAO.pause(id);
    }
}

package demo;

import demo.dao.impl.WashingMachineDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author V
 */
@SpringBootApplication
public class WashingMachineApp {

    // -----------------------------------------------------------------
    // Instance fields
    // -----------------------------------------------------------------

    @Autowired
    WashingMachineDAOImpl service;

    // -----------------------------------------------------------------
    // instance methods
    // -----------------------------------------------------------------

    public static void main(String[] args) {
        SpringApplication.run(WashingMachineApp.class, args);
    }
}

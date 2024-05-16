package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.CatalogCreator;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.SystemCreator;
import se.kth.iv1350.pos.view.View;

/**
 * Contains the <code>main</code> method. Initializes and coordinates the
 * startup of the application components.
 */
public class Main {

    /**
     * Starts the application. Initializes the necessary system components such
     * as the accounting system, inventory system, and printer. It also creates
     * the controller responsible for managing the interaction between the view
     * and the model.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {

        SystemCreator systemCreator = new SystemCreator();
        CatalogCreator catalogCreator = new CatalogCreator();
        Printer printer = new Printer();

        Controller controller = new Controller(systemCreator, catalogCreator, printer);
        View view = new View(controller);

        view.sampleExecution();
    }
}

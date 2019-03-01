package group244.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkTest {

    @Test
    public void addComputer() {
        Network network = new Network();
        Computer computer1 = new Computer("Aloha", new OS("Linux", 0.5));
        network.addComputer(computer1);
        assertEquals(network.showStatus(), "Aloha: not infected\n");
    }

    @Test
    public void addConnection() throws NoSuchComputerException {
        Network network = new Network();
        Computer computer1 = new Computer("Aloha", new OS("Linux", 0));
        Computer computer2 = new Computer("bla", new OS("Wind", 1));
        network.addComputer(computer1);
        network.addComputer(computer2);
        network.addConnection(computer2, computer1);
        computer1.setInfected();
        network.turn();
        assertFalse(computer2.isInfected());
        network.turn();
        assertTrue(computer2.isInfected());
    }


    @Test
    public void numberOfInfected() throws NoSuchComputerException {
        Network network = new Network();
        Computer computer1 = new Computer("Aloha", new OS("Linux", 0));
        Computer computer2 = new Computer("bla", new OS("Wind", 1));
        Computer computer3 = new Computer("vah", new OS("Mac", 1));
        network.addComputer(computer1);
        network.addComputer(computer2);
        network.addComputer(computer3);
        network.addConnection(computer2, computer1);
        network.addConnection(computer3, computer2);
        computer1.setInfected();
        network.turn();
        assertEquals(network.numberOfInfected(), 1);
        network.turn();
        network.turn();
        assertEquals(network.numberOfInfected(), 3);
    }

    @Test
    public void turn() throws NoSuchComputerException {
        Network network = new Network();
        Computer computer1 = new Computer("Aloha", new OS("Linux", 0));
        Computer computer2 = new Computer("bla", new OS("Wind", 1));
        Computer computer3 = new Computer("vah", new OS("Mac", 1));
        network.addComputer(computer1);
        network.addComputer(computer2);
        network.addComputer(computer3);
        network.addConnection(computer2, computer1);
        network.addConnection(computer3, computer2);

        computer1.setInfected();
        network.turn();
        assertFalse(computer2.isInfected());
        assertFalse(computer3.isInfected());

        network.turn();
        assertTrue(computer2.isInfected());
        assertFalse(computer3.isInfected());

        network.turn();
        assertTrue(computer3.isInfected());
    }

    @Test
    public void showStatus() throws NoSuchComputerException {
        Network network = new Network();
        Computer computer1 = new Computer("Aloha", new OS("Linux", 0.5));
        Computer computer2 = new Computer("bla", new OS("Wind", 0.1));
        Computer computer3 = new Computer("vah", new OS("Mac", 0.2));
        network.addComputer(computer1);
        network.addComputer(computer2);
        network.addComputer(computer3);
        network.addConnection(computer2, computer1);
        network.addConnection(computer3, computer2);
        computer1.setInfected();
        assertEquals(network.showStatus(), "Aloha: infected\nbla: not infected\nvah: not infected\n");
    }
}
package group244.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    @Test
    public void linkWithNetwork() {
        Computer computer = new Computer("aloha", new OS("non", 0.1));
        Network network = new Network();
        network.addComputer(computer);
        assertEquals(network.showStatus(), "aloha: not infected\n");
    }

    @Test
    public void isInfected() {
        Computer computer = new Computer("aloha", new OS("non", 0.1));
        assertFalse(computer.isInfected());
        computer.setInfected();
        assertTrue(computer.isInfected());
    }

    @Test
    public void getName() {
        Computer computer = new Computer("aloha", new OS("non", 0.1));
        assertEquals(computer.getName(), "aloha");
    }

    @Test
    public void turn() {
        float prob = (float) 0.5;
        int attempts = 1000;
        int success = 0;
        for (int i = 0; i < attempts; i++) {
            Computer computer = new Computer("aloha", new OS("non", prob));
            computer.inDangerOfInfection();
            computer.turn();
            if (computer.isInfected()) {
                success += 1;
            }
        }

        assertEquals((float) success/attempts, prob, 0.1);
    }
}
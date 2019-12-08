package group244.shervashidze;

import org.junit.Test;

import java.util.Random;

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
        Generator generator = new Generator();

        Computer computer = new Computer("aloha", new OS("non", prob), generator);
        computer.inDangerOfInfection();
        computer.turn();
        assertFalse(computer.isInfected());

        Computer computer2 = new Computer("aloha", new OS("non", prob), generator);
        computer2.inDangerOfInfection();
        computer2.turn();
        assertTrue(computer2.isInfected());
    }

    private class Generator extends Random {
        private int counter = 1;
        @Override
        public float nextFloat() {
            counter++;
            if ((counter - 1) % 2 == 0) {
                return (float) 0.4;
            } else {
                return (float) 0.6;
            }
        }
    }
}
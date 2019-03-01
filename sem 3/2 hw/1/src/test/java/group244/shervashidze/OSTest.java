package group244.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class OSTest {

    @Test
    public void getName() {
        OS newOs = new OS("aloha", 0);
        assertEquals(newOs.getName(), "aloha");
    }

    @Test
    public void getProbability() {
        OS newOs = new OS("aloha", 0.4);
        assertEquals(newOs.getProbability(), 0.4, 0.0001);
    }
}
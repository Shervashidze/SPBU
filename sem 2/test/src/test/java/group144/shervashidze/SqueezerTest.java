package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class SqueezerTest {

    @Test
    public void squeezeEasy() {
        byte[] array = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 5, (byte) 5};
        byte[] secondArray = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 5};
        assertArrayEquals(new byte[]{(byte) 3, (byte) 0, (byte) 2, (byte) 5}, Squeezer.squeeze(array));
        assertArrayEquals(new byte[]{(byte) 3, (byte) 0, (byte) 1, (byte) 5}, Squeezer.squeeze(secondArray));
    }

    @Test
    public void deSqueezeEasy() {
        byte[] array = new byte[]{(byte) 3, (byte) 0, (byte) 2, (byte) 5};
        byte[] secondArray = new byte[]{(byte) 1, (byte) 7};
        assertArrayEquals(new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 5, (byte) 5}, Squeezer.deSqueeze(array));
        assertArrayEquals(new byte[]{(byte) 7}, Squeezer.deSqueeze(secondArray));
    }

    @Test
    public void nullArraysTest() {
        byte[] array = null;
        assertArrayEquals(null, Squeezer.squeeze(array));
        assertArrayEquals(null, Squeezer.deSqueeze(array));
    }

    @Test
    public void mixedTest() {
        int size = 10;
        byte[] array = new byte[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i * size + j] = (byte) i;
            }
        }

        assertArrayEquals(array, Squeezer.deSqueeze(Squeezer.squeeze(array)));
    }
}
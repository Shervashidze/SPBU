package group144.shervashidze;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {
    @Test(expected = ElementAlreadyAddedException.class)
    public void appendTest() throws ListAppendException {
        UniqueList<String> myList = new UniqueList<>();
        myList.add("kek");
        myList.add("kek");
    }

}
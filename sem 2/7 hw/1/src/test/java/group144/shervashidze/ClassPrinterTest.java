package group144.shervashidze;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClassPrinterTest {

    @Test
    public void OneIntClassTest() {
        OneInt example = new OneInt(3);
        assertEquals("public class OneInt extends Object {\n\tprivate int first;\n" + "\n\tOneInt(int arg0);\n"
                + "\n\tpublic void divide();\n" + "\n}", ClassPrinter.reflect(example.getClass()));
    }

    @Test
    public void StackTest() {
        ArrayStack example = new ArrayStack();
        assertEquals("public class ArrayStack<T> extends Object implements Stack<T> {\n" +
                        "\tprivate int length;\n" +
                        "\tprivate int arraySize;\n" +
                        "\tprivate Object[] values;\n" +
                        "\n" +
                        "\tpublic ArrayStack();\n" +
                        "\n" +
                        "\tpublic boolean isEmpty();\n" +
                        "\tpublic class java.lang.Object pop() throws group144.shervashidze.EmptyStackException;\n" +
                        "\tpublic void push(class java.lang.Object arg0);\n" +
                        "\tpublic int getSize();\n" +
                        "\tprivate void rewrite(int arg0);\n" +
                        "\n}",
                ClassPrinter.reflect(example.getClass()));
    }
}
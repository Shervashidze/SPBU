package group144.shervashidze;

import java.io.*;

public class Serialize {

    /**
     * Serialize.
     *
     * @param out stream to write object(serializable) in.
     * @throws IOException when we cant write there.
     */
    public static void serialize(Serializable object, OutputStream out) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(object);
        outputStream.close();
    }

    /**
     * Deserialize.
     *
     * @param in - stream to take class(serializable).
     * @throws IOException when we cant read this stream.
     * @throws ClassNotFoundException when we cant find class which serialized in this stream.
     */
    public static Serializable deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(in);
        return (Serializable)input.readObject();
    }
}

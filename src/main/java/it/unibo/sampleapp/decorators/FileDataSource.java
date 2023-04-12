package it.unibo.sampleapp.decorators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Class to do things.
 */
public class FileDataSource implements DataSource {
    private final String name;

    /**
     * Method to set the file.
     * 
     * @param name is the name of the file.
     */
    public FileDataSource(final String name) {
        this.name = name;
    }

    /**
     * Method to write the data.
     * 
     * @param data is what to write.
     */
    @Override
    public void writeData(final String data) {
        final File file = new File(name);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // NOPMD
        }
    }

    /**
     * Method to read the file.
     */
    @Override
    public String readData() {
        char[] buffer = null;
        final File file = new File(name);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); // NOPMD
        }

        return new String(buffer);

    }
}

package it.unibo.sampleapp.decorators;

/**
 * Interface to manage datasources.
 */
public interface DataSource {
    /**
     * Method to write the data.
     * 
     * @param data is what to write.
     */
    void writeData(String data);

    /**
     * Method to read the data.
     * 
     * @return a string
     */
    String readData();
}

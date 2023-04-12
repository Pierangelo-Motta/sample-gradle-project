package it.unibo.sampleapp.decorators;

public interface DataSource {
    void writeData(String data);

    String readData();
}

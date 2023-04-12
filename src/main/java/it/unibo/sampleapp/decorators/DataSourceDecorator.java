package it.unibo.sampleapp.decorators;

/**
 * decorator class.
 */
public class DataSourceDecorator implements DataSource {
    private final DataSource wrappee;

    /**
     * class constructor.
     * 
     * @param source is the source.
     */
    DataSourceDecorator(final DataSource source) {
        this.wrappee = source;
    }

    /**
     * class writer.
     * 
     * @param data is the data.
     */
    @Override
    public void writeData(final String data) {
        wrappee.writeData(data);
    }

    /**
     * class readere.
     * 
     * @return String value.
     */
    @Override
    public String readData() {
        return wrappee.readData();
    }
}

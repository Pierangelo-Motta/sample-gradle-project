package it.unibo.sampleapp;

import it.unibo.sampleapp.decorators.CompressionDecorator;
import it.unibo.sampleapp.decorators.DataSource;
import it.unibo.sampleapp.decorators.DataSourceDecorator;
import it.unibo.sampleapp.decorators.EncryptionDecorator;
import it.unibo.sampleapp.decorators.FileDataSource;

/**
 * Javadoc comment.
 */
public final class Demo {

    /**
     * Javadoc comment.
     */
    private Demo() {
    }

    /**
     * Javadoc comment.
     * 
     * @param args not used.
     */
    public static void main(final String[] args) {
        final String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        final DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out\\OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        final DataSource plain = new FileDataSource("out\\OutputDemo.txt");

        System.out.println("- Input ----------------"); // NOPMD
        System.out.println(salaryRecords); // NOPMD
        System.out.println("- Encoded --------------"); // NOPMD
        System.out.println(plain.readData()); // NOPMD
        System.out.println("- Decoded --------------"); // NOPMD
        System.out.println(encoded.readData()); // NOPMD
    }
}

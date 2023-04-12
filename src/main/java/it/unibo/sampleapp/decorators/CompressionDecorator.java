package it.unibo.sampleapp.decorators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * CompressionDecorator class.
 */
public class CompressionDecorator extends DataSourceDecorator {
    private static final int BUFFER = 512;
    private static final int LEV = 6;
    private int compLevel = LEV;

    /**
     * CompressionDecorator builder.
     * 
     * @param source is the source.
     */
    public CompressionDecorator(final DataSource source) {
        super(source);
    }

    /**
     * @return compLevel value of the compression level.
     */
    public int getCompressionLevel() {
        return compLevel;
    }

    /**
     * set the compression level.
     * 
     * @param value is the value to sbe set.
     */
    public void setCompressionLevel(final int value) {
        compLevel = value;
    }

    /**
     * Write the data.
     * 
     * @param data is the value to be written.
     */
    @Override
    public void writeData(final String data) {
        super.writeData(compress(data));
    }

    /**
     * Read the data.
     */
    @Override
    public String readData() {
        return decompress(super.readData());
    }

    /**
     * compress the data.
     * 
     * @param stringData is the value to be compressed.
     */
    private String compress(final String stringData) {
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(BUFFER);
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
            dos.write(data);
            dos.close();
            bout.close();
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }

    private String decompress(final String stringData) {
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            int b;
            while ((b = iin.read()) != -1) {
                bout.write(b);
            }
            in.close();
            iin.close();
            bout.close();
            return new String(bout.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }
}

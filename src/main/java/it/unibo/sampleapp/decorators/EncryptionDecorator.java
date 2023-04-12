package it.unibo.sampleapp.decorators;

import java.util.Base64;

/**
 * encryption class.
 */
public class EncryptionDecorator extends DataSourceDecorator {

    /**
     * Class constructor.
     * 
     * @param source is the source.
     */
    public EncryptionDecorator(final DataSource source) {
        super(source);
    }

    /**
     * writeData instructions...
     * 
     * @param data is what to write.
     */
    @Override
    public void writeData(final String data) {
        super.writeData(encode(data));
    }

    /**
     * read instructions...
     * 
     * @return the decoded string.
     */

    @Override
    public String readData() {
        return decode(super.readData());
    }

    /**
     * encode method.
     * 
     * @param data is what to encode.
     * @return the encoded string.
     */

    private String encode(final String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * decode method.
     * 
     * @param data is what to decode.
     * @return the decoded string.
     */
    private String decode(final String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}

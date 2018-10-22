package Engine.Util.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
/** This class is used to separate the printStream in 2.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 */
public class DoublePrintStream extends PrintStream {
	/** The secondary print stream.
	 */
    private final PrintStream second;

    /** Create a DoublePrintStream.
     * 
     * @param main The main PrintStream.
     * @param second The secondary PrintStream.
     */
    public DoublePrintStream(OutputStream main, PrintStream second) {
        super(main);
        this.second = second;
    }


    @Override
    /**Closes the main stream but not the secondary.
     */
    public void close() {
        // just for documentation
        super.close();
    }
    
    
    @Override
    /** Flush both streams.
     */
    public void flush() {
        super.flush();
        second.flush();
    }

    @Override
    /** Write to both streams.
     * 
     * @param buf The buffer to write.
     * @param off The buffer offset.
     * @param buf The length of the buffer.
     */
    public void write(byte[] buf, int off, int len) {
        super.write(buf, off, len);
        second.write(buf, off, len);
    }

    @Override
    /** Write to both streams.
     * 
     * @param b The integer to write.
     */
    public void write(int b) {
        super.write(b);
        second.write(b);
    }

    @Override
    /** Write to both streams.
     * 
     * @param b An array of bytes to write.
     */
    public void write(byte[] b) throws IOException {
        super.write(b);
        second.write(b);
    }
}

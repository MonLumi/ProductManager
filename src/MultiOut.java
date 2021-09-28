import java.io.IOException;
import java.io.PrintStream;

public class MultiOut extends PrintStream {
    PrintStream second;

    public MultiOut(PrintStream out, PrintStream second) {
        super(out);
        this.second = second;
    }

    @Override
    public void write(int b) {
        super.write(b);
        second.write(b);
    }

    @Override
    public void write(byte[] buf) throws IOException {
        super.write(buf);
        second.write(buf);
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        super.write(buf, off, len);
        second.write(buf, off, len);
    }

    @Override
    public void flush() {
        super.flush();
        second.flush();
    }

    @Override
    public void close() {
        super.close();
        second.close();
    }
}

package demo.file;

import org.apache.mina.core.file.FilenameFileRegion;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by peter on 14-12-7.
 */
public class FileRequest extends FilenameFileRegion {
    String message;

    public FileRequest(File file, FileChannel channel) throws IOException {
        super(file, channel);
    }

    public FileRequest(File file, FileChannel channel, long position, long remainingBytes) {
        super(file, channel, position, remainingBytes);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

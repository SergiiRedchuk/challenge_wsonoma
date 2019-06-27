package challenge.wsonoma.ranges.reader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileZipRangesIterator implements Iterator<String[]>, AutoCloseable {

  private static final String DELIMITERS = "[ ,]+";

  private LineIterator it;

  public FileZipRangesIterator(String fileLocation) throws IOException {
    File file = new File(fileLocation);
    this.it = FileUtils.lineIterator(file, "UTF-8");
  }

  @Override
  public boolean hasNext() {
    return it.hasNext();
  }

  @Override
  public String[] next() {
    if (!it.hasNext()) {
      throw new NoSuchElementException();
    }
    return it.nextLine().trim().split(DELIMITERS);
  }

  @Override
  public void close() throws IOException {
    it.close();
  }
}

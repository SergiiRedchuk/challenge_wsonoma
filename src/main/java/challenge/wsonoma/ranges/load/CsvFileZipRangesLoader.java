package challenge.wsonoma.ranges.load;

import challenge.wsonoma.ranges.validator.SimpleZipRangeValidator;
import challenge.wsonoma.ranges.validator.ZipRangeValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileZipRangesLoader implements ZipRangesLoader {

  private String fileLocation;

  public CsvFileZipRangesLoader(String fileLocation) {
    this.fileLocation = fileLocation;
  }

  @Override
  public Integer[][] loadRanges() throws IOException {
    ZipRangeValidator rangeValidator = new SimpleZipRangeValidator();
    List<Integer[]> zipRanges = new ArrayList<>();
    try (FileZipRangesIterator it = new FileZipRangesIterator(fileLocation)) {
      while (it.hasNext()) {
        String[] range = it.next();
        if (rangeValidator.isRangeValid(range)) {
          zipRanges.add(new Integer[]{Integer.valueOf(range[0]), Integer.valueOf(range[1])});
        } else {
          System.err.printf("Skipping invalid Zip Code Range: [%s, %s]%n",
              range.length > 0 ? range[0] : "",
              range.length > 1 ? range[1] : "");
        }
      }
    }
    return zipRanges.toArray(new Integer[][]{});
  }
}

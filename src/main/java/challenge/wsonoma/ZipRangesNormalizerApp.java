package challenge.wsonoma;

import challenge.wsonoma.ranges.normalizer.ZipRangesNormalizer;
import challenge.wsonoma.ranges.normalizer.ZipRangesNormalizerImpl;
import challenge.wsonoma.ranges.reader.FileZipRangesIterator;
import challenge.wsonoma.ranges.validator.SimpleZipRangeValidator;
import challenge.wsonoma.ranges.validator.ZipRangeValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower
 * bounds), provide an algorithm that produces the minimum number of ranges required to represent
 * the same restrictions as the input.
 *
 * @author Sergii Redchuk
 */
public class ZipRangesNormalizerApp {

  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      System.err.println("Usage: java -jar .../zip_ranges_normalizer-1.0-dist.jar <PATH_TO_CSV_FILE>");
      System.exit(1);
    }

    // read CSV file into zipRanges
    ZipRangeValidator rangeValidator = new SimpleZipRangeValidator();
    List<Integer[]> zipRanges = new ArrayList<>();
    try (FileZipRangesIterator it = new FileZipRangesIterator(args[0])) {
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

    // normalize zipRanges
    ZipRangesNormalizer rangesNormalizer = new ZipRangesNormalizerImpl();
    Integer[][] normalizedZipRanges = rangesNormalizer
        .normalizeRanges(zipRanges.toArray(new Integer[][]{}));

    // print normalized zipRanges to stdout
    Arrays.stream(normalizedZipRanges)
        .forEach(range -> System.out.printf("%d, %d%n", range[0], range[1]));
  }
}

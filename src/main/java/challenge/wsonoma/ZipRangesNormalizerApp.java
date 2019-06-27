package challenge.wsonoma;

import challenge.wsonoma.ranges.load.CsvFileZipRangesLoader;
import challenge.wsonoma.ranges.load.ZipRangesLoader;
import challenge.wsonoma.ranges.normalizer.ZipRangesNormalizer;
import challenge.wsonoma.ranges.normalizer.ZipRangesNormalizerImpl;
import java.io.IOException;
import java.util.Arrays;

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

    // loadRanges CSV file
    ZipRangesLoader loader = new CsvFileZipRangesLoader(args[0]);
    Integer[][] zipRanges = loader.loadRanges();

    // normalize zipRanges
    ZipRangesNormalizer rangesNormalizer = new ZipRangesNormalizerImpl();
    Integer[][] normalizedZipRanges = rangesNormalizer.normalizeRanges(zipRanges);

    // print normalized zipRanges to stdout
    Arrays.stream(normalizedZipRanges)
        .forEach(range -> System.out.printf("%d, %d%n", range[0], range[1]));
  }
}

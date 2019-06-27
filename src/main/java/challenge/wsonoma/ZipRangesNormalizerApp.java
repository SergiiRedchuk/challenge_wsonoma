package challenge.wsonoma;

import challenge.wsonoma.rangenorm.ZipRangesNormalizer;
import challenge.wsonoma.rangenorm.ZipRangesNormalizerImpl;
import java.util.Arrays;

/**
 * Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds),
 * provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.
 *
 * @author Sergii Redchuk
 */
public class ZipRangesNormalizerApp {

  public static void main(String[] args) {
    ZipRangesNormalizer rangesNormalizer = new ZipRangesNormalizerImpl();
    Integer[][] normalizedZipRanges = rangesNormalizer.normalizeRanges(new Integer[][]{
        {94133, 94133}, {94200, 94299}, {94226, 94399}
    });
    Arrays.stream(normalizedZipRanges)
        .forEach(range -> System.out.printf("[%d, %d]%n", range[0], range[1]));
  }
}

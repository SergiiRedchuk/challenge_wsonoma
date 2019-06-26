package challenge.wsonoma;

import java.util.Arrays;
import java.util.Comparator;
import javax.xml.ws.Holder;

/**
 * Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds),
 * provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.
 *
 * @author Sergii Redchuk
 */
public final class ZipRangesNormalizer {

  private ZipRangesNormalizer() {
    // util class
  }

  /**
   * this comparator assumes that all input ranges are valid (e.g. rangeStart <= rangeEnd always)
   */
  private static final Comparator<Integer[]> VALID_RANGES_COMPARATOR =
      (a, b) -> a[0] < b[0] ? -1 : (a[0] > b[0] ? 1 : Integer.compare(a[1], b[1]));

  /**
   * throws IllegalArgumentException if an invalid range is met (e.g. rangeStart > rangeEnd)
   */
  private static final Comparator<Integer[]> RANGES_COMPARATOR = (a, b) -> {
    if (a[0] > a[1] || b[0] > b[1]) {
      throw new IllegalArgumentException("Invalid range");
    }
    return a[0] < b[0] ? -1 : (a[0] > b[0] ? 1 : Integer.compare(a[1], b[1]));
  };

  /**
   * Assume that all input ranges are valid and don't check if rangeStart <= rangeEnd.
   * Use only when sure if input is valid.
   *
   * @param zipRanges array
   * @return normalized ranges
   */
  public static Integer[][] normalizeValidRanges(Integer[][] zipRanges) {
    return normalizeRanges(zipRanges, VALID_RANGES_COMPARATOR);
  }

  /**
   * Checks every input range if it is valid (rangeStart <= rangeEnd)
   * and throws IllegalArgumentException when finds an invalid range.
   *
   * @param zipRanges array
   * @return ranges
   */
  public static Integer[][] normalizeRanges(Integer[][] zipRanges) {
    return normalizeRanges(zipRanges, RANGES_COMPARATOR);
  }

  private static Integer[][] normalizeRanges(Integer[][] zipRanges, Comparator<Integer[]> rangesComparator) {
    Arrays.sort(zipRanges, rangesComparator);
    Holder<Integer> mergedRangeIndex = new Holder<>(0);
    // We'll store the merged ranges right in the input array since it's not required to keep input intact.
    // If the current range is adjacent to the last merged range or overlaps it
    // but is not included there then we extend the merged range
    // else we treat the current range as the next merged range.
    Arrays.stream(zipRanges, 1, zipRanges.length).forEach(range -> {
      if (range[0] - 1 <= zipRanges[mergedRangeIndex.value][1]) {
        if (range[1] > zipRanges[mergedRangeIndex.value][1]) {
          zipRanges[mergedRangeIndex.value][1] = range[1];
        }
      } else {
        zipRanges[++mergedRangeIndex.value] = range;
      }
    });
    return Arrays.copyOf(zipRanges, mergedRangeIndex.value + 1);
  }

  // I don't like main methods regular project classes. This one here is an exception for the coding challenge sake.
  public static void main(String[] args) {
    // for more input sets please see ZipRangesNormalizerTest
    Integer[][] normalizedZipRanges = ZipRangesNormalizer.normalizeRanges(new Integer[][]{
        {94133, 94133}, {94200, 94299}, {94226, 94399}
    });
    Arrays.stream(normalizedZipRanges)
        .forEach(range -> System.out.printf("[%d, %d]%n", range[0], range[1]));
  }
}

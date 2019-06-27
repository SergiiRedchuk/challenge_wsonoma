package challenge.wsonoma.rangenorm;

import java.util.Arrays;
import java.util.Comparator;
import javax.xml.ws.Holder;

public class ZipRangesNormalizerImpl implements ZipRangesNormalizer {

  private static final Comparator<Integer[]> RANGES_COMPARATOR =
      (a, b) -> a[0] < b[0] ? -1 : (a[0] > b[0] ? 1 : Integer.compare(a[1], b[1]));

  /**
   * Assume that all input ranges are valid and don't check if rangeStart <= rangeEnd.
   * Use only when sure if input is valid.
   *
   * @param zipRanges array
   * @return normalized ranges
   */
  public Integer[][] normalizeRanges(Integer[][] zipRanges) {
    Arrays.sort(zipRanges, RANGES_COMPARATOR);
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
}

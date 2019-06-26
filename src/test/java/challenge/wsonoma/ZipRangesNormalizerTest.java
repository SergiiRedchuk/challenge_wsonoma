package challenge.wsonoma;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ZipRangesNormalizerTest {

  private static final Integer[][] IN_RANGES_1 = new Integer[][]{
      {94133, 94133}, {94200, 94299}, {94226, 94399}, {95226, 95399}
  };

  private static final Integer[][] OUT_RANGES_1 = new Integer[][]{
      {94133, 94133}, {94200, 94399}, {95226, 95399}
  };

  private static final Integer[][] IN_RANGES_2 = new Integer[][]{
      {94226, 94399},
      {94133, 94228},
      {94100, 94133},
      {94099, 94099}
  };

  private static final Integer[][] OUT_RANGES_2 = new Integer[][]{
      {94099, 94399}
  };

  private static final Integer[][] IN_RANGES_3 = new Integer[][]{
      {94226, 94399},
      {94299, 94299},
      {94133, 94133},
      {94200, 94299},
      {95226, 95399},
      {90100, 94134}
  };

  private static final Integer[][] OUT_RANGES_3 = new Integer[][]{
      {90100, 94134},
      {94200, 94399},
      {95226, 95399}
  };

  @Test
  public void testNonOverlappingRangesStay() {
    assertArrayEquals(OUT_RANGES_1, ZipRangesNormalizer.normalizeValidRanges(IN_RANGES_1));
    assertArrayEquals(OUT_RANGES_1, ZipRangesNormalizer.normalizeRanges(IN_RANGES_1));
  }

  @Test
  public void testAdjacentAndOverlappingRangesMerge() {
    assertArrayEquals(OUT_RANGES_2, ZipRangesNormalizer.normalizeValidRanges(IN_RANGES_2));
    assertArrayEquals(OUT_RANGES_2, ZipRangesNormalizer.normalizeRanges(IN_RANGES_2));
  }

  @Test
  public void testIncludedRangesAreSkipped() {
    assertArrayEquals(OUT_RANGES_3, ZipRangesNormalizer.normalizeValidRanges(IN_RANGES_3));
    assertArrayEquals(OUT_RANGES_3, ZipRangesNormalizer.normalizeRanges(IN_RANGES_3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRangeCausesException() {
    ZipRangesNormalizer.normalizeRanges(new Integer[][]{
        {90100, 94134}, {94200, 93500}
    });
  }
}

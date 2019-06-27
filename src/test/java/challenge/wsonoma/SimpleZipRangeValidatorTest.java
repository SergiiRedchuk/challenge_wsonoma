package challenge.wsonoma;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import challenge.wsonoma.ranges.validator.SimpleZipRangeValidator;
import org.junit.Before;
import org.junit.Test;

public class SimpleZipRangeValidatorTest {

  private SimpleZipRangeValidator zipRangeValidator;

  @Before
  public void setUp() {
    zipRangeValidator = new SimpleZipRangeValidator();
  }

  @Test
  public void testValidZipRanges() {
    assertTrue(zipRangeValidator.isRangeValid(new String[] {"93145", "93145"}));
    assertTrue(zipRangeValidator.isRangeValid(new String[] {"93145", "93146"}));
  }

  @Test
  public void testInvalidZipRanges() {
    assertFalse(zipRangeValidator.isRangeValid(new String[] {"93145", "93144"}));
    assertFalse(zipRangeValidator.isRangeValid(new String[] {"9314A", "93144"}));
  }
}

package challenge.wsonoma.ranges.validator;

import java.util.regex.Pattern;

public class SimpleZipRangeValidator implements ZipRangeValidator {

  private static Pattern SIMPLE_ZIP_PATTERN = Pattern.compile("\\d{5}");

  @Override
  public boolean isRangeValid(String[] range) {
    return SIMPLE_ZIP_PATTERN.matcher(range[0]).matches()
        && SIMPLE_ZIP_PATTERN.matcher(range[1]).matches()
        && range[0].compareTo(range[1]) < 1;
  }
}

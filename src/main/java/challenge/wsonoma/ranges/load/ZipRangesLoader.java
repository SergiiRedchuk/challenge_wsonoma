package challenge.wsonoma.ranges.load;

import challenge.wsonoma.ranges.validator.ZipRangeValidator;
import java.io.IOException;

public interface ZipRangesLoader {

  Integer[][] loadRanges(ZipRangeValidator rangeValidator) throws IOException;
}

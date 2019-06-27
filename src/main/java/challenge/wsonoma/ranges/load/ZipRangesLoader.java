package challenge.wsonoma.ranges.load;

import java.io.IOException;

public interface ZipRangesLoader {

  Integer[][] loadRanges() throws IOException;
}

package marimon.db;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Created by ignasi on 06/10/14.
 */
interface Unchecked {
  default <T> Supplier<T> unchecked(Callable<T> f) {
    return () -> {
      try {
        return f.call();
      } catch (Exception e) {
        throw new RuntimeException(e);
      } catch (Throwable t) {
        throw t;
      }
    };
  }
}

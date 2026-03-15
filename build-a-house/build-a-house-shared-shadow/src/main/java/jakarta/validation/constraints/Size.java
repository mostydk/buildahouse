package jakarta.validation.constraints;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Exist only for J2CL/shared compilation and do not perform validation
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Size {
	int max();
}

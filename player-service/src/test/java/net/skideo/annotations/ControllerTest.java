package net.skideo.annotations;

import net.skideo.FilterConfigurationTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ActiveProfiles("test")
@Import(FilterConfigurationTest.class)
public @interface ControllerTest {
}

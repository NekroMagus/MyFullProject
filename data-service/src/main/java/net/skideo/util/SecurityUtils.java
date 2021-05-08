package net.skideo.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {

    public String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

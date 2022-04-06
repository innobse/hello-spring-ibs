package ru.learnup.ibs.hello.spring.hellospring.controlllers.api.v1;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.util.MimeTypeUtils.*;

/**
 * Description
 *
 * @author bse71
 * Created on 31.03.2022
 * @since
 */
@Controller
@RequestMapping("/hello")
public class MiscController {

    @GetMapping(
            produces = TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String helloWorld() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
}

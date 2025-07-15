package com.takeshi.library.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("requestUri")
    public String exposeRequestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }
}

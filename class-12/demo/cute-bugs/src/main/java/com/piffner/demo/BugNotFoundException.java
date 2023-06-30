package com.piffner.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This describes a not found 404 in a controller
 * came from https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Bug Not Found")
public class BugNotFoundException extends RuntimeException {
}

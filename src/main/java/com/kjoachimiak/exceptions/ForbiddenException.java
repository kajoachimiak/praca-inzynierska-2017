package com.kjoachimiak.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by karol on 08.02.18.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Access refused! Not enough authority to execute requested action!")
public class ForbiddenException extends RuntimeException {}

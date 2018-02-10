package com.kjoachimiak.service;

import java.io.IOException;

/**
 * Created by karol on 12.12.17.
 */
public interface ShellRunnerService {
    String runScript(String script) throws IOException, InterruptedException;
}

package com.kjoachimiak.service;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by karol on 12.12.17.
 */
public interface ShellRunnerService {
    Future<Long> runScript(String script) throws IOException, InterruptedException;
}

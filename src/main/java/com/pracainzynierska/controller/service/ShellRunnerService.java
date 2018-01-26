package com.pracainzynierska.controller.service;

import java.io.IOException;

/**
 * Created by karol on 12.12.17.
 */
public interface ShellRunnerService {
    StringBuffer runScript(String script) throws IOException, InterruptedException;
}

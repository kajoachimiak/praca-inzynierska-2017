package com.kjoachimiak.service.impl;

import com.kjoachimiak.service.ShellRunnerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by karol on 12.12.17.
 */
@Service("shellRunnerService")
public class ShellRunnerServiceImpl implements ShellRunnerService {
    private static final Logger LOG = Logger.getLogger(ShellRunnerServiceImpl.class);

    @Override
    public void runScript(String script) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec(script);
        p.waitFor();
    }
}

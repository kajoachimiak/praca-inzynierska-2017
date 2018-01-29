package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.controller.service.ShellRunnerService;
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
    public String runScript(String script) throws IOException, InterruptedException {
        StringBuffer output = new StringBuffer();
        Process p;

        p = Runtime.getRuntime().exec(script);
        p.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        return output.toString();
    }
}

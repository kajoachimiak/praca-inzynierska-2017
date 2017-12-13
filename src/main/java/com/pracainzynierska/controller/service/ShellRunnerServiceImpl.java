package com.pracainzynierska.controller.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public StringBuffer runScript(String script) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(script);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!=null){
                output.append(line).append("\n");
            }
        } catch (IOException e) {
            LOG.error("Command: " + script + " execution failed!", e);
        } catch (InterruptedException e) {
            LOG.error("Intterupted exception while execution command: "+ script,e);
        }
        return output;
    }
}

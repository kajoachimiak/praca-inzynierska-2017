package com.kjoachimiak.service.impl;

import com.kjoachimiak.helpers.ProcessExecutor;
import com.kjoachimiak.service.ShellRunnerService;
import org.apache.commons.exec.CommandLine;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by karol on 12.12.17.
 */
@Service("shellRunnerService")
public class ShellRunnerServiceImpl implements ShellRunnerService {
    private static final Logger LOG = Logger.getLogger(ShellRunnerServiceImpl.class);

    /**
     * Takes raw command and splits it in two:
     * First - command working directory, second - command with arguments .
     * Next sets arguments.
     * Then starts asynchronous execution of command.
     * @param script raw command in format: root working dir *space* command/shell script *space* arguments
     * @return Result of an asynchronous command computation.
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public Future<Long> runScript(String script) throws IOException, InterruptedException {
        List<String> split = new LinkedList<String>(Arrays.asList(script.split("\\s+")));
        File workingDir = new File(split.get(0));
        split.remove(0);

        CommandLine command = new CommandLine(split.get(0));
        split.remove(0);
        split.forEach(command::addArgument);

        return ProcessExecutor.runProcess(new CommandLine(command), 5000, workingDir);
    }
}

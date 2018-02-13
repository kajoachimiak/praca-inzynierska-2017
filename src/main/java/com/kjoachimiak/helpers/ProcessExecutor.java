package com.kjoachimiak.helpers;

import org.apache.commons.exec.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by karol on 13.02.18.
 */
public class ProcessExecutor {
    public static final Long  WATCHDOG_EXIST_VALUE = -999L;
    public static Future<Long> runProcess(final CommandLine commandline, final long watchdogTimeout,
                                          final File workingDirectory) throws IOException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(new ProcessCallable(watchdogTimeout, commandline, workingDirectory));
    }
    private static class ProcessCallable implements Callable {
        private long watchdogTimeout;
        private CommandLine commandline;
        private File workingDir;
        private ProcessCallable(long watchdogTimeout, CommandLine commandline, File workingDir) {
            this.watchdogTimeout = watchdogTimeout;
            this.commandline = commandline;
            this.workingDir = workingDir;
        }
        @Override
        public Long call() throws Exception {
            DefaultExecutor executor = new DefaultExecutor();
            executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
            executor.setWorkingDirectory(workingDir);
            ExecuteWatchdog watchDog = new ExecuteWatchdog(watchdogTimeout);
            executor.setWatchdog(watchDog);
            Long exitValue;
            try {
                exitValue = (long) executor.execute(commandline);
            } catch (ExecuteException e) {
                exitValue = (long) e.getExitValue();
            }
            if(watchDog.killedProcess()){
                exitValue =WATCHDOG_EXIST_VALUE;
            }
            return exitValue;
        }
    }
}

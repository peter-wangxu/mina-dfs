package com.sting2me.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 14-12-10.
 * TODO need to add support for pipeline
 */
public class CommandExecutor {
    public static Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

    /**
     * add support for command with redirect pipe line |
     *
     * @param append
     * @return
     */
    public static CommandResult execute(List<String> append) {
        List<String> commands = new ArrayList<String>();
        commands.add("sh");
        commands.add("-c");
        commands.add("\\\"");
        commands.addAll(append);
        commands.add("\\\"");
        ProcessBuilder pb = new ProcessBuilder(commands);
        try {
            Process process = pb.start();
            int n = process.waitFor();
            return new CommandResult(n, getOutput(process, (n != 0)));
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("Execute [{}] errors : [{}]", commands.toString(), e.getMessage());
        } catch (InterruptedException e) {
            logger.error("Execute [{}] interrupted : [{}]", commands.toString(), e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }

    public static CommandResult execute(String[] params) {
        List<String> commands = new ArrayList<String>();
        for (String s : params) {
            commands.add(s);
        }
        return execute(commands);
    }

    public static CommandResult execute(String cmdLine) {
        try {
            Process process = Runtime.getRuntime().exec(cmdLine);
            int n = process.waitFor();
            return new CommandResult(n, getOutput(process, (n != 0)));
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("Execute [{}] errors : [{}]", cmdLine, e.getMessage());
        } catch (InterruptedException e) {
            //e.printStackTrace();
            logger.error("Execute [{}] interrupted : [{}]", cmdLine, e.getMessage());
        }
        return null;
    }

    /**
     * @param process
     * @return
     */
    private static String getOutput(Process process, boolean isError) {
        BufferedReader input = null;
        if (isError) {
            input = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        } else {
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        }
        String line = null;
        StringBuffer sb = new StringBuffer();
        try {
            while ((line = input.readLine()) != null) {
                //logger.debug(line);
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != input)
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        logger.debug("output:[{}]", sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        //execute("ps -ef");
        String[] cmds = new String[1];
        cmds[0] = "ps -ef | grep watchdog";

        execute(new String[]{"netstat", "-ntlp", "|", "grep", "7777"});
        System.out.println("====================================================");
        execute("sh -c \\\"netstat -ntlp | grep 7777\\\"");
        execute("mkdir -p /tmp/peter/test");
    }
}

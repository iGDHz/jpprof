package com.hz.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 命令行执行类
 */
@Slf4j
public class CommandUtils {

    /**
     * Executes a command on the native command line and returns the result.
     *
     * @param cmdToRun
     *            Command to run
     * @return A list of Strings representing the result of the command, or empty
     *         string if the command failed
     */
    public static List<String> runNative(String cmdToRun) throws IOException {
        String[] cmd = cmdToRun.split(" ");
        return runNative(cmd);
    }

    /**
     * Executes a command on the native command line and returns the result line by
     * line.
     *
     * @param cmdToRunWithArgs
     *            Command to run and args, in an array
     * @return A list of Strings representing the result of the command, or empty
     *         string if the command failed
     */
    public static List<String> runNative(String[] cmdToRunWithArgs) throws IOException {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(cmdToRunWithArgs);
        } catch (SecurityException e) {
            log.trace("Couldn't run command {}:", Arrays.toString(cmdToRunWithArgs));
            log.trace(String.valueOf(e));
            return new ArrayList<String>(0);
        } catch (IOException e) {
            log.trace("Couldn't run command {}:", Arrays.toString(cmdToRunWithArgs));
            log.trace(String.valueOf(e));
            return new ArrayList<String>(0);
        }

        ArrayList<String> sa = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sa.add(line);
            }
            p.waitFor();
        } catch (IOException e) {
            log.trace("Problem reading output from {}:", Arrays.toString(cmdToRunWithArgs));
            log.trace(String.valueOf(e));
            return new ArrayList<String>(0);
        } catch (InterruptedException ie) {
            log.trace("Problem reading output from {}:", Arrays.toString(cmdToRunWithArgs));
            log.trace(String.valueOf(ie));
            Thread.currentThread().interrupt();
        } finally {
            IOUtils.close(reader);
        }
        return sa;
    }
}

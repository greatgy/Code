package com.genius.core.base.utils;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;

/**
 * @author Architect.bian
 *
 */
public class ExecUtil extends BaseUtil {

	/**
	 * @param jarpath
	 * @param args
	 * @return
	 * @throws IOException 
	 * @throws ExecuteException 
	 */
	public static int execjar(String jarpath, String args) throws ExecuteException, IOException {
		String line = "java -jar " + jarpath +  args ;
		CommandLine commandLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
		executor.setWatchdog(watchdog);
		return executor.execute(commandLine);
	}

}
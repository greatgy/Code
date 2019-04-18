package com.genius.server.timer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 计时器操作类，负责执行executer
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午7:47:00
 */
public class Handler {

	private static Logger log = LoggerFactory.getLogger(Handler.class);
	
	private List<Executer> executers;

	public List<Executer> getExecuters() {
		return executers;
	}

	public void setExecuters(List<Executer> executers) {
		this.executers = executers;
	}
	
	public void execute() {
		for (Executer item : executers) {
			try {
				item.execute();
				log.debug(String.format("success execute: %s", item.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				log.debug("exception executing:" + item.toString() + "\n" + e.getMessage());
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				log.error(sw.toString());
			}
		}
	}
}

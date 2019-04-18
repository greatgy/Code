package com.genius.server.base.exception;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * log异常处理换行符
 * 
 * @author YangGuang
 * @createtime 2018年6月26日12:15:38
 */
public class ExceptionFileAppender extends FileAppender {

	@Override
	protected void subAppend(LoggingEvent event) {
		String str = this.layout.format(event).replaceAll(System.getProperty("line.separator"), "^-^");
		str = str.replaceAll("\\^-\\^\\^-\\^", System.getProperty("line.separator"));
		if (new StringBuffer(str).reverse().toString().startsWith("^-^")) {
			str = str.substring(0, str.length() - 3) + System.getProperty("line.separator");
		}
		this.qw.write(str);

		if (layout.ignoresThrowable()) {
			String[] s = event.getThrowableStrRep();
			if (s != null) {
				int len = s.length;
				for (int i = 0; i < len; i++) {
					this.qw.write(s[i]);
					this.qw.write(Layout.LINE_SEP);
				}
			}
		}

		if (shouldFlush(event)) {
			this.qw.flush();
		}
	}
}

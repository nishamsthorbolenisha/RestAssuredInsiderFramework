package com.insider.framework;

import org.apache.log4j.Logger;

public class Logs {
	public static Logger log;

	public Logs() {
		log = Logger.getLogger(this.getClass().getName());
	}

}

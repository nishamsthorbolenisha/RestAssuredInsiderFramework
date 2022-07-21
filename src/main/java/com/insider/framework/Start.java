package com.insider.framework;

import com.insider.common.Constants;

public class Start extends Setup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Setup st = new Setup();
			Constants.initConfig();
			st.executeTest();

		} catch (Exception e) {
			System.out.println("Exception during executions.Error: " + e.getMessage());
		} finally {
			System.out.println("============ Automation Tests Finished ================");
		}
	}
}

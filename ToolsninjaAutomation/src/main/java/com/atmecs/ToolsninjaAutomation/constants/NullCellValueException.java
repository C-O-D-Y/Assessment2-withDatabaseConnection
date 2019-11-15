package com.atmecs.ToolsninjaAutomation.constants;

public class NullCellValueException extends Throwable {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 477561502219684525L;

	/*
	 * Constructor calling is done when condition arises as mentioned and, Message
	 * will be printed.
	 */
	public NullCellValueException(String message) {
		System.out.println(message);
	}

}

package com.seating.plan.generator.system.exception;

public class InstituteValidationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InstituteValidationException() {

	}

	public InstituteValidationException(String message) {
		super(message);

	}

	public InstituteValidationException(Throwable cause) {
		super(cause);

	}

	public InstituteValidationException(String message, Throwable cause) {
		super(message, cause);

	}

	public InstituteValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}

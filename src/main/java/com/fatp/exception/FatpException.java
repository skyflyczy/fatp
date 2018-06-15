package com.fatp.exception;

public class FatpException extends RuntimeException{

	private static final long serialVersionUID = 7428818080722071837L;
	
	private String message;
	
	private ErrorCode errorCode;

	public FatpException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public FatpException(String message) {
		errorCode = ErrorCode.SYSTEM_ERROR;
		this.message = message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

}

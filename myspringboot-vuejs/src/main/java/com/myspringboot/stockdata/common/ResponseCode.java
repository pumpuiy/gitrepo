package com.myspringboot.stockdata.common;

public enum ResponseCode {

	SUCCESSFUL("0000I", "Successful"), 
	USER_IN_USE("2001E", "User Already in Session"), 
	GROUP_IN_USE("2002E", "Group Already Assigned in Pool"), 
	POOL_IN_USE("2003E", "Pool Already Assigned in Service"), 
	USER_PASSWORD_INVALID("3001E", "Invalid Username or Password"), 
	USER_INACTIVE("3002E", "Inactive User"), 
	USER_UNAUTHORIZE("3003E", "Unauthorized User"), 
	DATA_INVALID("6001E", "Invalid Request Data"), 
	FILE_FORMAT_INVALID("6002E", "Invalid File Format"), 
	CALL_HTTP_ERROR("6003E", "Call Http Error ({httpErrorCode})"), 
	DATA_NOT_FOUND("8001I",	"Data not found"), 
	DATA_DUPLICATE("8002E", "Duplicate Data"), 
	DATABASE_ERROR("8003E", "Database Error"), 
	DATA_REQUIRE("8004E", "Invalid Required Data"), 
	TRANSACTION_FAIL("8005E", "Unsuccess Transaction");

	private final String code;
	private String defaultMsg;

	ResponseCode(String code, String msg) {
		this.code = code;
		this.defaultMsg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getDefaultMessage() {
		return defaultMsg;
	}
	
	public static ResponseCode getResponseCodeByString(String code) {
		if(SUCCESSFUL.code.equals(code)) {
			return SUCCESSFUL;
		}else if(USER_IN_USE.code.equals(code)) {
			return USER_IN_USE;
		}else if(GROUP_IN_USE.code.equals(code)) {
			return GROUP_IN_USE;
		}else if(POOL_IN_USE.code.equals(code)) {
			return POOL_IN_USE;
		}else if(USER_PASSWORD_INVALID.code.equals(code)) {
			return USER_PASSWORD_INVALID;
		}else if(USER_INACTIVE.code.equals(code)) {
			return USER_INACTIVE;
		}else if(USER_UNAUTHORIZE.code.equals(code)) {
			return USER_UNAUTHORIZE;
		}else if(DATA_INVALID.code.equals(code)) {
			return DATA_INVALID;
		}else if(FILE_FORMAT_INVALID.code.equals(code)) {
			return FILE_FORMAT_INVALID;
		}else if(CALL_HTTP_ERROR.code.equals(code)) {
			return CALL_HTTP_ERROR;
		}else if(DATA_NOT_FOUND.code.equals(code)) {
			return DATA_NOT_FOUND;
		}else if(DATA_DUPLICATE.code.equals(code)) {
			return DATA_DUPLICATE;
		}else if(DATABASE_ERROR.code.equals(code)) {
			return DATABASE_ERROR;
		}else if(DATA_REQUIRE.code.equals(code)) {
			return DATA_REQUIRE;
		}else if(TRANSACTION_FAIL.code.equals(code)) {
			return TRANSACTION_FAIL;
		}
		return ResponseCode.DATA_NOT_FOUND;
	}

	@Override
	public String toString() {
		return "Code=" + code + ", Msg=" + defaultMsg;
	}

	
}

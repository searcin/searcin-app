package com.searcin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class DataValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String MOBILE_PATTERN = "\\d{10}";
	
	private static final String PHONE_PATTERN = "\\d{6}";

	public boolean email(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public boolean mobile(String mobile) {
		pattern = Pattern.compile(MOBILE_PATTERN);
		matcher = pattern.matcher(mobile);
		return matcher.matches();
	}
	
	public boolean phone(String phone) {
		pattern = Pattern.compile(PHONE_PATTERN);
		matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
}
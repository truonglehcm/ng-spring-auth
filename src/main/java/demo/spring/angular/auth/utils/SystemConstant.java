package demo.spring.angular.auth.utils;

import java.util.regex.Pattern;

public final class SystemConstant {
	
	private SystemConstant() {
		
	}
	
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	public static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
	public static final String TOKEN_INVALID = "invalidToken";
	public static final String TOKEN_EXPIRED = "expired";
	public static final String TOKEN_VALID = "valid";

	public static final String CLAIM_KEY_ROLES = "roles";
	public static final String CLAIM_KEY_USERNAME = "sub";
	public static final String CLAIM_KEY_AUDIENCE = "audience";
	public static final String CLAIM_KEY_CREATED = "created";
	public static final String SECRET = "secret";
	public static final Long EXPIRATION = 7200L;

	public static final String AUDIENCE_UNKNOWN = "unknown";
	public static final String AUDIENCE_WEB = "web";
	public static final String AUDIENCE_MOBILE = "mobile";
	public static final String AUDIENCE_TABLET = "tablet";

	public static final String X_FORWARDED_FOR = "X-Forwarded-For";
	public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

	public static final int EXPIRY_DATE = 60 * 24;
	public static final String TOKEN_HEADER = "Authorization";
}

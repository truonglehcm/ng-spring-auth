package demo.spring.angular.auth.utils;

public final class URIConstant {
	
	private URIConstant() {
		
	}

	public static final String EMPTY_STR = "";
	public static final String SLASH = "/";

	/********* Authentication *****************/
	public static final String AUTH = "/auth";
	public static final String SIGN_UP = "/signup";
	public static final String LOG_IN = "/login";
	public static final String SIGNUP_CONFIRM = "/signup/confirm/{token:.+}";
	public static final String REGIST_ACTIVE = "/#!/auth/signup/confirm/";
	public static final String RESET_PASSWORD = "/reset/password";
	public static final String RESET_PASSWORD_CONFIRM = "/reset/password/confirm";

	/********* Tags *****************/
	public static final String GET_TAGS = "/tags";
	public static final String MANAGEMENT_TAG = "/tags/{id}";

	/********* Posts *****************/
	public static final String GET_POSTS = "/posts";
	public static final String MANAGEMENT_POST = "/posts/{id}";

	/********* Management **********/
	public static final String USER_MANAGEMENT_PROFILE = "/user/profile";
	public static final String USER_MANAGEMENT_PASSWORD = "/user/change-password";
	public static final String ADMIN_MANAGEMENT_POSTS = "/management/posts";
	public static final String ADMIN_MANAGEMENT_POST = "/management/posts/{id}";
	public static final String ADMIN_MANAGEMENT_TAGS = "/management/tags";
	public static final String ADMIN_MANAGEMENT_TAG = "/management/tags/{id}";
	public static final String ADMIN_MANAGEMENT_USERS = "/management/users";
	public static final String ADMIN_MANAGEMENT_USER = "/management/users/{id}";

	/********* Google recaptcha ****************/
	public static final String RECAPTCHA_SITEVERIFY = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s";
}

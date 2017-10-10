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
	public static final String TAGS = "/tags";
	public static final String TAGS_UPDATE = "/tags/{id}";
	public static final String TAGS_DELETE = "/tags/{id}";
	public static final String TAGS_GET_BY_ID = "/tags/{id}";

	/********* Posts *****************/
	public static final String POSTS = "/posts";
	public static final String POSTS_UPDATE = "/posts/{id}";
	public static final String POSTS_DELETE = "/posts/{id}";
	public static final String POSTS_GET_BY_ID = "/posts/{id}";

	/********* Management **********/
	public static final String USER_PROFILE = "/user/profile";

	public static final String ADMIN_POSTS = "/management/posts";
	public static final String ADMIN_FIND_POST = "/management/posts/{id}";
	public static final String ADMIN_UPDATE_POSTS = "/management/posts/{id}";
	public static final String ADMIN_DELETE_POSTS = "/management/posts/{id}";

	public static final String ADMIN_TAGS = "/management/tags";
	public static final String ADMIN_FIND_TAG = "/management/tags/{id}";
	public static final String ADMIN_DELETE_TAGS = "/management/tags/{id}";

	public static final String ADMIN_USERS = "/management/users";
	public static final String ADMIN_DELETE_USERS = "/management/users/{id}";

	/********* Google recaptcha ****************/
	public static final String RECAPTCHA_SITEVERIFY = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s";
}

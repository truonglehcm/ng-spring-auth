package demo.spring.angular.auth.web.exception;

public class TokenNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7632805963734190667L;

	public TokenNotFoundException() {
        super();
    }

    public TokenNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(final String message) {
        super(message);
    }

    public TokenNotFoundException(final Throwable cause) {
        super(cause);
    }
}


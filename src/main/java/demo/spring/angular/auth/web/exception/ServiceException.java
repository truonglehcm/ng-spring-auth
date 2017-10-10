package demo.spring.angular.auth.web.exception;

public class ServiceException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2918421925296437470L;

	public ServiceException() {
        super();
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final Throwable cause) {
        super(cause);
    }
    
}

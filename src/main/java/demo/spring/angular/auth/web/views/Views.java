package demo.spring.angular.auth.web.views;

public final class Views {
	
	public static interface Anonymous {}
    public static interface User extends Anonymous {}
    public static interface Admin extends User {}
    
    private Views() {
		
	}
}

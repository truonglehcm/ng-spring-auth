package demo.spring.angular.auth.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.mobile.device.Device;

import demo.spring.angular.auth.persistence.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private final User user;
	private final Device device;
	private final HttpServletRequest httpReq;

	public OnRegistrationCompleteEvent(User user, HttpServletRequest httpReq, Device device) {
		super(user);
		this.user = user;
		this.device = device;
		this.httpReq = httpReq;
	}
}

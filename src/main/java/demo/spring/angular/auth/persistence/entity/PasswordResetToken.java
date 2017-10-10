package demo.spring.angular.auth.persistence.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import demo.spring.angular.auth.utils.CommonUtils;
import demo.spring.angular.auth.utils.SystemConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken extends BaseEntity {
	
	@Column(name = "token", nullable = false)
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiry;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	public PasswordResetToken(final User user, final String token) {
		this.user = user;
		this.token = token;
		this.expiry = CommonUtils.calculateExpiryDate(SystemConstant.EXPIRY_DATE);
	}

	public boolean isExpired() {
		if (Objects.isNull(this.expiry)) {
			return false;
		}
		return this.expiry.getTime() < System.currentTimeMillis();
	}
}

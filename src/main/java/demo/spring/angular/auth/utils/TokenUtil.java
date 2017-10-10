package demo.spring.angular.auth.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import demo.spring.angular.auth.web.response.JwtUserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public final class TokenUtil {
	
	/**
	 * get username from token
	 * @param token
	 * @return username
	 */
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
        	return claims.getSubject();
        }
        return StringUtils.EMPTY;
    }

    /**
     * get created date from token
     * @param token
     * @return created date
     */
    public Date getCreatedDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
        	return new Date((Long) claims.get(SystemConstant.CLAIM_KEY_CREATED));
        }
        return null;
    }

    /**
     * get expiration date from token
     * @param token
     * @return expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
        	return claims.getExpiration();
        }
        return null;
    }

    /**
     * get audience from token
     * @param token
     * @return audience
     */
    public String getAudienceFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
        	return (String) claims.get(SystemConstant.CLAIM_KEY_AUDIENCE);
        }
        return StringUtils.EMPTY;
    }

    /**
     * get claims from token
     * @param token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
    	Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SystemConstant.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * generate expiration date
     * @return expiration date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + SystemConstant.EXPIRATION * 1000);
    }

    /**
     * check token is expired
     * @param token
     * @return true/false
     */
    public Boolean isTokenExpired(String token) {
    	if(StringUtils.isBlank(token)) {
    		return true;
    	}
        Date expiration = getExpirationDateFromToken(token);
        if (Objects.isNull(expiration)) {
        	return false;
        }
        
        return expiration.before(new Date());
    }

    /**
     * check if create date before last password reset
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (Objects.nonNull(lastPasswordReset) && created.before(lastPasswordReset));
    }

    /**
     * generate audience
     * @param device
     * @return audience
     */
    private String generateAudience(Device device) {
        if (device.isNormal()) {
            return SystemConstant.AUDIENCE_WEB;
        } else if (device.isTablet()) {
            return SystemConstant.AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            return SystemConstant.AUDIENCE_MOBILE;
        }
        return SystemConstant.AUDIENCE_UNKNOWN;
    }

    /**
     * check if ignore token expiration
     * @param token
     * @return true/false
     */
    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (SystemConstant.AUDIENCE_TABLET.equals(audience) || SystemConstant.AUDIENCE_MOBILE.equals(audience));
    }

    /**
     * generate token
     * @param userName
     * @param device
     * @return
     */
    public String generateToken(UserDetails userDetail, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SystemConstant.CLAIM_KEY_USERNAME, userDetail.getUsername());
        claims.put(SystemConstant.CLAIM_KEY_ROLES, createRoleMap(userDetail));
        claims.put(SystemConstant.CLAIM_KEY_AUDIENCE, generateAudience(device));
        claims.put(SystemConstant.CLAIM_KEY_CREATED, new Date());
        
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SystemConstant.SECRET)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        Date created = getCreatedDateFromToken(token);
        return Objects.nonNull(created) && !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (Objects.nonNull(claims)) {
        	claims.put(SystemConstant.CLAIM_KEY_CREATED, new Date());
        	return generateToken(claims);
        }
        return StringUtils.EMPTY;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUserResponse user = (JwtUserResponse) userDetails;
        String username = getUsernameFromToken(token);
        Date created = getCreatedDateFromToken(token);
        return (Objects.nonNull(created) 
        		&& username.equals(user.getUsername()) 
        		&& !isTokenExpired(token) 
        		&& !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset()));
    }
    
    private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
        Map<String, Boolean> roles = new HashMap<>();
        userDetails.getAuthorities().stream().forEach(a -> {
        	roles.put(a.getAuthority(), Boolean.TRUE);
        });
        
        return roles;
    }
}
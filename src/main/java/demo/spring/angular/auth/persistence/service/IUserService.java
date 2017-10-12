package demo.spring.angular.auth.persistence.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import demo.spring.angular.auth.persistence.entity.User;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.SignupRequest;
import demo.spring.angular.auth.web.request.UserChangePasswordRequest;
import demo.spring.angular.auth.web.request.UserRequest;
import demo.spring.angular.auth.web.request.UserUpdateInfoRequest;
import demo.spring.angular.auth.web.request.UserUpdateRequest;
import demo.spring.angular.auth.web.response.UserResponse;

public interface IUserService extends IGenericService<User, Long>  {
    User findByUsername(String username) throws ServiceException;
    User findUserByEmail(String email) throws ServiceException;
    void createVerificationToken(User user, String token) throws ServiceException;
    void createPasswordResetToken(User user, String token) throws ServiceException;
    void verificationToken(String token) throws ServiceException;
    UserResponse createUser(UserRequest userRequest) throws ServiceException;
    void updateUser(UserUpdateRequest userRequest) throws ServiceException;
    void deleteUser(Long id) throws ServiceException;
    void resetPassword(String email, HttpServletRequest httpRequest, Device device) throws ServiceException;
    void resetNewPassword(String token, String newPassword) throws ServiceException;
    void signUp(SignupRequest signUpRequest, HttpServletRequest httpRequest, Device device) throws ServiceException;
    List<UserResponse> findAllUser() throws ServiceException;
    void updateProfile(UserUpdateInfoRequest userUpdateInfoRequest, HttpServletRequest httpRequest, Device device) throws ServiceException;
    void changePassword(UserChangePasswordRequest userChangePasswordRequest, HttpServletRequest httpRequest, Device device) throws ServiceException;
}

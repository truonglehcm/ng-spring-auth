(function () {
    'use strict';

    /*** get module to regist constant ***/
    angular
        .module('app')
	      .constant('CONFIG', {
	    	      SLASH						        : '/',
        			SIGNIN						        : '/auth/signin',
        			SIGNUP						        : '/auth/signup',
        			SIGNUP_CONFIRM				    : '/auth/signup/confirm',
        			RESET_PASSWORD				    : '/reset/password?email=',
        			RESET_PASSWORD_CONFIRM		: '/reset/password/confirm?newPassword=',
        			USER_PROFILE				      : '/user/profile',
        			USER_CHANGE_PASSWORD		  : '/user/change-password',
        			MANAGE_USERS				      : '/management/users',
        			MANAGE_POSTS				      : '/management/posts',
        			MANAGE_TAGS					      : '/management/tags',
        			GET_POSTS					        : '/posts',
        			GET_POST_BY_ID				    : '/posts/{id}'
    	});
    
})();

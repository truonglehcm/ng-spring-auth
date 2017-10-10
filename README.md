# NG-SPRING-AUTH #

### This repository is a simple demo using ###
* Spring Boot
* Spring Data JPA
* Angularjs 1.x
* Jwt (Json Web Token)
* Satellizer
* Google captcha
* Mysql
* Flywaydb
### Fork from ###
* [Login and Registration Example Project with Spring Security](https://github.com/Baeldung/spring-security-registration)
* [JWT Spring Security Demo](https://github.com/szerhusenBC/jwt-spring-security-demo)

### Requirements ###
* Maven 3++
* Java 1.8++ 
* Eclipse Neon 4.6.3++ installed STS (Spring Tool Suite)

### Set up ###
##### 1. Config lombok #####
* Right Click <strong>lombok-1.16.16-sources.jar</strong> from <strong>Maven Dependencies > Run As > Java Application</strong>.
* Click button <strong>Install/Update</strong> from lombok console, then refresh project.
##### 2. Open file <strong>application-dev.properties</strong> and change #####
    spring.datasource.username=Your username db
    spring.datasource.password=Your password db
    spring.mail.username=your google mail
    spring.mail.password=your password google mail
    
### If you want to use recaptcha for your site, then follow step 3, step 4, Step 5 ###

##### 3. Regist recaptcha from here: https://www.google.com/recaptcha/intro/ #####

##### 4. Open file <strong>captcha.properties</strong> and change #####
    google.recaptcha.site-key=Your site key (from step 3)
    google.recaptcha.secret-key=Your secret key (from step 3)
       
##### 5. Open file app.constants.js and change<br>
    PUBLIC_RECAPTCHA_KEY : 'your puplic site key'
      
### Build ###
    mvn clean install
### Run ###
    mvn spring-boot:run
* Go to http://localhost:8080
* Login with admin role: <strong>username: admin, password: 123456</strong>
* Login with user role: <strong>username: user, password: 123456</strong>

### Screeenshot ###
  * Home page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/home.PNG)
    
  * Post detail page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/post_detail.PNG)
  
  * Login page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/signin.PNG)
    
  * Signup page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/sign_up.PNG)
        
  * Confirm reset password page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/confirm_reset_password.PNG)
    
  * Manage api page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/manage_api.PNG)
    
  * Manage post page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/manage_post.PNG)
      
  * Manage tag page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/manage_tag.PNG)
        
  * Manage user page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/manage_user.PNG)
    
  * Add or edit post page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/add_or_edit_post.PNG)
    
  * Add or edit user page:
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/add_ore_dit_user.PNG)  
 
  * Manage profile user page: // todo
  
    ![alt text](https://github.com/truonglehcm/ng-spring-auth/blob/master/src/main/resources/static/img/profile.PNG)  

# Spring Security Exploration

ThinkSyncSecurity is a spring security functionality explored project `https://docs.spring.io/spring-security/reference/index.html`.

## Branches
- Find changes in different branch.
- Main is the final changes of all branches 

## Branch With Details

### Branch 1:  [feature/v0.0.1-enable-spring-security](https://github.com/AbinashBapu/ThinkSyncSecurity/tree/feature/v0.0.1-enable-spring-security)

- Spring security enabled
- integrated with username and password basic authentication
- username and password is set to test

### Branch 2:  [feature/v0.0.2-useOfInMemoryUserDetailsManager](https://github.com/AbinashBapu/ThinkSyncSecurity/tree/feature/v0.0.2-useOfInMemoryUserDetailsManager)

- Implemented InMemoryUserDetailsManager basically UserDetailsManager is responsible to manage user details i.e crud User Details
- Default password encoder is bcrypt if we want to use string then should implement noop password encoder
- Generate [bcrypt passwords](https://bcrypt-generator.com/)  
- Check given password is compromised or not

### Branch 3:  [feature/v0.0.3-custom-userService-with-db-integration](https://github.com/AbinashBapu/ThinkSyncSecurity/tree/feature/v0.0.3-custom-userService-with-db-integration)
- Implemented custom user manager which helps to register and signin
- Removed hardcoded users from config and implemented own userService
- Leveraged Spring Security UserDetailsService

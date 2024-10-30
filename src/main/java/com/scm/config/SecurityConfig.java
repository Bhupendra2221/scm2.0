package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.scm.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {


  
    // user create and login page sing java code with in memoary service
 // @Bean
   // public UserDetailsService userDetailsService(){

   // UserDetails user1 = User
    //.withDefaultPasswordEncoder()
   // .username("admin123")
   // .password("admin123")
 //   .roles("ADMIN","USER")
 //   .build();

  //  var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
   //  return inMemoryUserDetailsManager;*/

 //  }
@Autowired
 private SecurityCustomUserDetailService userDetailService;

@Autowired
 private OAuthAuthenicationSuccessHandler handler;

 //configuration of authentication provider for spring security
@Bean
 public AuthenticationProvider authenticationProvider(){

  DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
  //user Details service ka object:
daoAuthenticationProvider.setUserDetailsService(userDetailService);
//password service ka object:
daoAuthenticationProvider.setPasswordEncoder(PasswordEncoder());

  return daoAuthenticationProvider;
 }
@Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{


  //configurtion

  //urls configure kiya hai ki koun se public rangenge hai private rangenge
  httpSecurity.authorizeHttpRequests(authorize ->{
    //authorize.requestMatchers("/home","/register","/services").permitAll();
    authorize.requestMatchers("/user/**").authenticated();
    authorize.anyRequest().permitAll();

  });


  //form default login
  //agar  hame kuch bhi change karna hua to hama yaha ayenge: form login related
  httpSecurity.formLogin(formLogin->{
    //
    formLogin.loginPage("/login");
    formLogin.loginProcessingUrl("/authenticate");
    formLogin.successForwardUrl("/user/profile");
    //formLogin.failureForwardUrl("/login?error=true");
    //formLogin.defaultSuccessUrl("/home");
    formLogin.usernameParameter("email");
    formLogin.passwordParameter("password");
  
    /*formLogin.failureHandler(new AuthenticationFailureHandler() {

     @Override
      public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
      }
      
    });
    
    formLogin.successHandler(new AuthenticationSuccessHandler() {

      @Override
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
          Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
      }
      
    })*/
  }); 


   httpSecurity.csrf(AbstractHttpConfigurer::disable);
  httpSecurity.logout(logoutForm->{
   
    logoutForm.logoutUrl("/logout");
    logoutForm.logoutSuccessUrl("/login?logout=true");
  });

   //oauth configurations
   httpSecurity.oauth2Login(oauth -> {
    oauth.loginPage("/login");
   oauth.successHandler(handler);
});

httpSecurity.logout(logoutForm -> {
    logoutForm.logoutUrl("/do-logout");
    logoutForm.logoutSuccessUrl("/login?logout=true");
});


  return httpSecurity.build();

 }

 @Bean
 public PasswordEncoder PasswordEncoder() {

  return new BCryptPasswordEncoder();
 }
}

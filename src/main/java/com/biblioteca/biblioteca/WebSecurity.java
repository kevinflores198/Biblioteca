package com.biblioteca.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.biblioteca.biblioteca.Services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity {

        @Autowired
        UserService userService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userService)
                                .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Bean
        @Primary
        protected HttpSecurity config(HttpSecurity http) throws Exception {
                return http.authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll();
                        
                })
                                .formLogin(login -> extracted(login))
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .permitAll());
        }

        private FormLoginConfigurer<HttpSecurity> extracted(FormLoginConfigurer<HttpSecurity> login) {
                return login
                                .loginPage("/login")
                                .loginProcessingUrl("/logincheck")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/main")
                                .permitAll();
        }
}

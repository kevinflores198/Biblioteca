package com.biblioteca.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.biblioteca.biblioteca.Services.UserService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurity {

        @Autowired
        private UserService userService;

        @Autowired
        public void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userService)
                                .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Bean
        public SecurityFilterChain filter(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/admin/*").hasRole("ADMIN")
                                                .requestMatchers("/css/*", "/js/*", "/img/*", "/**")
                                                .permitAll())
                                .formLogin(login -> login
                                                .loginPage("/login")
                                                .loginProcessingUrl("/logincheck")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/main")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login")
                                                .permitAll())
                                .csrf(csrf -> csrf
                                                .disable());
                return http.build();
        }

}

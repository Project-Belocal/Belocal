package kr.co.belocal.web.config;


import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.co.belocal.web.handler.AuthSuccessHandler;

import javax.swing.*;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class SecurityConfig  {


    //    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web -> web
//                .ignoring().anyRequest()
//        );
//    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/js/**","/css/**","/images/**").permitAll()
//                                .requestMatchers("/**").hasAnyRole("MEMBER","GUIDE","ADMIN")
                        .requestMatchers("/member/**"
                                                    ,"/wishlists/**"
                                                    ,"/my/**"
                                                    ,"/location/**"
                                                    ,"/chat/**")
                                .hasAnyRole("MEMBER","GUIDE","ADMIN")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("userId")
                        .passwordParameter("pw")
                        .successHandler(authSuccessHandler())
                        // .defaultSuccessUrl("/")
                )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .sessionManagement(session->session
                        .sessionFixation().changeSessionId()
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//세션이 필요하다면 생성
                        .maximumSessions(1)//최대1개
                        .maxSessionsPreventsLogin(false)
                );

        return http.build();
    }


    @Bean
    public AuthSuccessHandler authSuccessHandler() {
        return new AuthSuccessHandler();
    }


}

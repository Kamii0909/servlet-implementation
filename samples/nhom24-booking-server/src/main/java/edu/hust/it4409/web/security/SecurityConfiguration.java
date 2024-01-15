package edu.hust.it4409.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/hotel/new/**").hasRole("admin")
                .requestMatchers("/hotel/review/**").authenticated()
                .anyRequest().permitAll())
            .csrf(csrf -> csrf.disable())
            .formLogin(login -> login
                .successHandler((req, res, chain) -> res.setStatus(HttpStatus.NO_CONTENT.value())))
            .logout(logout -> logout
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT)))
            .httpBasic(basic -> basic.addObjectPostProcessor(saveContextInHttpSession()))
            
            .build();
    }
    
    @Bean
    SecurityController securityController(UserDetailsManager userDetailsManager) {
        return new SecurityController(userDetailsManager);
    }
    
    @Bean
    @Profile("dev")
    InMemoryUserDetailsManager inmemoryUserDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("admin")
            .build();
        UserDetails member = User.withDefaultPasswordEncoder()
            .username("member")
            .password("password")
            .roles()
            .build();
        return new InMemoryUserDetailsManager(admin, member);
    }
    
    private ObjectPostProcessor<BasicAuthenticationFilter> saveContextInHttpSession() {
        return new ObjectPostProcessor<BasicAuthenticationFilter>() {
            @Override
            public <O extends BasicAuthenticationFilter> O postProcess(O object) {
                object.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
                return object;
            }
        };
    }
    
}

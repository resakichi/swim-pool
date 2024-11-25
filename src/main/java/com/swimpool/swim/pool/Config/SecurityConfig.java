package com.swimpool.swim.pool.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/").permitAll() // Разрешаем доступ к консоли H2
                    .anyRequest().authenticated()) // Все остальные запросы требуют аутентификации
            .formLogin(Customizer.withDefaults()); // Включаем форму логина
        
        // Отключаем CSRF для доступа к H2 Console
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/h2-console/**"));
        
        // Включаем поддержку фреймов для H2 Console
        http.headers((headers) -> headers.frameOptions((options) -> options.sameOrigin()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                               .username("user")
                               .password("password")
                               .roles("USER")
                               .build());
        return manager;
    }
}

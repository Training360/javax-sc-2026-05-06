package employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers("/")
                                .permitAll()
                                .requestMatchers("/create-employee")
                                .authenticated()
//                                .hasRole("employees_admin")
                                .anyRequest()
                                .permitAll()
                )
                .oauth2Login(Customizer.withDefaults())
                .logout(conf -> conf.logoutSuccessUrl("/"));
        return http.build();
    }
}

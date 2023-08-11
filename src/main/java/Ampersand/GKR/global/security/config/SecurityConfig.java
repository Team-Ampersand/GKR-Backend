package Ampersand.GKR.global.security.config;

import Ampersand.GKR.global.filter.ExceptionFilter;
import Ampersand.GKR.global.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    private final ExceptionFilter exceptionFilter;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
                .antMatchers(HttpMethod.DELETE, "/auth").authenticated()

                .antMatchers(HttpMethod.GET, "/equipment").permitAll()
                .antMatchers(HttpMethod.GET, "/equipment/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/equipment/rent").permitAll()
                .antMatchers(HttpMethod.GET, "/equipment/type").permitAll()
                .antMatchers(HttpMethod.GET, "/equipment/search").permitAll()
                .antMatchers("/equipment/admin").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.GET, "/user").authenticated()
                .antMatchers(HttpMethod.GET, "/user/admin/all").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.POST, "/application/rental/{id}").authenticated()

                .anyRequest().denyAll();

        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, JwtRequestFilter.class);

        return http.build();
    }
}


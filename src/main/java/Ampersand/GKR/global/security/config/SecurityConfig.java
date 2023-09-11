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
                .antMatchers(HttpMethod.GET, "/equipment/state").permitAll()
                .antMatchers(HttpMethod.GET, "/equipment/type").permitAll()
                .antMatchers(HttpMethod.GET, "/equipment/search").permitAll()
                .antMatchers("/equipment/create").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/equipment/edit/{id}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/equipment/delete{id}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/equipment/repair/{id}").hasAuthority("ROLE_ADMIN")
                .antMatchers("/equipment/repair/completion/{id}").hasAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.GET, "/user").authenticated()
                .antMatchers(HttpMethod.GET, "/user/all").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.POST, "/order/rental/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/order/return/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/order/extension/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/order/state").authenticated()

                .antMatchers(HttpMethod.GET, "/order/now").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/order/noreturn").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/order/wait").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PATCH, "/order/reject/{id}").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PATCH, "/order/accept/{id}").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.POST, "/violation").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PATCH, "/violation").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/violation").authenticated()

                .anyRequest().denyAll();

        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, JwtRequestFilter.class);

        return http.build();
    }
}


package Ampersand.GKR.global.security.config;

import Ampersand.GKR.global.filter.ExceptionFilter;
import Ampersand.GKR.global.filter.JwtRequestFilter;
import Ampersand.GKR.global.logger.filter.LogRequestFilter;
import Ampersand.GKR.global.security.handler.CustomAccessDeniedHandler;
import Ampersand.GKR.global.security.handler.CustomAuthenticationEntryPointHandler;
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

    private final LogRequestFilter logRequestFilter;

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
                .antMatchers(HttpMethod.GET, "/equipment/filter").permitAll()
                .antMatchers(HttpMethod.GET, "/equipment/search").permitAll()
                .antMatchers(HttpMethod.POST, "/equipment/create").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/equipment/edit/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.DELETE, "/equipment/delete/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.DELETE, "/equipment/delete").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/equipment/repair/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/equipment/repair/completion/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")

                .antMatchers(HttpMethod.GET, "/user").authenticated()
                .antMatchers(HttpMethod.GET, "/user/rental").authenticated()
                .antMatchers(HttpMethod.GET, "/user/all").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")

                .antMatchers(HttpMethod.POST, "/order/rental/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/order/return/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/order/extension/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/order/cancel/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/order/state").authenticated()

                .antMatchers(HttpMethod.GET, "/order/now").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/order/noreturn").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/order/wait").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/order/reject/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/order/accept/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/order/detail/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")

                .antMatchers(HttpMethod.POST, "/violation").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/violation/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/violation").authenticated()
                .antMatchers(HttpMethod.GET, "/violation/all").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")

                .antMatchers(HttpMethod.POST, "/notice/create").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.DELETE, "/notice/delete/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/notice/edit/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/notice").authenticated()
                .antMatchers(HttpMethod.GET, "/notice/{id}").authenticated()

                .anyRequest().denyAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPointHandler())

                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, JwtRequestFilter.class)
                .addFilterBefore(logRequestFilter, ExceptionFilter.class);

        return http.build();
    }
}


package com.hustle_hub.server.config;

import com.hustle_hub.server.exceptions.CustomBasicAuthenticationEntryPoint;
import com.hustle_hub.server.filter.CsrfCookieFilter;
import com.hustle_hub.server.filter.JWTTokenGenerationFilter;
import com.hustle_hub.server.filter.JWTTokenValidationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))


//                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
//                        .ignoringRequestMatchers("/swagger-ui/**", "/v3/api-docs/**","/api/user/create","/api/user/apiLogin")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers( "/swagger-ui/**", "/v3/api-docs/**","/api/user/create","/api/user/apiLogin")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGenerationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**","/api/user/create","/api/user/apiLogin").permitAll()
                .requestMatchers("/api/**").hasAuthority("USER")
        );
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
        HustleHubUsernameAuthenticationPassword hustleHubUsernameAuthenticationPassword = new HustleHubUsernameAuthenticationPassword(userDetailsService,passwordEncoder);
        ProviderManager providerManager = new ProviderManager(hustleHubUsernameAuthenticationPassword);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }
}

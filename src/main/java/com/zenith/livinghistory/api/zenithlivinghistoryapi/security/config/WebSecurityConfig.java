package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.CustomCorsFilter;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.RestAuthenticationEntryPoint;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.request.RequestAuthenticationProvider;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.request.LoginProcessingFilter;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt.JwtAuthenticationProvider;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt.SkipPathRequestMatcher;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt.extractor.TokenExtractor;


/**
 * Custom security configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //region Private Members

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private RequestAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    //endregion

    //region Public Properties

    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";

    public static final String LOGIN_ENTRY_POINT = "/api/v1/auth/signin";

    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/v1/**";

    public static final String REGISTRATION_ENTRY_POINT = "/api/v1/auth/signup";

    public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/v1/auth/token";

    //endregion

    //region Public Methods

    /**
     * Builds process filter for login.
     * @return - Login Process Filter.
     * @throws Exception
     */
    protected LoginProcessingFilter buildLoginProcessingFilter() throws Exception {

        LoginProcessingFilter filter = new LoginProcessingFilter(
                LOGIN_ENTRY_POINT,
                successHandler,
                failureHandler,
                objectMapper
        );
        filter.setAuthenticationManager(this.authenticationManager);

        return filter;
    }

    /**
     * Builds JWT filter.
     * @return - JWT Process Filter.
     * @throws Exception
     */
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {

        List<String> pathsToSkip = Arrays.asList(
                TOKEN_REFRESH_ENTRY_POINT,
                LOGIN_ENTRY_POINT,
                REGISTRATION_ENTRY_POINT
        );
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(
                failureHandler,
                tokenExtractor,
                matcher
        );
        filter.setAuthenticationManager(this.authenticationManager);

        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(authenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

            // Redirect all requests to entry point.
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(this.authenticationEntryPoint)

            // Configure sesssion.
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // Configure end points do not require authentication.
            .and()
            .authorizeRequests()
            .antMatchers(LOGIN_ENTRY_POINT).permitAll() // Login end-point
            .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll() // Token refresh end-point
            .antMatchers(REGISTRATION_ENTRY_POINT).permitAll() // Registration end-point

            // Configure protection of end points require authentication.
            .and()
            .authorizeRequests()
            .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected API End-points

            // Configure filtering classes.
            .and()
            .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    //endregion
}

package com.zenith.livinghistory.api.zenithlivinghistoryapi;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 *  CORS filter.
 */
public class CustomCorsFilter extends CorsFilter {

    //region Constructor

    /**
     * Ctor.
     */
    public CustomCorsFilter() {

        super(getConfigurationSource());

    }

    //endregion

    //region Private Methods

    /**
     * Creates custom config for cors settings.
     * @return - Config.
     */
    private  static UrlBasedCorsConfigurationSource getConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(36000L);
        config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return  source;
    }

    //endregion
}

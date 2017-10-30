package com.zenith.livinghistory.api.zenithlivinghistoryapi.security.auth.jwt;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

/**
 * Skips predefined paths for authentication filter.
 */
public class SkipPathRequestMatcher implements RequestMatcher {

    //region Private Members

    private OrRequestMatcher matchers;

    private RequestMatcher processingMatcher;

    //endregion

    //region Constructor

    /**
     * Ctor.
     * @param pathsToSkip - List of paths will be skipped.
     * @param processingPath - Processing path.
     */
    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {

        Assert.notNull(pathsToSkip);

        List<RequestMatcher> matches = pathsToSkip.stream()
                .map(path -> new AntPathRequestMatcher(path))
                .collect(Collectors.toList());

        matchers = new OrRequestMatcher(matches);
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }
    //endregion

    //region Public Methods

    /**
     * Return whether requesting path matches with one of the skipped paths.
     * @param request - Request.
     * @return
     */
    @Override
    public boolean matches(HttpServletRequest request) {

        if (matchers.matches(request))
            return false;

        return processingMatcher.matches(request) ? true : false;
    }
    //endregion
}
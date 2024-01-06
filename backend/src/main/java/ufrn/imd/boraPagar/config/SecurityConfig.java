package ufrn.imd.boraPagar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import ufrn.imd.boraPagar.user.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.GET, "/subjects").anonymous()
                    .requestMatchers(HttpMethod.GET, "/subjects/*").anonymous()
                    .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                    .defaultSuccessUrl("/")
                    .userInfoEndpoint(userInfo -> userInfo
                        .oidcUserService(this.oidcUserService())
                    )
                )
                .build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return (userRequest) -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);
            customUserDetailsService.processUserDetails(oidcUser);
            return oidcUser;
        };
    }

}

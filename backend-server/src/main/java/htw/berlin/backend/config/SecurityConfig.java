package htw.berlin.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
public class SecurityConfig {

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/webjars/**");
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/oauth2/authorization/writer-client-authorization-code"))
                .oauth2Client(withDefaults());
        return http.build();
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/try/{chartId}").permitAll()
//                .antMatchers("/authorize**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/callback/").permitAll()
//                .antMatchers("/error**").permitAll()
//                .antMatchers("/test").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login(oauth2Login ->
//                        oauth2Login.loginPage("/oauth2/authorization/writer-client-authorization-code"))
//                .oauth2Client(withDefaults());
//        return http.build();

//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/try/{chartId}").permitAll()
//                .antMatchers("/error**").permitAll()
//                .antMatchers("/test").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Client(withDefaults());
//        return http.build();
    }

}


//oauth2Login.loginPage("/oauth2/authorization/writer-client-oidc"))
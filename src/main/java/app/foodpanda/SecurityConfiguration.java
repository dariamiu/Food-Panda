package app.foodpanda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers(HttpMethod.POST,"/customers").permitAll()
                    .antMatchers(HttpMethod.GET,"/customer").permitAll()
                    .antMatchers(HttpMethod.POST,"/admins").permitAll()
                    .antMatchers(HttpMethod.GET,"/admin").permitAll()
                    .anyRequest().authenticated();

    }
}

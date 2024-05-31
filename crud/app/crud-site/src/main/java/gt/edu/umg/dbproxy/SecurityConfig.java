package gt.edu.umg.dbproxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	
	@Value("${spring.security.debug:false}")
    boolean securityDebug;

	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
  
        UserDetails admin = User.withUsername("admin") 
                .password(bCryptPasswordEncoder.encode("123")) 
                .roles("ADMIN", "USER") 
                .build(); 
  
        UserDetails user = User.withUsername("usuario") 
                .password(bCryptPasswordEncoder.encode("123")) 
                .roles("USER") 
                .build(); 
        return new InMemoryUserDetailsManager(admin, user); 
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
		authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
		.requestMatchers("/admin/**").hasAnyRole("ADMIN")
        .anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(httpSecuritySessionManagementConfigurer -> 
		httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.formLogin((login) -> login.defaultSuccessUrl("/admin", true).permitAll())
		.logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
		
		return http.build();
    }
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.debug(securityDebug)
                .ignoring()
                .requestMatchers("/imagenes/**", "/favicon.ico");
    }
	
    
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

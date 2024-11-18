package spring.jpa;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig
{
@Bean
public PasswordEncoder passwordEncoder() {
return NoOpPasswordEncoder.getInstance();
}
@Autowired // pour l'injection de dépendances
//Définir les utilisateurs et leurs rôles
public void
globalConfig(AuthenticationManagerBuilder auth ,
DataSource dataSource) throws Exception {

// configuration en mémoire
/*
auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN","USER");
auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
*/
//configuration en BD
auth.jdbcAuthentication()
.dataSource(dataSource)
.usersByUsernameQuery("select username as principal" +
", password as credentials, true from users where username =? ")
.authoritiesByUsernameQuery("select user_username as" +
" principal , roles_role as role from users_roles where " +
"user_username =?")
.rolePrefix("ROLE_");
}
@Bean
public SecurityFilterChain filterChain(HttpSecurity
http) throws Exception {

http
.csrf().disable()//désactiver la protection contre CSRF
.authorizeRequests()
.anyRequest()
.authenticated() // il faut s'authentifier pour accéder à toutes les URLs
.and()
.formLogin()
.loginPage("/login") //page d'authentification (template)
.permitAll()

.defaultSuccessUrl("/accueil",true)//page d’accueil

.and()
.logout()
.invalidateHttpSession(true)
.logoutUrl("/logout")
.permitAll()
;
return http.build();
}}
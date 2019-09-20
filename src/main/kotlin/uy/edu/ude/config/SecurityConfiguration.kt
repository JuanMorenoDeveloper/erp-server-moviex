package uy.edu.ude.config

import org.springframework.http.HttpMethod.DELETE
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.PATCH
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpMethod.PUT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

  @Autowired
  private val jdbcTemplate: JdbcTemplate? = null

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return NoOpPasswordEncoder.getInstance()
  }

  @Autowired
  @Throws(Exception::class)
  fun configureGlobal(auth: AuthenticationManagerBuilder) {
    auth.jdbcAuthentication().dataSource(jdbcTemplate!!.dataSource)
        .usersByUsernameQuery(
            "select login, password, enabled from usuario where login=?")
        .authoritiesByUsernameQuery(
            "select u.login, r.nombre from usuario u left join usuario_roles ur "
                + "on ur.usuario_id=u.id left join rol r "
                + "on ur.roles_id=r.id where u.login=?")
        .passwordEncoder(passwordEncoder())
  }

  @Throws(Exception::class)
  override fun configure(httpSecurity: HttpSecurity) {
    val ROLE_ADMIN = "ADMIN"
    val ROLE_USER = "USER"
    httpSecurity.authorizeRequests()
        .antMatchers(GET, "/usuario/**")
        .hasAnyRole(ROLE_ADMIN, ROLE_USER)
        .antMatchers(POST, "/pelicula/**")
        .hasRole(ROLE_ADMIN)
        .antMatchers(PUT, "/pelicula/**")
        .hasRole(ROLE_ADMIN)
        .antMatchers(DELETE, "/pelicula/**")
        .hasRole(ROLE_ADMIN)
        .antMatchers(PATCH, "/pelicula/**")
        .hasRole(ROLE_ADMIN)
        .antMatchers(GET, "/pelicula/**")
        .hasRole(ROLE_ADMIN)
        .antMatchers(GET, "/genero/**")
        .hasAnyRole(ROLE_ADMIN, ROLE_USER)
        .antMatchers(POST, "/genero/**")
        .denyAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/browser/**").permitAll()
        .antMatchers("/v2/api-docs/**").permitAll()
    httpSecurity.httpBasic()
    httpSecurity.csrf().disable()
    httpSecurity.headers().frameOptions().disable()
  }

  @Bean
  public override fun userDetailsService(): JdbcUserDetailsManager {
    val manager = JdbcUserDetailsManager()
    manager.jdbcTemplate = jdbcTemplate
    return manager
  }
}

package com.yongback.portfolio.admin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class AdminSecurityConfiguration {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests {
                it.requestMatchers("/admin/**")
                    .authenticated()
                    .anyRequest()
                    .permitAll()
            }.formLogin {
                it.defaultSuccessUrl("/admin")
            }.logout {
                it.logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/")
            }.build()
    }
}

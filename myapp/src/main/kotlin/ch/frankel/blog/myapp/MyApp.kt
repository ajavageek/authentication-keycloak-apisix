package ch.frankel.blog.myapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
class MyApp

fun beans() = beans {
    bean {
        ref<HttpSecurity>().authorizeHttpRequests {
            it.requestMatchers("/*")
                .hasAuthority("OIDC_USER")
                .anyRequest()
                .permitAll()
        }.oauth2Login {}
        .build()
    }
}

fun main(args: Array<String>) {
    runApplication<MyApp>(*args) {
        addInitializers(beans())
    }
}

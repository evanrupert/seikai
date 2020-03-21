package io.studioreach.seikai

import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class SeikaiConfig {
    @Bean
    @Profile("default")
    fun db(): MongoOperations = MongoTemplate(MongoClients.create(), "seikai")

    @Bean
    @Profile("test")
    fun testDb(): MongoOperations = MongoTemplate(MongoClients.create(), "seikai_test")
}

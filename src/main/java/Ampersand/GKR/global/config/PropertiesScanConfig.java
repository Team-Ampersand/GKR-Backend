package Ampersand.GKR.global.config;

import Ampersand.GKR.global.gauth.properties.GAuthProperties;
import Ampersand.GKR.global.redis.properties.RedisProperties;
import Ampersand.GKR.global.security.jwt.properties.JwtProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(
        basePackageClasses = {
                JwtProperties.class,
                GAuthProperties.class,
                RedisProperties.class
        }
)
public class PropertiesScanConfig {
}

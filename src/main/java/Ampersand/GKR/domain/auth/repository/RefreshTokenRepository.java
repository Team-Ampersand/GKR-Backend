package Ampersand.GKR.domain.auth.repository;

import Ampersand.GKR.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    RefreshToken findByUserId(UUID userId);
}

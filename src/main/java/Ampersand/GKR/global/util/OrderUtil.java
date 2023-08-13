package Ampersand.GKR.global.util;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.exception.ApplicationNotFoundException;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderUtil {

    private final ApplicationRepository applicationRepository;

    public Application findApplicationById(Long id) {

        return applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException());
    }
}
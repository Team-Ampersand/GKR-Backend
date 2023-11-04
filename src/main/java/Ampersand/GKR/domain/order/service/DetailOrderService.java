package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.exception.ApplicationNotFoundException;
import Ampersand.GKR.domain.order.presentation.dto.response.ApplicationResponse;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class DetailOrderService {

    private final ApplicationRepository applicationRepository;

    public ApplicationResponse execute(Long id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException());

        ApplicationResponse applicationResponse = ApplicationResponse.toResponse(application);

        return applicationResponse;
    }
}

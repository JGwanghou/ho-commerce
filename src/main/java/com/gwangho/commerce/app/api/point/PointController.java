package com.gwangho.commerce.app.api.point;

import com.gwangho.commerce.app.application.PointFacade;
import com.gwangho.commerce.app.domain.point.service.PointCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class PointController {

    private final PointFacade pointFacade;

    @GetMapping("{userId}/points")
    // TODO : 사용자 권한(JWT 등 추가해야함)
    public ResponseEntity<?> getUserPoint(@PathVariable Long userId){
        return null;
    }

    @PostMapping("{userId}/points")
    public PointResponse chargePoint(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @PathVariable Long userId,
            @RequestBody @Valid PointChargeRequest charge
    ) {
        return pointFacade.charge(userId, new PointCommand.ChargePoint(charge.getChargeAmount()));
    }

}

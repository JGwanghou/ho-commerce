package com.gwangho.commerce.app.api.point;

import com.gwangho.commerce.app.application.PointFacade;
import com.gwangho.commerce.app.domain.point.service.PointCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public PointDto.PointResponse chargePoint(
            @PathVariable Long userId,
            @RequestBody @Valid PointDto.PointCreate charge
    ) {
        return pointFacade.charge(userId, new PointCommand.ChargePoint(charge.chargeAmount()));
    }

}

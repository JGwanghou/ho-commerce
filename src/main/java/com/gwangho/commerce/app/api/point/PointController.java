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
    public ResponseEntity<PointViewResponse> getUserPoint(@PathVariable Long userId){
        PointViewResponse userPointInto = pointFacade.getUserPointInto(userId);

        return ResponseEntity.ok()
                .body(userPointInto);
    }

    @PostMapping("{userId}/points")
    public ResponseEntity<PointViewResponse> chargePoint(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @PathVariable Long userId,
            @RequestBody @Valid PointChargeRequest charge
    ) throws Exception {
        PointViewResponse response = pointFacade.charge(idempotencyKey, userId, new PointCommand.ChargePoint(charge.getChargeAmount()));

        return ResponseEntity.ok()
                .body(response);
    }

}

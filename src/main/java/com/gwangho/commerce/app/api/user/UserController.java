package com.gwangho.commerce.app.api.user;

import com.gwangho.commerce.app.application.UserFacade;
import com.gwangho.commerce.app.domain.payment.service.PaymentCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("{userId}")
    // TODO : 사용자 권한(JWT 등 추가해야함)
    public ResponseEntity<UserPointViewResponse> getUserPoint(@PathVariable Long userId){
        UserPointViewResponse userPointInto = userFacade.getUserPointInto(userId);

        return ResponseEntity.ok()
                .body(userPointInto);
    }

    @PostMapping("{userId}/charge")
    public ResponseEntity<UserPointViewResponse> chargePoint(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @PathVariable Long userId,
            @RequestBody @Valid UserPointChargeRequest charge
    ) throws Exception {
        UserPointViewResponse response = userFacade.charge(idempotencyKey, userId, new PaymentCommand.ChargePoint(charge.getChargeAmount()));

        return ResponseEntity.ok()
                .body(response);
    }

}

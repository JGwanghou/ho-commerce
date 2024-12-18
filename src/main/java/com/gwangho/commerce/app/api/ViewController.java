package com.gwangho.commerce.app.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/index")
    public String index() {
        return "redirect:/"; // 루트 경로로 리다이렉트
    }

    @GetMapping("/index2")
    public String index2(Model model) {
        Optional<User> user = userService.findById(999L);

        model.addAttribute("user", user);
        return "page.html"; // 루트 경로로 리다이렉트
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }



    @RequestMapping(value = "/confirm")
    public ResponseEntity<JsonNode> confirmPayment(@RequestBody String jsonBody) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode requestData = objectMapper.readTree(jsonBody);
            String paymentKey = requestData.get("paymentKey").asText();
            String orderId = requestData.get("orderId").asText();
            String amount = requestData.get("amount").asText();

            // JSON 객체 생성
            ObjectNode obj = objectMapper.createObjectNode();
            obj.put("orderId", orderId);
            obj.put("amount", amount);
            obj.put("paymentKey", paymentKey);

            // 토스페이먼츠 API 인증 설정
            String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
            String authorizations = "Basic " + new String(encodedBytes);

            // URL 연결 설정
            URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 요청 데이터 전송
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(objectMapper.writeValueAsBytes(obj));
            }

            // 응답 코드 확인
            int code = connection.getResponseCode();
            boolean isSuccess = code == 200;

            // 응답 스트림 읽기
            InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

            // 응답 파싱
            JsonNode jsonResponse = objectMapper.readTree(responseStream);
            responseStream.close();

            return ResponseEntity.status(code).body(jsonResponse);

        } catch (Exception e) {
            throw new RuntimeException("Payment confirmation failed", e);
        }
    }
}

package com.sparta.paymentsystem.domain.member.controller;

import com.sparta.paymentsystem.domain.member.dto.MemberResponse;
import com.sparta.paymentsystem.domain.member.service.MemberService;
import com.sparta.paymentsystem.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MemberResponse>> me(@AuthenticationPrincipal Long memberId) {
        return ResponseEntity.ok(ApiResponse.ok(memberService.getMe(memberId)));
    }
}
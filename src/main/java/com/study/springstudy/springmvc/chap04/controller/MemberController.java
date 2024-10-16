package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.dto.request.LoginRequestDto;
import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Member;
import com.study.springstudy.springmvc.chap04.service.LoginResult;
import com.study.springstudy.springmvc.chap04.service.MemberService;
import com.study.springstudy.springmvc.util.FileUtils;
import com.study.springstudy.springmvc.util.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Value("${file.upload.root-path}")
    private String rootPath;

    private final MemberService memberService;
    private final MailSenderService mailSenderService;

    // 회원가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp() {
        log.info("/members/sign-up: GET");
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(@RequestParam String type,
                                   @RequestParam String keyword) {
        log.info("/members/check: async GET!");
        log.debug("type: {}, keyword: {}", type, keyword);

        boolean flag = memberService.checkIdentifier(type, keyword);
        return ResponseEntity.ok()
                .body(flag);
    }

    @PostMapping("/sign-up")
    public String signUp(@Validated SignUpRequestDto dto) {
        log.info("/members/sign-up: POST!");
        log.info("param: {}", dto);

        // 서버 로컬 경로에 파일 업로드 지시
        String savePath = FileUtils.uploadFile(dto.getProfileImage(), rootPath);

        // 일반 방식 (우리 사이트를 통해)으로 회원가입
        dto.setLoginMethod(Member.LoginMethod.COMMON);

        boolean flag = memberService.join(dto, savePath);
        return flag ? "redirect:/members/sign-in" : "redirect:/members/sign-up";
    }

    // 로그인 화면 요청
    @GetMapping("/sign-in")
    public void signIn() {
    }

    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDto dto,
                         // Model에 담긴 데이터는 리다이렉트 시 jsp로 전달되지 못함.
                         // 리다이렉트는 응답이 나갔다가 재요청이 들어오는데, 그 과정에서
                         // 응답이 나가는 순간 모델이 소멸함.
                         RedirectAttributes ra,
                         HttpServletResponse response,
                         HttpServletRequest request) {

        // 자동 로그인 서비스를 추가하기 위해 세션과 응답객체도 함께 전달.
        LoginResult result = memberService.authenticate(dto, request.getSession(), response);
        // redirect에서 데이터를 일회성으로 전달할 때 사용하는 메서드.
        ra.addFlashAttribute("result", result);

        if (result == LoginResult.SUCCESS) { // 로그인 성공
            // 로그인을 했다는 정보를 계속 유지하기 위한 수단으로 쿠키를 사용하자.
            // makeLoginCookie(dto, response);

            // 세션으로 로그인을 유지
            // 서비스에게 세션 객체와 아이디를 전달.
            memberService.maintainLoginState(request.getSession(), dto.getAccount());


            return "redirect:/board/list";
        }
        return "redirect:/members/sign-in"; // 로그인 실패 시
    }

    // 로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(HttpSession session,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        // 자동 로그인 중인지 확인
        if (WebUtils.getCookie(request, "auto") != null) {
            // 쿠키를 없애주고, DB 데이터도 원래대로 돌려놔야 한다.
            memberService.autoLoginClear(request, response);
        }

        // 세션에서 로그인 정보 기록 삭제
        session.removeAttribute("login");

        // 세션 전체 무효화 (초기화)
        session.invalidate();

        return "redirect:/";
    }

    // 연습용 이메일 폼 화면
    @GetMapping("/email")
    public String emailForm() {
        return "email/email-form";
    }

    @PostMapping("/email")
    @ResponseBody
    public ResponseEntity<?> mailCheck(@RequestBody String email) {
        log.info("이메일 인증 요청 들어옴!: {}", email);

        try {
            String authNum = mailSenderService.joinMail(email);
            return ResponseEntity.ok().body(authNum);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private void makeLoginCookie(LoginRequestDto dto, HttpServletResponse response) {
        // 쿠키에 로그인 기록을 저장
        // 쿠키 객체를 생성 -> 생성자의 매개값으로 쿠키의 이름과 저장할 값을 전달. (문자열만 가능, 4KB)
        Cookie cookie = new Cookie("login", dto.getAccount());

        // 쿠키 세부 설정
        cookie.setMaxAge(60); // 쿠키 수명 설정(초)
        cookie.setPath("/"); // 이 쿠키는 모든 경로에서 유효하다.

        // 쿠키가 완성되었다면 응답 객체에 쿠키를 태워서 클라이언트로 전송
        response.addCookie(cookie);
    }

}















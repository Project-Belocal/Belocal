package kr.co.belocal.web.handler;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Slf4j
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String prevPage = (String) request.getSession().getAttribute("prevPage");

        log.info("prevPage {}",prevPage);

        if (prevPage=="http://www.tobelocal.site/sign-up"){
            response.sendRedirect("/");
        }

        if(prevPage != null) {
            request.getSession().removeAttribute("prevPage");
            response.sendRedirect(prevPage);
        } else {
            response.sendRedirect("/");
        }
        // HttpSession session = request.getSession(false);
        // System.out.println("---------------------------------------");
        // System.out.println(session);
        // if(session != null) {
        //     SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        //     System.out.println(savedRequest);
        //     System.out.println(savedRequest.getRedirectUrl());
        //     if(savedRequest != null && savedRequest.getRedirectUrl() != null) {
        //         System.out.println(savedRequest);
        //         System.out.println(savedRequest.getRedirectUrl());
        //         String redirectUrl = savedRequest.getRedirectUrl();
        //         response.sendRedirect(redirectUrl);
        //     } else {
        //         response.sendRedirect("/");
        //     }
        // }
    }
}

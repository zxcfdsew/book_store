package com.store.book.aspect;

import com.store.book.dto.request.ReqSignupDto;
import com.store.book.repository.UserMapper;
import com.store.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidAspect {

    private final UserService userService;

    @Pointcut("@annotation(com.store.book.aspect.annotation.SignupValidAop)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;
        ReqSignupDto dto = null;

        for (Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            } else if (arg.getClass() == ReqSignupDto.class) {
                dto = (ReqSignupDto) arg;
            }
        }

        if (!dto.getPassword().equals(dto.getCheckPassword())) {
            bindingResult.addError(new FieldError(
                    "checkPassword",
                    "checkPassword",
                    "비밀번호가 일치하지 않습니다."
            ));
        }

        if (userService.checkDuplicateUsername(dto.getUsername())) {
            bindingResult.addError(new FieldError(
                    "duplicateUsername",
                    "duplicateUsername",
                    "이미 존재하는 아이디입니다."
            ));
        }

        if (userService.checkDuplicateEmail(dto.getEmail())) {
            bindingResult.addError(new FieldError(
                    "duplicateEmail",
                    "duplicateEmail",
                    "이미 존재하는 이메일입니다."
            ));
        }

        return proceedingJoinPoint.proceed();
    }

}

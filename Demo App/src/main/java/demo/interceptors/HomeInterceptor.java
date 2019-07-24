package demo.interceptors;

import org.springframework.http.HttpMethod;
import demo.api.services.InterceptorService;
import org.springframework.stereotype.Component;
import demo.exceptions.UnauthorizedEcxeption;
import demo.api.repositories.UsersRepository;
import demo.api.repositories.UsersTokensRepository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HomeInterceptor implements InterceptorService, HandlerInterceptor {
    private UsersRepository userRepo;
    private UsersTokensRepository usersTokensRepo;

    @Autowired
    public HomeInterceptor(UsersRepository userRepo, UsersTokensRepository usersTokensRepo) {
        this.userRepo = userRepo;
        this.usersTokensRepo = usersTokensRepo;
    }

    @Override
    public boolean checkToken(String token) {
        Integer id = usersTokensRepo.getUserIdForToken(token);
        return id != null && userRepo.getUser(id) != null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString()) || checkToken(request.getHeader("authorization"))) return true;

        throw new UnauthorizedEcxeption();
    }
}

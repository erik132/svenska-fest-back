package erik.soekov.svenska_fest_back.controller;


import erik.soekov.svenska_fest_back.dto.*;
import erik.soekov.svenska_fest_back.security.JwtTokenProvider;
import erik.soekov.svenska_fest_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http//localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            request.verifyFields();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new LoginResponse("success", "Login successful", token, request.getUsername()));
        } catch (RequestVerificationException rve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("error", "Incorrect fields: " + rve.getMessage()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("error", "Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse("error", "Authentication failed: " + e.getMessage()));
        }
    }

    @PostMapping("/create_user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            request.verifyFields();
            userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CreateUserResponse("success", "User created successfully", request.getUsername()));
        } catch (RequestVerificationException rve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateUserResponse("error", "Incorrect fields: " + rve.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new CreateUserResponse("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateUserResponse("error", "Failed to create user: " + e.getMessage()));
        }
    }
}

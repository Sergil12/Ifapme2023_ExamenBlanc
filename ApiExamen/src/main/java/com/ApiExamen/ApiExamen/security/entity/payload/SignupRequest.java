package com.ApiExamen.ApiExamen.security.entity.payload;
import com.ApiExamen.ApiExamen.Entity.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest{
    private String username;
    private String gender;
    private String password;
    private String firstname;
    private String lastname;

    public ApiResponse isValid(){
        if(username == null ||username.isEmpty()){
            if(password == null ||password.isEmpty()){
                return new ApiResponse(false, null, "api.signup.valid-error-full");
            }else{
                return new ApiResponse(false, null, "api.signup.valid-error-email");
            }
        }else if(password == null ||password.isEmpty()){
            return new ApiResponse(false, null, "api.signup.valid-error-password");
        }else if(firstname == null ||firstname.isEmpty()){
            return new ApiResponse(false, null, "api.signup.valid-error-firstname");
        }else if(lastname == null ||lastname.isEmpty()){
            return new ApiResponse(false, null, "api.signup.valid-error-lastname");
        }else{
            return new ApiResponse(true, this, null);
        }
    }
}


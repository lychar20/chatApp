package fr.charly.chatApp.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterPostDTO {

    @NotBlank(message = "Please, give a proper name")
    @Size(message = "The account name must have at least 5 characters", min = 5)
    private String nickname;

    @NotBlank(message = "Please, the password must have a value")
    @Size(message = "The password must have at least 8 characters", min = 8)
    private String password;

    @Email
    private String email;

    @NotBlank
    private String birthAt; //birthedAt

}

package fr.charly.chatApp.DTO;


import fr.charly.chatApp.entity.Category;
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
public class ThreadDTO {

    @NotBlank(message = "Please, give a proper name")
    private String title;

    @NotBlank
    private Category category;


}

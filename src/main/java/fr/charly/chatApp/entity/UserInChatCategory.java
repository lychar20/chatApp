package fr.charly.chatApp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInChatCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    private Category category;
//
//    private User user;

    private Map<Category, List<User>> userInChatCategory;

}

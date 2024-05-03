package fr.charly.chatApp.service;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.ChatRoom;
import fr.charly.chatApp.repository.CategoryRepository;
import fr.charly.chatApp.repository.ChatRoomRepository;
import fr.charly.chatApp.service.interfaces.DAOFindAllInterface;
import fr.charly.chatApp.service.interfaces.DAOFindBySlugInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatRoomService  {


    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomId(
            String chatterSenderId,
            String chatterReceiverId,
            boolean createNewRoomIfNotExists
    ) {
        return chatRoomRepository
                .findByChatterSenderIdAndChatterReceiverId(chatterSenderId, chatterReceiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists) {
                        var chatId = createChatId(chatterSenderId, chatterReceiverId);
                        return Optional.of(chatId);
                    }

                    return  Optional.empty();
                });
    }

    private String createChatId(String chatterSenderId, String chatterReceiverId) {
        var chatId = String.format("%s_%s", chatterSenderId, chatterReceiverId);

        ChatRoom senderRecipient = ChatRoom
                .builder()
                .chatId(chatId)
                .chatterSenderId(chatterSenderId)
                .chatterReceiverId(chatterReceiverId)
                .build();

        ChatRoom recipientSender = ChatRoom
                .builder()
                .chatId(chatId)
                .chatterSenderId(chatterReceiverId)
                .chatterReceiverId(chatterSenderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }


}

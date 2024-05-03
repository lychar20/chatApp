package fr.charly.chatApp.service;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.ChatRoomMessage;
import fr.charly.chatApp.repository.CategoryRepository;
import fr.charly.chatApp.repository.ChatMessageRoomRepository;
import fr.charly.chatApp.service.interfaces.DAOFindAllInterface;
import fr.charly.chatApp.service.interfaces.DAOFindBySlugInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatMessageRoomService  {


    private ChatMessageRoomRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatRoomMessage save(ChatRoomMessage chatRoomMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatRoomMessage.getChatterSenderId(), chatRoomMessage.getChatterReceiverId(), true)
                .orElseThrow(); // You can create your own dedicated exception
        chatRoomMessage.setChatId(chatId);
        repository.save(chatRoomMessage);
        return chatRoomMessage;
    }

    public List<ChatRoomMessage> findChatMessages(String chatterSenderId, String chatterReceiverId) {
        var chatId = chatRoomService.getChatRoomId(chatterSenderId, chatterReceiverId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }


}

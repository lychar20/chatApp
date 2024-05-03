package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.ChatRoom;
import fr.charly.chatApp.entity.ChatRoomMessage;
import fr.charly.chatApp.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRoomRepository extends JpaRepository<ChatRoomMessage, Long>, EntityNomenclatureRepository<ChatRoomMessage> {

    List<ChatRoomMessage> findByChatId(String chatId);

}

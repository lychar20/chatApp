package fr.charly.chatApp.repository;

import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.ChatRoom;
import fr.charly.chatApp.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, EntityNomenclatureRepository<ChatRoom> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String chatterSenderId, String chatterReceiverId);

}

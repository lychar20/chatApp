'use strict';

function init(event) {
    // pour avoir le message
    var messageInput = document.querySelector('#message');
    // lee bouton d'envoie
    var sendButton = document.querySelector('#sendMessage');

    // pour l'afficher
    var messageArea = document.querySelector('#messageArea');

    // pour avoir la catégorie
    var category = document.querySelector('#categoryChoice');

    var username = null;
    var stompClient = null;

    var colors = [
        '#2196F3', '#32c787', '#00BCD4', '#ff5652',
        '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
    ];

    // l'arrivée sur la page envoie un message de connection
    function connect(event) {
        username = document.querySelector('#name').value.trim();

        if (username) {
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, onConnected);
        }
        event.preventDefault();
    }

    function onConnected() {
        // Subscribe to the Public Topic
        stompClient.subscribe('/topic/public/'+category.value, onMessageReceived);

        // Tell your username to the server
        stompClient.send("/app/chat.addUser/"+category.value,
            {},
            JSON.stringify({ sender: username, type: 'JOIN' })
        )
    }

    // l'envoie de message sur le chat de la catégorie
    function sendMessage(event) {
        var messageContent = messageInput.value.trim();
        if (messageContent && stompClient) {
            var chatMessage = {
                sender: username,
                content: messageInput.value,
                type: 'CHAT'
            };
            stompClient.send("/app/chat.sendMessage/"+category.value, {}, JSON.stringify(chatMessage));
            messageInput.value = '';
        }
        event.preventDefault();
    }

    // la reeception de message et l'affichage
    function onMessageReceived(payload) {
        var message = JSON.parse(payload.body);

        var messageElement = document.createElement('li');

        // en fonction du type de mesage (connection, deconnection, message)
        // on rajoute les classes CSS pour les afficher différement

        if (message.type === 'JOIN') {
            messageElement.classList.add('event-message');
            message.content = message.sender + ' joined!';
        } else if (message.type === 'LEAVE') {
            messageElement.classList.add('event-message');
            message.content = message.sender + ' left!';
        } else {
            messageElement.classList.add('chat-message');

            var avatarElement = document.createElement('i');
            var avatarText = document.createTextNode(message.sender[0]);
            avatarElement.appendChild(avatarText);
            avatarElement.style['background-color'] = getAvatarColor(message.sender);

            messageElement.appendChild(avatarElement);

            var usernameElement = document.createElement('span');
            var usernameText = document.createTextNode(message.sender);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);
        }

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
        messageElement.scrollIntoView();
    }

    function updateScroll(){
        var element = document.getElementById("cc-height-messageArea");
        element.scrollTop = element.scrollHeight;
    }

    function getAvatarColor(messageSender) {
        var hash = 0;
        for (var i = 0; i < messageSender.length; i++) {
            hash = 31 * hash + messageSender.charCodeAt(i);
        }
        var index = Math.abs(hash % colors.length);
        return colors[index];
    }

    connect(event);
    sendButton.addEventListener('click', sendMessage);
}

window.addEventListener('load', (event) => {
    init(event);
});


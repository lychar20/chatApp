<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<jsp:include flush="true" page="../base.jsp"/>


<div class="cc-background5 px-5 pt-5">
    <!-- pour avoir le username et la category dans le js -->
    <input type="text" id="name" value="${userLogged.nickname}" class="d-none"/>
    <input type="text" id="categoryChoice" value="${categoryChoice.slug}" class="d-none"/>


    <!-- pour avoir le display des User déja connectés -->

    <div class="chat-container hidden" id="chat-page">

        <div class="users-list">
            <div class="users-list-container">
                <h2>Online Users</h2>
                <ul id="connectedUsers">
                </ul>
            </div>
            <div>
                <p id="connected-user-fullname"></p>
            </div>



    </div>


    <div class="chat-container">
    <div class="cc-height-messageArea">
        <!-- affichage des messages du chat général-->
        <ul id="messageArea">

        </ul>
    </div>

        <!-- formulaire d envoie de message -->
        <div>
            <div class="d-flex form-group cc-message-chat">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control cc-width-chat"/>
                    <button class="bg-info cc-width-button" id="sendMessage">Send</button>
                </div>
            </div>
        </div>
    </div>
</div>



<%@ include file="../../jsp/footer.jsp" %>
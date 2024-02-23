<!-- <%@ include file="../../jsp/tag.jsp" %> -->
<jsp:include flush="true" page="../base.jsp"/>


<div>
    <!-- pour avoir le username dans le js -->
    <input type="text" id="name" value="${userLogged.nickname}" class="d-none"/>
    <input type="text" id="categoryChoice" value="${categoryChoice.slug}" class="d-none"/>

    <div class="chat-container">
        <!-- affichage des messages -->
        <ul id="messageArea">

        </ul>

        <!-- formulaire d'envoie de message -->
        <div id="messageForm" name="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                    <button class="primary" id="sendMessage">Send</button>
                </div>
            </div>
        </div>
    </div>
</div>



<%@ include file="../../jsp/footer.jsp" %>
<!-- <%@ include file="../../webapp/WEB-INF/jsp/tag.jsp" %> -->
<jsp:include flush="true" page="../base.jsp"/>


<div>
    <!-- pour avoir le username dans le js -->
    <input type="text" id="name" value="${userLogged.nickname}" autocomplete="off" class="hidden"/>
    <div class="chat-container">
        <!-- affichage des messages -->
        <ul id="messageArea">

        </ul>

        <!-- formulaire d'envoie de message -->
        <form id="messageForm" name="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                    <button type="submit" class="primary">Send</button>
                </div>
            </div>
        </form>
    </div>
</div>

 <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/main.js"></script>
<!--</body>
</html> -->

<%@ include file="../../webapp/WEB-INF/jsp/footer.jsp" %>
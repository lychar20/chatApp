package fr.charly.chatApp.mapping;

public class UrlRoute {

    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTER = "/register";
    public final static String URL_ADMIN = "/admin";

    public final static String URL_COMMENT_MESSAGE_MAPPING = "/chat.addUser";
    public final static String URL_COMMENT_SEND_TO = "/topic.public";
    public final static String URL_SALON_CHAT = "/chat";
    public final static String URL_SALON_NAME = URL_SALON_CHAT + "/{slug}";

    public final static String URL_FORUM = "/forum";

    public final static String URL_FORUM_NEW = URL_FORUM + "/nouveau";
    public final static String URL_FORUM_NAME = URL_FORUM + "/{slug}";

    public final static String URL_FORUM_NAME_COMMENTS = URL_FORUM_NAME + "/{threadSlug}";
    public final static String URL_FORUM_COMMENT_MODERATE = URL_FORUM_NAME_COMMENTS + "/moderate" ;
    public final static String URL_FORUM_COMMENT_MODERATE_PATH = URL_FORUM_COMMENT_MODERATE + "{id}/{moderate}" ;

}

package Controller;
import Model.*;

import Service.WebsiteService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static org.mockito.ArgumentMatchers.nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    WebsiteService websiteService;
    

    public SocialMediaController()
    {
        websiteService = new WebsiteService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI()
     {
        Javalin app = Javalin.create();
        app.post("/register", this::createAccountHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByUserHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void createAccountHandler(Context context) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Account newAccount = mapper.readValue(context.body(), Account.class);
        Account postedAccount = websiteService.registerAccount(newAccount);
        if (postedAccount != null)
        {
            context.json(mapper.writeValueAsString(postedAccount));
            context.status(200);
        }
        else
        {
            context.status(400);
        }
        
    }

    private void loginHandler(Context context) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Account userAccount = mapper.readValue(context.body(), Account.class);
        Account user = websiteService.login(userAccount);
        if (user != null)
        {
            context.json(mapper.writeValueAsString(user));
            context.status(200);
        }
        else
        {
            context.status(401);
        }
    }

    private void createMessageHandler(Context context) throws JsonProcessingException 
    {
        ObjectMapper mapper = new ObjectMapper();
        Message newMessage = mapper.readValue(context.body(), Message.class);
        Message postedMessage = websiteService.createMessage(newMessage);
        if (postedMessage != null)
        {
            context.json(mapper.writeValueAsString(postedMessage));
            context.status(200);
        }
        else
        {
            context.status(400);
        }
    }

    private void getAllMessagesHandler(Context context) {
        List<Message> posts = websiteService.retrieveAllMessages();
        context.json(posts);
    }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message retrievedMessage = websiteService.retrieveMessage(messageId);
        if (retrievedMessage != null)
        {
            context.json(mapper.writeValueAsString(retrievedMessage));
        }
        context.status(200);
    }

    private void deleteMessageByIdHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message deletedMessage = websiteService.deleteMessage(messageId);
        if (deletedMessage != null)
        {
            context.json(mapper.writeValueAsString(deletedMessage));
        }
        context.status(200);
    }

    private void updateMessageByIdHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        System.out.println(context.body());
        String newText = mapper.readValue(context.body(), Message.class).getMessage_text();
        Message updatedMessage = websiteService.updateMessage(messageId, newText);
        if (updatedMessage != null)
        {
            context.json(mapper.writeValueAsString(updatedMessage));
            context.status(200);
        }
        else
        {
            context.status(400);
        }
    }

    private void getAllMessagesByUserHandler(Context context) {
        int userId = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messagesFromUser = websiteService.retrieveMessagesByUser(userId);
        context.json(messagesFromUser);
    }
}
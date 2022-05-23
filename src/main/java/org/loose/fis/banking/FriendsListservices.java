package org.loose.fis.banking;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.loose.fis.authentication.Main;
import org.loose.fis.authentication.model.User;
import org.loose.fis.authentication.services.FileSystemService;
import org.loose.fis.authentication.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FriendsListservices {
    private static List<FriendsList> friends;
    private static final Path FRIENDS_PATH = FileSystemService.getPathToFile("config", "friends.json");

    public static void addFriendsList(String username) {
        friends.add(new FriendsList(username));
        persistFriendsList();
    }

    private static void persistFriendsList() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(FRIENDS_PATH.toFile(), friends);
        } catch (IOException e) {
            throw new CouldNotWriteWalletException();
        }
    }
    public static void loadFriendsListFromFile() throws IOException {

        if (!Files.exists(FRIENDS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("friends.json")), FRIENDS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        friends = objectMapper.readValue(FRIENDS_PATH.toFile(), new TypeReference<List<FriendsList>>() {});
    }

    public static void addFriend(String friendName) throws CouldNotFindUserException{
        for (FriendsList f:friends) {

            if(f.getUsername().equals(Main.getUsername()) &&  !f.getFriendsList().contains(friendName)){
                friends.remove(f);
                f.AddFriends(friendName);
                friends.add(f);
                persistFriendsList();
                return;
            }
        }
        throw new CouldNotFindUserException();
    }

    public static List<String> getFriends() throws CouldNotFindUserException{
        for (FriendsList f:friends) {
            if (Main.getUsername().equals(f.getUsername())){
                return  f.getFriendsList();
            }
        }

        throw new CouldNotFindUserException();
    }
}


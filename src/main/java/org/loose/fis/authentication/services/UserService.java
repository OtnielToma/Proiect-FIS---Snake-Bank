package org.loose.fis.authentication.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.loose.fis.authentication.exceptions.CouldNotWriteUsersException;
import org.loose.fis.authentication.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.authentication.exceptions.UsernameOrPasswordIncorrectException;
import org.loose.fis.authentication.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("users.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addUser(String username, String password,String lastName, String firstName, String gender, String date) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(username, encodePassword(username, password), lastName, firstName, gender, date));
        persistUsers();
    }

    public static void checkPassword(String username, String password) throws UsernameOrPasswordIncorrectException {
        int ok1=0;
        for (User user : users) {

            //if(user.verify(username,encodePassword(username, password))==true)
            if(Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username,password),user.getPassword()))
            ok1=1;


        }
        if(ok1==0)
        throw new UsernameOrPasswordIncorrectException();
        //persistUsers();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static List<User> getUsers(){
        return users;
    }

}

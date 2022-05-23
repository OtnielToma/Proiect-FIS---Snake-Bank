package org.loose.fis.banking;

import org.loose.fis.introduction.model.User;
import org.loose.fis.introduction.services.UserService;

import java.util.ArrayList;

public class FriendsList {
    private ArrayList<String> friendsList;
    private  String username;


    public FriendsList(String username){
        this.username = username;
        friendsList = new ArrayList<String>();
    }

    public FriendsList(){}

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }

    public void AddFriends (String friendUsername) throws CouldNotFindUserException {
        for ( User user : UserService.getUsers() ){
            if(friendUsername.equals(user.getUsername())){
                friendsList.add( user.getUsername() );
                return;
            }
        }
        throw new CouldNotFindUserException();
    }
}

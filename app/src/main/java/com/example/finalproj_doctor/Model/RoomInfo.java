package com.example.finalproj_doctor.Model;

import java.util.ArrayList;
import java.util.List;

public class RoomInfo {


  List<List<User>> roomInfo;

    public List<List<User>> getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(List<List<User>> roomInfo) {
        this.roomInfo = roomInfo;
    }
}

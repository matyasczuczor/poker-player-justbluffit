package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import java.util.Map;

public class Player {

    static final String VERSION = "1.1";

    public static int betRequest(JsonElement request) {
        String id = request.get(0).get("in_action");
        JSONArray hand = new JSONArray();
        JSONArray players = request.getJSONArray("players");
        String stack;
        for(int i = 0 ; i < players.length() ; i++){
            if(players.get(i).get("id").equals(id)){
                hand = players.get(i).getJSONArray("hole_cards");
                stack = players.get(i).get("stack");
            }
        }

        String[] ranks = new String[2];
        for(int i = 0 ; i < hand.length() ; i++){
            ranks[i] = hand.get(i).get("rank");
        }

        if(ranks[0].equals(ranks[1])){
            return Integer.parseInt(stack);
        }else {
            return 0;
        }

    }

    public static void showdown(JsonElement game) {

    }
}

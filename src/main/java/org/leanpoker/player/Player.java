package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class Player {

    static final String VERSION = "1.2";

    public static int betRequest(JsonElement request) {
        String player_id = request.getAsJsonObject().get("in_action").getAsString();
        JsonArray players = request.getAsJsonObject().get("players").getAsJsonArray();
        JsonArray cards = new JsonArray();
        JsonArray highCards = new JsonArray();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getAsJsonObject().get("id").getAsString().equals(player_id)) {
                 cards = players.get(i).getAsJsonObject().get("hole_cards").getAsJsonArray();
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getAsJsonObject().get("rank").getAsString().equals("10") ||
                    cards.get(i).getAsJsonObject().get("rank").getAsString().equals("J") ||
                    cards.get(i).getAsJsonObject().get("rank").getAsString().equals("Q") ||
                    cards.get(i).getAsJsonObject().get("rank").getAsString().equals("K") ||
                    cards.get(i).getAsJsonObject().get("rank").getAsString().equals("A")) {
                highCards.add(cards.get(i));
            }
        }
        if(highCards.size() > 1) {
            return 1000;
        }
        return 0;
    }

    public static void showdown(JsonElement game) {

    }
}

package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class Player {

    static final String VERSION = "1.4";

    public static int betRequest(JsonElement request) {
        String player_id = request.getAsJsonObject().get("in_action").getAsString();
        JsonArray players = request.getAsJsonObject().get("players").getAsJsonArray();
        JsonArray flop = request.getAsJsonObject().get("community_cards").getAsJsonArray();
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

        int counterFirst = 1;
        int counterSecond = 1;
        for (int i = 0; i < flop.size(); i++) {
            if (cards.get(0).equals(flop.get(i))) {
                counterFirst++;
            }
            if (cards.get(1).equals(flop.get(i))) {
                counterSecond++;
            }
        }

        if (counterFirst > 1 || counterSecond > 1) {
            return 299;
        }

        if (highCards.size() >= 2 || cards.get(0).equals(cards.get(1))) {
            return 298;
        }
        return 0;
    }

    public static void showdown(JsonElement game) {

    }
}

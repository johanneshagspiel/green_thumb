package entity;

import helper.ComparatorLeaderBoard;
import service.FriendServiceImpl;
import service.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Leader board entry.
 */
public class LeaderBoardEntry {

    private String name;
    private int score;

    /**
     * Instantiates a new Leader board entry.
     *
     * @param name  the name
     * @param score the score
     */
    public LeaderBoardEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name +", " + score;
    }


    public static String createLeaderboard(User userIn, String label) {

        List<LeaderBoardEntry> listEntries = new ArrayList<LeaderBoardEntry>();



        if (label.equals("Vegetarian Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getVegetarian());
            listEntries.add(user);
        } else if (label.equals("Leaderboard Top 10 CO2 Used") || label.equals("Overall Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getTotal());
            listEntries.add(user);
        } else if (label.equals("Produce Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getProduce());
            listEntries.add(user);
        } else if (label.equals("Car Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getCar());
            listEntries.add(user);
        } else if (label.equals("Public Transportation Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getPublic_transportation());
            listEntries.add(user);
        } else if (label.equals("Bike Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getBike());
            listEntries.add(user);
        } else if (label.equals("Temperature Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getTemperature());
            listEntries.add(user);
        } else if (label.equals("Solar Score"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getSolar());
            listEntries.add(user);
        } else if (label.equals("Overall CO2 Saved"))
        {
            LeaderBoardEntry user = new LeaderBoardEntry("You", userIn.getTotal_saved());
            listEntries.add(user);
        }



            // adding the friends
            FriendServiceImpl clientFriend = new FriendServiceImpl();
            UserServiceImpl clientUser = new UserServiceImpl();
            List<Friend> friends = clientFriend.getFriends(userIn.getUser_name());

            for (int i = 0; i < friends.size(); i++) {
                Friend temp = friends.get(i);
                String nameFriend = temp.getFriend_name();
                User friend = clientUser.getUser2(nameFriend);

                int points = 0;

                if (label.equals("Vegetarian Score")) {
                    points = friend.getVegetarian();
                } else if (label.equals("Leaderboard Top 10 CO2 Used") || label.equals("Overall Score"))
                {
                    points = friend.getTotal();
                } else if (label.equals("Produce Score"))
                {
                    points = friend.getProduce();
                } else if (label.equals("Car Score"))
                {
                    points = friend.getCar();
                } else if (label.equals("Public Transportation Score"))
                {
                    points = friend.getPublic_transportation();
                } else if (label.equals("Bike Score"))
                {
                    points = friend.getBike();
                } else if (label.equals("Temperature Score"))
                {
                    points = friend.getTemperature();
                } else if (label.equals("Solar Score"))
                {
                    points = friend.getSolar();
                } else if (label.equals("Overall CO2 Saved"))
                {
                    points = friend.getTotal_saved();

                }




                listEntries.add(new LeaderBoardEntry(nameFriend, points));
            }


            // sort the list
            Collections.sort(listEntries, new ComparatorLeaderBoard());

            userIn.setFriendlist(friends);

            String content = new String();
            for (int i = 0; i < listEntries.size() & i <= 10; i++) {
                LeaderBoardEntry temp = listEntries.get(i);
                content = content + "Rank " + (i + 1) + ": " + temp.toString() + "<br/>";
            }

            String LeaderBoardContent = "<html>" + content + "</html>";


            return LeaderBoardContent;

        }
}

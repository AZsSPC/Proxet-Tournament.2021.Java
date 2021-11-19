package proxet.tournament.generator;

import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.TeamGeneratorResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TeamGenerator {

    public TeamGeneratorResult generateTeams(String filePath) {
        ArrayList<Player> team1 = new ArrayList<>(), team2 = new ArrayList<>();
        // need 6 players of all types (3 player os single type in command of 9 players): total 18
        // so find em:
        String[][][] selected = new String
                [3] // for 3 types
                [6] // for player
                [3];// for attribute
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) try {
                String[] spline = line.split("\t", 3);
                int id = Integer.parseInt(spline[2]) - 1;
                for (int c = 0; c < 6; c++) // yeah, im using c++ in java
                    if (selected[id][c][0] == null || Integer.parseInt(selected[id][c][1]) < Integer.parseInt(spline[1])) {
                        // simple sort: if current verifiable player (line) queues longer than recorded - update
                        selected[id][c] = spline;
                        break;
                    }
            } catch (Exception e) {
                e.printStackTrace(); // catches nothing, but it’ll make me feel better
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                team1.add(new Player(selected[i][j][0], Integer.parseInt(selected[i][j][2])));
                team2.add(new Player(selected[i][j + 3][0], Integer.parseInt(selected[i][j + 3][2])));
            }

        return new TeamGeneratorResult(team1, team2);
    }


}

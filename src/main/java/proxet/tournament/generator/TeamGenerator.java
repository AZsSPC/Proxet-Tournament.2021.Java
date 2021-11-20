package proxet.tournament.generator;

import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.PlayerInQueue;
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
        PlayerInQueue[][] selected = new PlayerInQueue[3][6];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) try {
                String[] spline = line.split("\t", 3);
                int id = Integer.parseInt(spline[2]) - 1;
                int await = Integer.parseInt(spline[1]);
                int set_to = 0;
                int min_queue = await;
                for (int c = 0; c < 6; c++) {// yeah, im using c++ in java
                    if (selected[id][c] == null) {
                        set_to = c;
                        min_queue = 0;
                        break;
                    }
                    // simple sort: if current verifiable player (line) queues longer than recorded - update
                    if (selected[id][c].getTimeInQueue() < min_queue) {
                        min_queue = selected[id][c].getTimeInQueue();
                        set_to = c;
                    }
                }
                if (await > min_queue) selected[id][set_to] = new PlayerInQueue(spline);
            } catch (Exception e) {
                e.printStackTrace(); // catches nothing, but it’ll make me feel better
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                team1.add(selected[i][j].getPlayer());
                team2.add(selected[i][j + 3].getPlayer());
            }

        return new TeamGeneratorResult(team1, team2);
    }

}

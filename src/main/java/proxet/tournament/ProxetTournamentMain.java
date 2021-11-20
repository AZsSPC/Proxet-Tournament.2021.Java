package proxet.tournament;

import proxet.tournament.generator.TeamGenerator;
import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.TeamGeneratorResult;

import java.util.List;

public class ProxetTournamentMain {

    public static void main(String[] args) {
        TeamGenerator generator = new TeamGenerator();
        TeamGeneratorResult generatedTeamResult = generator.generateTeams("wait-time.stat");

        outputTeam("Red", generatedTeamResult.getFirstTeam());
        outputTeam("Blue", generatedTeamResult.getSecondTeam());
        System.out.println("Work is done");
    }

    private static void outputTeam(String name, List<Player> players) {
        System.out.printf("Team '%s'%n", name);

        if (players != null) {
            players.forEach(s -> System.out.println("\t" + s.getNickname() + "\t/ " + s.getVehicleType()));
        }
    }
}

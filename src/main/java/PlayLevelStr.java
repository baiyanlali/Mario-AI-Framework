import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import engine.core.MarioAgentEvent;
import engine.core.MarioEvent;
import engine.core.MarioGame;
import engine.core.MarioResult;

public class PlayLevelStr {

    public static class MarioReusltJsonCompatible {
        public String gameStatus;
        public float completionPercentage;
        public int currentLives;
        public int currentCoins;
        public int remainingTime;
        public int marioMode;
        public int numCollectedMushrooms;
        public int numCollectedFireflower;
        public int killsTotal;
        public int killsByStomp;
        public int killsByFire;
        public int killsByShell;
        public int killsByFall;
        public int numDestroyedBricks;
        public int numJumps;
        public float maxXJump;
        public float maxJumpAirTime;

        public ArrayList<MarioEvent> gameEvents;
        public ArrayList<MarioAgentEvent> agentEvents;

        public MarioReusltJsonCompatible(MarioResult result) {
            this.gameStatus = result.getGameStatus().toString();
            this.completionPercentage = result.getCompletionPercentage();
            this.currentLives = result.getCurrentLives();
            this.currentCoins = result.getCurrentCoins();
            this.remainingTime = (int) Math.ceil(result.getRemainingTime() / 1000f);
            this.marioMode = result.getMarioMode();
            this.numCollectedMushrooms = result.getNumCollectedMushrooms();
            this.numCollectedFireflower = result.getNumCollectedFireflower();
            this.killsTotal = result.getKillsTotal();
            this.killsByStomp = result.getKillsByStomp();
            this.killsByFire = result.getKillsByFire();
            this.killsByShell = result.getKillsByShell();
            this.killsByFall = result.getKillsByFall();
            this.numDestroyedBricks = result.getNumDestroyedBricks();
            this.numJumps = result.getNumJumps();
            this.maxXJump = result.getMaxXJump();
            this.maxJumpAirTime = result.getMaxJumpAirTime();

            this.gameEvents = new ArrayList<>(result.getGameEvents());
            this.agentEvents = new ArrayList<>(result.getAgentEvents());

        }
    }

    public static void printResults(MarioResult result) {
        System.out.println("****************************************************************");
        System.out.println("Game Status: " + result.getGameStatus().toString() +
                " Percentage Completion: " + result.getCompletionPercentage());
        System.out.println("Lives: " + result.getCurrentLives() + " Coins: " + result.getCurrentCoins() +
                " Remaining Time: " + (int) Math.ceil(result.getRemainingTime() / 1000f));
        System.out.println("Mario State: " + result.getMarioMode() +
                " (Mushrooms: " + result.getNumCollectedMushrooms() + " Fire Flowers: " + result.getNumCollectedFireflower() + ")");
        System.out.println("Total Kills: " + result.getKillsTotal() + " (Stomps: " + result.getKillsByStomp() +
                " Fireballs: " + result.getKillsByFire() + " Shells: " + result.getKillsByShell() +
                " Falls: " + result.getKillsByFall() + ")");
        System.out.println("Bricks: " + result.getNumDestroyedBricks() + " Jumps: " + result.getNumJumps() +
                " Max X Jump: " + result.getMaxXJump() + " Max Air Time: " + result.getMaxJumpAirTime());
        System.out.println("****************************************************************");
    }

    public static String getLevel(String filepath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
        }
        return content;
    }

    public static String Play(String level) {
        MarioGame game = new MarioGame();
        MarioResult result = game.runGame(new agents.robinBaumgarten.Agent(), level, 20, 0, false);
        MarioReusltJsonCompatible resultJC = new MarioReusltJsonCompatible(result);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(resultJC);
        return jsonResult;
    }

    public static void main(String[] args) {
        System.out.println(Play(getLevel("./levels/original/lvl-1.txt"))); 
    }
}

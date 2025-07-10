import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import agents.replay.MarioResultJsonCompatible;
import com.google.gson.Gson;
import engine.core.*;

public class PlayLevelStr {

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

    public static String Play(String level, int agentType) {
        MarioGame game = new MarioGame();
        MarioAgent agent = null;
        switch (agentType) {
            case 0: agent = new agents.robinBaumgarten.Agent(); break;
            case 1: agent = new agents.glennHartmann.Agent(); break;
            case 2: agent = new agents.andySloane.Agent(); break;
            case 3: agent = new agents.michal.Agent(); break;
            case 4: agent = new agents.sergeyKarakovskiy.Agent(); break;
            case 5: agent = new agents.sergeyPolikarpov.Agent(); break;
            case 6: agent = new agents.spencerSchumann.Agent(); break;
            case 7: agent = new agents.trondEllingsen.Agent(); break;
            default: agent = new agents.robinBaumgarten.Agent();
        }
        MarioResult result =  game.runGame(agent, level, 300, 0, false, 0, 2);
//        MarioResult result = game.runGame(new agents.robinBaumgarten.Agent(), level, 20, 0, true);
        MarioResultJsonCompatible resultJC = new MarioResultJsonCompatible(result);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(resultJC);
        return jsonResult;
    }

    public static String HumanPlay(String level) {
        MarioGame game = new MarioGame();
        MarioResult result =  game.runGame(new agents.human.Agent(), level, 200000000, 0, true, 20, 2);
//        MarioResult result = game.runGame(new agents.robinBaumgarten.Agent(), level, 20, 0, true);
        MarioResultJsonCompatible resultJC = new MarioResultJsonCompatible(result);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(resultJC);
        return jsonResult;
    }

    public static String RePlay(String level) {
        MarioGame game = new MarioGame();
        Gson gson = new Gson();
        MarioResultJsonCompatible result = gson.fromJson("Replace it with MarioResult", MarioResultJsonCompatible.class);

        var actions = agents.replay.Agent.readActionsFromMarioJson(result);
        MarioResult result2 =  game.runGame(new agents.replay.Agent(actions), level, 200000000, 0, true, 20, 2);
//        MarioResult result = game.runGame(new agents.robinBaumgarten.Agent(), level, 20, 0, true);
        MarioResultJsonCompatible resultJC = new MarioResultJsonCompatible(result2);
        String jsonResult = gson.toJson(resultJC);
        return jsonResult;
    }

    public static void main(String[] args) {
        System.out.println(Play(getLevel("./levels/test/lvl-2.txt"), 1));
    }
}

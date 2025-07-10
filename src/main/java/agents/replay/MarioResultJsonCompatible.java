package agents.replay;

import engine.core.MarioAgentEvent;
import engine.core.MarioEvent;
import engine.core.MarioResult;

import java.util.ArrayList;

public class MarioResultJsonCompatible {
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

    public MarioResultJsonCompatible(MarioResult result) {
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
package agents.replay;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;

import java.util.ArrayList;
import java.util.Arrays;

public class Agent implements MarioAgent {

    public static boolean[][] readActionsFromMarioJson(MarioResultJsonCompatible result) {
        int eventLength = result.agentEvents.size();
        int actionLength = 5;
        boolean[][] actions = new boolean[eventLength][actionLength];
        for (int i = 0; i < eventLength; i++) {
            for (int j = 0; j < actionLength; j++) {
                actions[i][j] = result.agentEvents.get(i).getActions()[j];
            }
        }
        return actions;
    }

    private boolean[][] actions = null;
    public int p = 0;

    public Agent(boolean[][] actions) {
        this.actions = actions;

    }

    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
//        actions = new boolean[MarioActions.numberOfActions()];
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
        System.out.printf("get actions: %d/%d %s\n", p, actions.length,Arrays.toString(actions[p]));
        return actions[p++];
    }

    @Override
    public String getAgentName() {
        return "ReplayAgent";
    }


}

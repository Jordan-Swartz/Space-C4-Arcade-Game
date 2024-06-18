package ai;

import core.*;

public class ComputerLogic {
    private GameLogic logic;
    
    public ComputerLogic(GameLogic logic) {
        this.logic = logic;
    }

    public int[] getComputerMove() {
        int[] computerMove = {0,0};
        return computerMove;
    }

    public boolean checkPotentialWin(char token) {
        return false;
    }


}

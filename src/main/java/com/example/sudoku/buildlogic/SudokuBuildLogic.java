package com.example.sudoku.buildlogic;

import com.example.sudoku.computationlogic.GameLogic;
import com.example.sudoku.persistence.LocalStorageImpl;
import com.example.sudoku.problemdomain.IStorage;
import com.example.sudoku.problemdomain.SudokuGame;
import com.example.sudoku.userinterface.IUserInterfaceContract;
import com.example.sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uilogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uilogic);
        userInterface.updateBoard(initialState);
    }
}

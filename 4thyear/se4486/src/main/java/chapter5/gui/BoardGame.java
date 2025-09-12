package chapter5.gui;


import base.GameEntity;
import chapter5.boardgames.base.*;
import chapter5.boardgames.player.AIPlayer;
import chapter5.boardgames.player.HumanPlayer;
import chapter5.boardgames.player.Player;
import chapter5.boardgames.player.PlayerType;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class BoardGame implements GameEntity {

    Board board;
    BoardViewer viewer;
    BoardController controller;

    List<Player> players;
    private boolean gameIsOver;
    private boolean gameChanged;

    public BoardGame(Board board, BoardViewer viewer, BoardController controller) {
        this.board = board;
        this.viewer = viewer;
        this.controller = controller;
        players = new ArrayList<>();
    }

    public void addPlayer(Player p)
    {
        players.add(p);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {

        gameContainer.setAlwaysRender(true);

        board.init();
        viewer.init(boundary,board);
        controller.init(viewer);

        for (Player p: players) {
            p.init(board);
        }

        gameIsOver = players.isEmpty();
        if (currentPlayer()!= null && currentPlayer().getType()== PlayerType.AIPlayer)
            ((AIPlayer)currentPlayer()).calculateMove(board);

        gameChanged = false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        viewer.render(gameContainer,stateBasedGame,graphics);

        if (gameIsOver)
        {
            renderGameOver(gameContainer,graphics);
        }
        else if (currentPlayer()!= null && currentPlayer().getType()==PlayerType.AIPlayer)
            renderAIPlayer(gameContainer,graphics);


    }

    private void renderAIPlayer(GameContainer gameContainer, Graphics graphics) {
        graphics.setColor(new Color(0,100,100,50));
        graphics.fillRect(0,0,gameContainer.getWidth(),gameContainer.getHeight());
    }

    private void renderGameOver(GameContainer gameContainer, Graphics graphics) {
        graphics.setColor(new Color(100,0,100,50));
        graphics.fillRect(0,0,gameContainer.getWidth(),gameContainer.getHeight());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        if (gameIsOver)
        {
            return;
        }
        if ( gameChanged && board.isGameOver()) {
            gameIsOver = true;

            System.out.println("GAME OVER! The winner is : "+ board.winner());
            return;
        }

        gameChanged = false;

        Player currentPlayer = currentPlayer();

        if (currentPlayer.getType() == PlayerType.HumanPlayer)
        {
            controller.update(board,gameContainer, stateBasedGame,time, (HumanPlayer) currentPlayer);
        }

        if ( currentPlayer.hasMove())
        {
            Move m = currentPlayer.getMove();
            board.perform(m);

            gameChanged = true;
            currentPlayer = currentPlayer();

            if (!board.isGameOver() &&currentPlayer.getType()== PlayerType.AIPlayer)
                ((AIPlayer)currentPlayer).calculateMove(board);

        }

    }

    Player currentPlayer() {
        if (players.isEmpty())
            return null;
        return players.get(board.currentPlayer());
    }
}

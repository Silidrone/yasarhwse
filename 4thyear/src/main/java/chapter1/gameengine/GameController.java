package chapter1.gameengine;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface GameController<G extends Game> extends MouseListener, KeyListener, MouseMotionListener {

}

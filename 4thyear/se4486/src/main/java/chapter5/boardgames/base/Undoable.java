package chapter5.boardgames.base;

public interface Undoable<M> {
    void undo(M m);
}

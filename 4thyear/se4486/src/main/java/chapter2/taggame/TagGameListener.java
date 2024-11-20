package chapter2.taggame;

public interface TagGameListener {
    void tagChanged(TagPlayer oldTag,TagPlayer newTag);
    void warmupFinished();

}

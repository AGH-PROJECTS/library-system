package dawidkruczek.projectII.librarysystem.support;

public enum AnswerType {
    ADDED,UPDATED,DELETED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

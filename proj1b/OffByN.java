public class OffByN implements CharacterComparator {
    int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char a, char b) {
        int diff = Math.abs(a - b);
        if (diff == N) {
            return true;
        }
        return false;
    }
}
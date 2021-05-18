public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character>  wordDeque = new LinkedListDeque();
        for (int i = 0; i < word.length(); i += 1) {
            wordDeque.addLast(word.charAt(i));
        }

        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();

        if (first == last) {
            return isPalindromeHelper(wordDeque);
        } else {
            return false;
        }

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);

        while (wordDeque.size() > 1) {
            char first = wordDeque.removeFirst();
            char last = wordDeque.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }


}

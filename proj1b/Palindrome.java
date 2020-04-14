public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> words = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            words.addLast(word.charAt(i));
        }
        return words;
    }

    public boolean isPalindrome(String word) {
        return checkIsPalindrome(wordToDeque(word));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return checkIsPalindromeWithCC(wordToDeque(word), cc);
    }

    private boolean checkIsPalindromeWithCC(Deque<Character> words, CharacterComparator cc) {
        if (words.size() <= 1) {
            return true;
        }
        Character front = words.removeFirst();
        Character tail = words.removeLast();
        if (!cc.equalChars(front, tail)) {
            return false;
        }
        return checkIsPalindromeWithCC(words, cc);
    }

    private boolean checkIsPalindrome(Deque<Character> words) {
        if (words.size() <= 1) {
            return true;
        }
        Character front = words.removeFirst();
        Character tail = words.removeLast();
        if (front != tail) {
            return false;
        }
        return checkIsPalindrome(words);
    }
}

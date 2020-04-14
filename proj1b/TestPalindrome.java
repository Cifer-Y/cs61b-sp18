import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testIsPalindromeWithCC() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("persiflage", cc));
        assertTrue(palindrome.isPalindrome("jufti", cc));
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
    }
}

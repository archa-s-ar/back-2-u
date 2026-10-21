
package back2u;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ItemMatcher {

    public static int calculateScore(
            String lostName,
            String foundName,
            String lostDescription,
            String foundDescription,
            String lostLocation,
            String foundLocation,
            LocalDate lostDate,
            LocalDate foundDate,
            String lostCategory,
            String foundCategory,
            String lostUniqueDetails,
            String foundUniqueDetails) {

        
        
int nameScore = textSimilarityScore(
    lostName, foundName, 20
);

int descScore = textSimilarityScore(
    lostDescription, foundDescription, 25
);

int locScore = locationScore(
    lostLocation, foundLocation
);

int datePoints = dateScore(
    lostDate, foundDate
);

int categoryPoints =
    sameText(lostCategory, foundCategory) ? 10 : 0;

int uniqueScore = textSimilarityScore(
    lostUniqueDetails, foundUniqueDetails, 5
);

System.out.println("Name score: " + nameScore);
System.out.println("Description score: " + descScore);
System.out.println("Location score: " + locScore);
System.out.println("Date score: " + datePoints);
System.out.println("Category score: " + categoryPoints);
System.out.println("Unique details score: " + uniqueScore);

int score = nameScore + descScore + locScore
    + datePoints + categoryPoints + uniqueScore;

System.out.println("Total score: " + score);

return Math.min(score, 100);


        /*int score = 0;

        score += textSimilarityScore(lostName, foundName, 20);
        score += textSimilarityScore(
            lostDescription, foundDescription, 25
        );
        score += locationScore(lostLocation, foundLocation);
        score += dateScore(lostDate, foundDate);

        if (sameText(lostCategory, foundCategory)) {
            score += 10;
        }

        score += textSimilarityScore(
            lostUniqueDetails, foundUniqueDetails, 5
        );

        return Math.min(score, 100);*/
    }

    private static int textSimilarityScore(
            String a, String b, int maxPoints) {

        Set<String> wordsA = words(a);
        Set<String> wordsB = words(b);

        if (wordsA.isEmpty() || wordsB.isEmpty()) {
            return 0;
        }

        Set<String> common = new HashSet<>(wordsA);
        common.retainAll(wordsB);

        Set<String> all = new HashSet<>(wordsA);
        all.addAll(wordsB);

        double similarity =
            (double) common.size() / all.size();

        return (int) Math.round(similarity * maxPoints);
    }

    private static Set<String> words(String text) {
        Set<String> result = new HashSet<>();

        if (text == null || text.trim().isEmpty()) {
            return result;
        }

        String cleaned = text.toLowerCase()
            .replaceAll("[^a-z0-9]+", " ")
            .trim();

        if (!cleaned.isEmpty()) {
            result.addAll(
                Arrays.asList(cleaned.split("\\s+"))
            );
        }

        return result;
    }

    private static int locationScore(String a, String b) {
        if (a == null || b == null
                || a.trim().isEmpty()
                || b.trim().isEmpty()) {
            return 0;
        }

        String x = a.trim().toLowerCase();
        String y = b.trim().toLowerCase();

        if (x.equals(y)) {
            return 25;
        }

        if (x.contains(y) || y.contains(x)) {
            return 15;
        }

        return 0;
    }

    private static int dateScore(
            LocalDate lost, LocalDate found) {

        if (lost == null || found == null) {
            return 0;
        }

        long days = Math.abs(
            ChronoUnit.DAYS.between(lost, found)
        );

        if (days == 0) return 15;
        if (days <= 1) return 12;
        if (days <= 3) return 8;
        if (days <= 7) return 4;

        return 0;
    }

    private static boolean sameText(String a, String b) {
        return a != null && b != null
            && !a.trim().isEmpty()
            && a.trim().equalsIgnoreCase(b.trim());
    }
}

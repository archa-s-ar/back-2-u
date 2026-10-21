
package back2u;

public class MatchingDatabaseTest {

    public static void main(String[] args) {
        try {
            var matches =
                ItemMatchingService.findMatches(1);

            if (matches.isEmpty()) {
                System.out.println("No possible matches found.");
            }

            for (var match : matches) {
                System.out.println(
                    match.itemName + " | " +
                    match.location + " | " +
                    match.score + "/100"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

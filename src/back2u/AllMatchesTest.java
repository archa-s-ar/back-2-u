
package back2u;

public class AllMatchesTest {

    public static void main(String[] args) {
        try {
            var matches = ItemMatchingService.findAllMatches();

            if (matches.isEmpty()) {
                System.out.println("No possible matches found.");
                return;
            }

            for (var m : matches) {
                System.out.println(
                    "Lost #" + m.lostItemId +
                    " (" + m.lostItemName + ")" +
                    " <-> Found #" + m.foundItemId +
                    " (" + m.foundItemName + ")" +
                    " | Score: " + m.score + "/100"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

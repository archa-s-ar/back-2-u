
package back2u;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ItemMatchingService {

    public static class MatchResult {
        public int itemId;
        public String itemName;
        public String category;
        public String description;
        public String location;
        public LocalDate date;
        public int score;

        public MatchResult(
                int itemId,
                String itemName,
                String category,
                String description,
                String location,
                LocalDate date,
                int score) {

            this.itemId = itemId;
            this.itemName = itemName;
            this.category = category;
            this.description = description;
            this.location = location;
            this.date = date;
            this.score = score;
        }
    }

    public static List<MatchResult> findMatches(int lostItemId)
            throws SQLException {

        List<MatchResult> matches = new ArrayList<>();

        String lostSql =
            "SELECT item_name, description, location, " +
            "date_occurred, category, unique_details " +
            "FROM items " +
            "WHERE item_id = ? AND report_type = 'LOST'";

        String foundSql =
            "SELECT item_id, item_name, category, description, " +
            "location, date_occurred, unique_details " +
            "FROM items " +
            "WHERE report_type = 'FOUND' AND status = 'OPEN'";

        try (Connection con = new Conn().c;
             PreparedStatement lostStmt =
                 con.prepareStatement(lostSql)) {

            lostStmt.setInt(1, lostItemId);

            String lostName;
            String lostDescription;
            String lostLocation;
            String lostCategory;
            String lostUniqueDetails;
            LocalDate lostDate;

            try (ResultSet lostRs = lostStmt.executeQuery()) {

                if (!lostRs.next()) {
                    return matches;
                }

                lostName = lostRs.getString("item_name");
                lostDescription = lostRs.getString("description");
                lostLocation = lostRs.getString("location");
                lostCategory = lostRs.getString("category");
                lostUniqueDetails =
                    lostRs.getString("unique_details");

                Date sqlDate = lostRs.getDate("date_occurred");
                lostDate = sqlDate == null
                    ? null : sqlDate.toLocalDate();
            }

            try (PreparedStatement foundStmt =
                     con.prepareStatement(foundSql);
                 ResultSet foundRs = foundStmt.executeQuery()) {

                while (foundRs.next()) {

                    int foundId = foundRs.getInt("item_id");
                    String foundName =
                        foundRs.getString("item_name");
                    String foundCategory =
                        foundRs.getString("category");
                    String foundDescription =
                        foundRs.getString("description");
                    String foundLocation =
                        foundRs.getString("location");
                    String foundUniqueDetails =
                        foundRs.getString("unique_details");

                    Date sqlDate =
                        foundRs.getDate("date_occurred");

                    LocalDate foundDate = sqlDate == null
                        ? null : sqlDate.toLocalDate();

                    int score = ItemMatcher.calculateScore(
                        lostName,
                        foundName,
                        lostDescription,
                        foundDescription,
                        lostLocation,
                        foundLocation,
                        lostDate,
                        foundDate,
                        lostCategory,
                        foundCategory,
                        lostUniqueDetails,
                        foundUniqueDetails
                    );

                    if (score > 0) {
                        matches.add(new MatchResult(
                            foundId,
                            foundName,
                            foundCategory,
                            foundDescription,
                            foundLocation,
                            foundDate,
                            score
                        ));
                    }
                }
            }
        }

        matches.sort(
            Comparator.comparingInt(
                (MatchResult m) -> m.score
            ).reversed()
        );

        return matches;
    }
}

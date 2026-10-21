
package back2u;

import javax.swing.SwingUtilities;

public class PossibleMatchesTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PossibleMatches(2).setVisible(true);
        });
    }
}

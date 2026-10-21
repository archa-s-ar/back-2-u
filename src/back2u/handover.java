package back2u;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class handover extends JFrame {

    private JTextArea detailsArea;
    private JButton loadButton;
    private JButton enterPinButton;

    private int handoverId = -1;
    private String correctPin = "";

    public handover() {
        setTitle("Back2U - Handover Details");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout());

        JLabel titleLabel = new JLabel("Handover Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        loadButton = new JButton("Load Handover Details");

        topPanel.add(titleLabel);
        topPanel.add(loadButton);

        // Details area
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(detailsArea);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout());

        enterPinButton = new JButton("Enter Handover PIN");
        enterPinButton.setEnabled(false);

        bottomPanel.add(enterPinButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> loadDetails());
        enterPinButton.addActionListener(e -> openPinWindow());
    }

    private void loadDetails() {

        // Fetch the latest handover record
        String query =
            "SELECT * FROM handover " +
            "ORDER BY handover_id DESC LIMIT 1";

        try {
            Conn con = new Conn();

            if (con.c == null) {
                JOptionPane.showMessageDialog(
                    this, "Database connection failed. Check Conn.java.");
                return;
            }

            try (PreparedStatement stmt = con.c.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    handoverId = rs.getInt("handover_id");
                    correctPin = rs.getString("pin");

                    StringBuilder sb = new StringBuilder();

                    sb.append("ITEM INFORMATION\n");
                    sb.append("----------------\n");
                    sb.append("Item: ")
                        .append(rs.getString("item_name")).append("\n");
                    sb.append("Category: ")
                        .append(rs.getString("category")).append("\n");
                    sb.append("Description: ")
                        .append(rs.getString("description")).append("\n");
                    sb.append("Found Date: ")
                        .append(rs.getDate("found_date")).append("\n");
                    sb.append("Found Location: ")
                        .append(rs.getString("found_location")).append("\n\n");

                    sb.append("OWNER INFORMATION\n");
                    sb.append("-----------------\n");
                    sb.append("Owner: ")
                        .append(rs.getString("owner_name")).append("\n");
                    sb.append("Registration: ")
                        .append(rs.getString("reg_no")).append("\n");
                    sb.append("Phone: ")
                        .append(rs.getString("phone")).append("\n\n");

                    sb.append("MEETING DETAILS\n");
                    sb.append("---------------\n");
                    sb.append("Location: ")
                        .append(rs.getString("meeting_location")).append("\n");
                    sb.append("Date: ")
                        .append(rs.getDate("meeting_date")).append("\n");
                    sb.append("Time: ")
                        .append(rs.getString("meeting_time")).append("\n");

                    detailsArea.setText(sb.toString());
                    enterPinButton.setEnabled(true);

                } else {
                    handoverId = -1;
                    correctPin = "";
                    detailsArea.setText("");
                    enterPinButton.setEnabled(false);

                    JOptionPane.showMessageDialog(
                        this,
                        "No handover details found in database.",
                        "Not Found",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            } finally {
                if (con.c != null) {
                    con.c.close();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Database error: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();
        }
    }

    private void openPinWindow() {

        if (handoverId == -1 || correctPin.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please load valid handover details first.");
            return;
        }

        JDialog pinDialog =
            new JDialog(this, "Handover PIN Verification", true);

        pinDialog.setSize(350, 180);
        pinDialog.setLocationRelativeTo(this);
        pinDialog.setLayout(new GridLayout(4, 1, 5, 5));

        JLabel promptLabel = new JLabel(
            "Enter the 6-digit Handover PIN:",
            SwingConstants.CENTER
        );

        JPasswordField pinField = new JPasswordField();
        JButton verifyButton =
            new JButton("Verify & Complete Handover");

        pinDialog.add(promptLabel);
        pinDialog.add(pinField);
        pinDialog.add(new JLabel(""));
        pinDialog.add(verifyButton);

        verifyButton.addActionListener(e -> {

            String enteredPin =
                new String(pinField.getPassword()).trim();

            if (!enteredPin.matches("\\d{6}")) {
                JOptionPane.showMessageDialog(
                    pinDialog,
                    "Please enter a valid 6-digit PIN.",
                    "Invalid PIN",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (enteredPin.equals(correctPin)) {

                if (updateHandoverStatus()) {
                    JOptionPane.showMessageDialog(
                        pinDialog,
                        "Handover completed successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    pinDialog.dispose();
                    enterPinButton.setEnabled(false);
                }

            } else {
                JOptionPane.showMessageDialog(
                    pinDialog,
                    "Incorrect PIN! Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        pinDialog.setVisible(true);
    }

    private boolean updateHandoverStatus() {

        String updateSql =
            "UPDATE handover " +
            "SET status = 'COMPLETED', returned_at = NOW() " +
            "WHERE handover_id = ? AND status <> 'COMPLETED'";

        try {
            Conn con = new Conn();

            if (con.c == null) {
                JOptionPane.showMessageDialog(
                    this, "Database connection failed.");
                return false;
            }

            try (PreparedStatement stmt =
                     con.c.prepareStatement(updateSql)) {

                stmt.setInt(1, handoverId);

                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    return true;
                }

                JOptionPane.showMessageDialog(
                    this,
                    "This handover may already be completed. Reload the details."
                );

                return false;

            } finally {
                if (con.c != null) {
                    con.c.close();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Could not update handover status: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new handover().setVisible(true));
    }
}
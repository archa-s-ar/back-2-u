package back2u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class handover extends JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/back2u";
    private static final String USER = "root";
    private static final String PASSWORD = "Gitto@2005"; // Replace with your actual MySQL password

    private JTextArea detailsArea;
    private JButton loadButton;
    private JButton enterPinButton;

    private int handoverId = 1; // Default ID to fetch
    private String correctPin = "";

    public handover() {
        setTitle("Back2U - Handover Details");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Top Panel with title and Load button
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Handover Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        loadButton = new JButton("Load Handover Details");
        topPanel.add(titleLabel);
        topPanel.add(loadButton);

        // Center Panel for Details Area
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(detailsArea);

        // Bottom Panel with Enter PIN button
        JPanel bottomPanel = new JPanel(new FlowLayout());
        enterPinButton = new JButton("Enter Handover PIN");
        enterPinButton.setEnabled(false); // Enabled after details load
        bottomPanel.add(enterPinButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Load Data Action
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDetails();
            }
        });

        // Open PIN Window Action
        enterPinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPinWindow();
            }
        });
    }

    private void loadDetails() {
        String query = "SELECT * FROM handover WHERE handover_id = ? OR handover_id IS NOT NULL ORDER BY handover_id DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, handoverId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                handoverId = rs.getInt("handover_id");
                correctPin = rs.getString("pin");

                StringBuilder sb = new StringBuilder();
                sb.append("ITEM INFORMATION\n");
                sb.append("----------------\n");
                sb.append("Item: ").append(rs.getString("item_name")).append("\n");
                sb.append("Category: ").append(rs.getString("category")).append("\n");
                sb.append("Description: ").append(rs.getString("description")).append("\n");
                sb.append("Found Date: ").append(rs.getDate("found_date")).append("\n");
                sb.append("Found Location: ").append(rs.getString("found_location")).append("\n\n");

                sb.append("OWNER INFORMATION\n");
                sb.append("-----------------\n");
                sb.append("Owner: ").append(rs.getString("owner_name")).append("\n");
                sb.append("Registration: ").append(rs.getString("reg_no")).append("\n");
                sb.append("Phone: ").append(rs.getString("phone")).append("\n\n");

                sb.append("MEETING DETAILS\n");
                sb.append("---------------\n");
                sb.append("Location: ").append(rs.getString("meeting_location")).append("\n");
                sb.append("Date: ").append(rs.getDate("meeting_date")).append("\n");
                sb.append("Time: ").append(rs.getString("meeting_time")).append("\n");

                detailsArea.setText(sb.toString());
                enterPinButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No handover details found in database.", "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openPinWindow() {
        // Window 2: PIN Verification Dialog
        JDialog pinDialog = new JDialog(this, "Handover PIN Verification", true);
        pinDialog.setSize(350, 180);
        pinDialog.setLocationRelativeTo(this);
        pinDialog.setLayout(new GridLayout(4, 1, 5, 5));

        JLabel promptLabel = new JLabel("Enter the 6-digit Handover PIN:", SwingConstants.CENTER);
        JPasswordField pinField = new JPasswordField();
        JButton verifyButton = new JButton("Verify & Complete Handover");

        pinDialog.add(promptLabel);
        pinDialog.add(pinField);
        pinDialog.add(new JLabel("")); // spacing
        pinDialog.add(verifyButton);

        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPin = new String(pinField.getPassword()).trim();

                if (enteredPin.isEmpty()) {
                    JOptionPane.showMessageDialog(pinDialog, "Please enter the PIN.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (enteredPin.equals(correctPin)) {
                    updateHandoverStatus();
                    JOptionPane.showMessageDialog(pinDialog, "PIN Verified Successfully! Handover Completed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    pinDialog.dispose();
                    enterPinButton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(pinDialog, "Incorrect PIN! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pinDialog.setVisible(true);
    }

    private void updateHandoverStatus() {
        String updateSql = "UPDATE handover SET status = 'COMPLETED', returned_at = NOW() WHERE handover_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {
            stmt.setInt(1, handoverId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Could not update status: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new handover().setVisible(true);
        });
    }
}
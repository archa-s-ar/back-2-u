package back2u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class matchfound extends JFrame {

    // Method to fetch details from the handover table
    public String fetchUniqueDetails(int itemId) {
        String details = "No details found";
        try {
            Conn c = new Conn();
            // Updated to query 'description' from 'handover' table using 'handover_id'
            String query = "SELECT description FROM handover WHERE handover_id = " + itemId;
            ResultSet rs = c.s.executeQuery(query);
            
            if (rs.next()) {
                details = rs.getString("description");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
        return details;
    }

    // Window shown to the potential owner
    public void showOwnerMatchWindow(int itemId, String cat, String name, String time, String score) {
        JFrame frame = new JFrame("Owner - Potential Match");
        frame.setSize(420, 360);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Potential Match Found");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblCat = new JLabel("Item Category: " + cat);
        JLabel lblName = new JLabel("Item Name: " + name);
        JLabel lblTime = new JLabel("Time: " + time);
        JLabel lblScore = new JLabel("Match Score: " + score);
        lblScore.setFont(new Font("Arial", Font.BOLD, 14));
        lblScore.setForeground(new Color(0, 128, 0));

        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblCat);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblName);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblTime);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblScore);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnView = new JButton("View Details");
        JButton btnNotMatch = new JButton("Not a Match");

        btnView.addActionListener(e -> {
            frame.dispose();
            showUniqueDetailsWindow(itemId, name);
        });

        btnNotMatch.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Marked as not a match.");
            frame.dispose();
        });

        btnPanel.add(btnView);
        btnPanel.add(btnNotMatch);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // Window shown to the finder
    public void showFinderMatchWindow(int itemId, String cat, String name, String score) {
        JFrame frame = new JFrame("Finder - Potential Owner Found");
        frame.setSize(420, 360);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("Potential Owner Found");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblCat = new JLabel("Item Category: " + cat);
        JLabel lblName = new JLabel("Item Name: " + name);
        JLabel lblScore = new JLabel("Match Score: " + score);
        lblScore.setFont(new Font("Arial", Font.BOLD, 14));
        lblScore.setForeground(new Color(0, 102, 204));

        panel.add(title);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblCat);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblName);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblScore);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnViewDetails = new JButton("View Lost Item Details");
        JButton btnNotMatch = new JButton("Not a Match");

        btnViewDetails.addActionListener(e -> {
            frame.dispose();
            showUniqueDetailsWindow(itemId, name);
        });

        btnNotMatch.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Match rejected.");
            frame.dispose();
        });

        btnPanel.add(btnViewDetails);
        btnPanel.add(btnNotMatch);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // Verification Window displaying fetched details
    public void showUniqueDetailsWindow(int itemId, String itemName) {
        JFrame frame = new JFrame("Item Verification (" + itemName + ")");
        frame.setSize(450, 380);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel("Item Verification (" + itemName + ")");
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel descLabel = new JLabel("Unique Identifying Details:");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        String uniqueInfo = fetchUniqueDetails(itemId);

        JTextArea txtDetails = new JTextArea(uniqueInfo);
        txtDetails.setLineWrap(true);
        txtDetails.setWrapStyleWord(true);
        txtDetails.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtDetails);
        scroll.setPreferredSize(new Dimension(380, 150));

        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(descLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(scroll);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnMismatch = new JButton("Details Don't Match");
        JButton btnMatch = new JButton("Details Match");

        btnMismatch.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Details do not match. Contact administrator.");
            frame.dispose();
        });

        btnMatch.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Details matched successfully!");
            frame.dispose();
        });

        btnPanel.add(btnMismatch);
        btnPanel.add(btnMatch);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            matchfound app = new matchfound();
            // Test with a sample handover_id that exists in your table (e.g., 1)
            app.showFinderMatchWindow(1, "Electronics", "Black Wireless Earbuds", "92.5%");
        });
    }
}
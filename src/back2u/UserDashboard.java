
package back2u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserDashboard extends JFrame implements ActionListener{
    JButton lostBtn,foundBtn,notificationsBtn,adminBtn;
    private final int userId;
    
    UserDashboard(int userId){
        this.userId = userId;
        
        setLayout(null);
        setTitle("WELCOME");
        
        JPanel options = new JPanel(new GridLayout(2,2,10,10));
        options.setBounds(120, 400, 900, 300);

        lostBtn = new JButton("Report Lost Item");
        lostBtn.addActionListener(this);
        foundBtn = new JButton("Report Found Item");
        foundBtn.addActionListener(this);
        notificationsBtn = new JButton("Notifications");
        notificationsBtn.addActionListener(this);
        adminBtn = new JButton("Report to Admin");
        adminBtn.addActionListener(this);

        
        Font buttonFont = new Font("Raleway", Font.BOLD, 18);

        JButton[] buttons = {lostBtn, foundBtn, notificationsBtn, adminBtn};

        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(new Color(91, 72, 157));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
        }
        options.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        options.add(lostBtn);
        options.add(foundBtn);
        options.add(notificationsBtn);
        options.add(adminBtn);

        add(options);
        
        
        JLabel welcome= new JLabel("Welcome to Back2U!");
        welcome.setFont(new Font("Raleway",Font.BOLD,32));
        welcome.setBounds(120,300,500,30);
        add(welcome);
        
        getContentPane().setBackground(Color.WHITE);
        
        
        setSize(1200,1000);
        setLocation(300,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {

    if (e.getSource() == lostBtn) {
        setVisible(false);
        new ReportLostItem(userId).setVisible(true);
    }
    else if (e.getSource() == foundBtn) {
        setVisible(false);
        new ReportFounditem().setVisible(true);
    }
    else if (e.getSource() == notificationsBtn) {
        JOptionPane.showMessageDialog(
            this, "Notifications will be available here."
        );
    }
    else if (e.getSource() == adminBtn) {
        JOptionPane.showMessageDialog(
            this, "Report to Admin will be available here."
        );
    }
}
    
    public static void main (String[] args){
        new UserDashboard(1);
    }
}

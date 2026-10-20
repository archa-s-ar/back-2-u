
package back2u;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class Forgotpassword extends JFrame implements ActionListener {
    Forgotpassword(){
        setTitle("Forgot Password");
        setSize(600,800);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245,242,255));
        
        JLabel title=new JLabel("Forgot Password");
        title.setBounds(130,20,240,30);
        title.setFont(new Font("Raleway",Font.BOLD,22));
        add(title);
        
        //email 
        JLabel l1=new JLabel("Email:");
        l1.setBounds(40,70,100,30);
        l1.setFont(new Font("Raleway",Font.BOLD,18));
        add(l1);
        
        //email textfield
        JTextField l1text=new JTextField();
        l1text.setBounds(220,70,220,30);
        add(l1text);
        
        //password
        JLabel l2=new JLabel("New Password:");
        l2.setBounds(40,120,200,30);
        l2.setFont(new Font("Raleway",Font.BOLD,18));
        add(l2);
        
        //passwordfield
        JPasswordField l2pass=new JPasswordField();
        l2pass.setBounds(220,120,220,30);
        add(l2pass);
        
        //confirmpassword
        JLabel l3=new JLabel("Confirm password:");
        l3.setBounds(40,160,200,30);
        l3.setFont(new Font("Raleway",Font.BOLD,18));
        add(l3);
        
        //confirmapassword passwordfield
        JPasswordField confirm=new JPasswordField();
        confirm.setBounds(220,120,220,30);
        add(confirm);
        
        
        
        
        
        
        
        
        
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent et){
        
    }
    
public static void main(String args[]){
    new Forgotpassword();
 }
}

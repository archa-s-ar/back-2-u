
package back2u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateAccount extends JFrame implements ActionListener{
    JTextField nametextfield,mailtextfield,phntextfield;
    JPasswordField password;
    JCheckBox terms;
    
    CreateAccount(){
          
        setTitle("CREATE ACCOUNT");
        
        setLayout(null);
        
        JLabel head = new JLabel("CREATE ACCOUNT");
        head.setBounds(250,100,300,30);
        head.setFont(new Font("Raleway", Font.BOLD, 32));
        add(head);
        
        JLabel name = new JLabel("Full Name: ");
        name.setBounds(150,150,150,30);
        name.setFont(new Font("Raleway",Font.BOLD,18));
        add(name);
        
        nametextfield = new JTextField();
        nametextfield.setBounds(280,150,250,30);
        add(nametextfield);
        
        JLabel mail = new JLabel("E-mail id: ");
        mail.setBounds(150,190,150,30);
        mail.setFont(new Font("Raleway",Font.BOLD,18));
        add(mail);
        
        mailtextfield = new JTextField();
        mailtextfield.setBounds(280,190,250,30);
        add(mailtextfield);
        
        JLabel phn = new JLabel("Phone No: ");
        phn.setBounds(150,230,150,30);
        phn.setFont(new Font("Raleway",Font.BOLD,18));
        add(phn);
        
        phntextfield = new JTextField();
        phntextfield.setBounds(280,230,250,30);
        add(phntextfield);
        
        JLabel pass = new JLabel("Password: ");
        pass.setFont(new Font("Raleway",Font.BOLD,18));
        pass.setBounds(150,270,250,30);
        add(pass);
        
        password = new JPasswordField();
        password.setBounds(280,270,250,30);
        add(password);
        
        terms = new JCheckBox("I agree to the Terms and Conditions");
        terms.setFont(new Font("Raleway",Font.BOLD,12));
        terms.setBounds(150,310,250,30);
        terms.setBackground(Color.WHITE);
        add(terms);
        
        JButton create = new JButton("Create Account");
        create.setFont(new Font("Raleway",Font.BOLD,16));
        create.setBounds(250,350,200,30);
        add(create); 
        
        
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        
        
    }
    
    public static void main (String args[]){
        
        new CreateAccount();
        
    } 
    
}

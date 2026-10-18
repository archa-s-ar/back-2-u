
package back2u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateAccount extends JFrame implements ActionListener{
    JTextField nametextfield,mailtextfield,phntextfield;
    JPasswordField password;
    JCheckBox terms;
    JButton create;
    
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
        
        create =new JButton("Create Account");
        create.setFont(new Font("Raleway",Font.BOLD,16));
        create.setBounds(250,350,200,30);
        create.addActionListener(this);
        add(create);
        
        /*create.addActionListener(e->{
         String passwordl = new String(password.getPassword());
         if(passwordl.length()<8){
             JOptionPane.showMessageDialog(this,"password must contain 8 characters");
         }
         else if(!passwordl.matches(".*[0-9].*")){
             JOptionPane.showMessageDialog( this,"password must contain a digit");
         }
         else{
             JOptionPane.showMessageDialog( this,"password created successfully");
             
         }
     });*/
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==create){
            
         String passwordl = new String(password.getPassword());
         String fname= nametextfield.getText();
         String email= mailtextfield.getText();
         String phone=phntextfield.getText();
         
         if(passwordl.length()<8){
             JOptionPane.showMessageDialog(this,"password must contain 8 characters");
         }
         else if(!passwordl.matches(".*[0-9].*")){
             JOptionPane.showMessageDialog( this,"password must contain a digit");
         }
         else{
             try{
                 Conn c= new Conn();
                 String query= "insert into accountdetails values('"+fname+"','"+email+"','"+phone+"','"+passwordl+"')";
                 c.s.executeUpdate(query);
                 JOptionPane.showMessageDialog( null,"Account created successfully");
             }
             catch(Exception e){
                 System.out.println(e);
             }
             
             
             
         }
        }
        
    }
    
    public static void main (String args[]){
        
        new CreateAccount();
        
    }
    
}

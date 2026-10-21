package back2u;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Login extends JFrame implements ActionListener{
     JTextField emailtxt;
    JPasswordField passtxt;
    JButton forgot,login,createacc;
    
    
    Login(){
        setTitle("Back2U-login");
        setSize(600,700);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245,242,255));
        //Heading
        JLabel heading=new JLabel("Welcome back!");
        heading.setFont(new Font("Times New Roman",Font.BOLD,34));
        heading.setBounds(60,60,450,45);
        add(heading);
        
        //Subtitle
        JLabel subtitle=new JLabel ("Login to Continue to Back2U");
        subtitle.setFont(new Font("Raleway",Font.BOLD,20));
        subtitle.setForeground(new Color(102,102,102));
        subtitle.setBounds(62,110,400,30);
        add(subtitle);
        
        JLabel email=new JLabel("Email id:");
        email.setFont(new Font("Raleway",Font.BOLD,16));
        email.setBounds(60,150,400,30);
        add(email);
        
        emailtxt=new JTextField();
        emailtxt.setBounds(160,150,350,30);
        add(emailtxt);
        
        JLabel password=new JLabel ("Password:");
        password.setFont(new Font("Raleway",Font.BOLD,16));
        password.setBounds(60,190,400,30);
        add(password);
        
        passtxt=new JPasswordField();
        passtxt.setBounds(160,190,350,30);
        add(passtxt);
        
        forgot=new JButton("Forgot password ?");
        forgot.setBounds(350,240,200,20);
        forgot.addActionListener(this);
        add(forgot);
        
        login=new JButton("LOGIN");
        login.setForeground(Color.WHITE);
        login.setBackground(new Color(91,72,157));
        login.setFont(new Font("Raleway",Font.BOLD,16));
        login.addActionListener(this);
        login.setBounds(170,300,230,30);
        add(login);
        
        JLabel orlabel=new JLabel("OR",SwingConstants.CENTER);
        orlabel.setFont(new Font("Times New Roman",Font.PLAIN,18));
        orlabel.setBounds(260,340,60,30);
        add(orlabel);
        
        createacc=new JButton("Create Account");
        createacc.setFont(new Font("Raleway",Font.BOLD,16));
        createacc.setBounds(170,390,230,30);
        createacc.setForeground(Color.WHITE);
        createacc.setBackground(new Color(91,72,157));
        createacc.addActionListener(this);
        add(createacc);
        
     
        
       setVisible(true);
    }
    

 public void actionPerformed(ActionEvent ev){
     if(ev.getSource()==login){
         String mail=emailtxt.getText().trim();
         String Pass=new String(passtxt.getPassword());
         if(mail.isEmpty()||Pass.isEmpty()){
             JOptionPane.showMessageDialog(this,"Please enter email and password");
         }
         else{
             if (mail.equals("admin@gmail.com")&&(Pass.equals("admin"))){
                 setVisible(false);
                 new AdminDashboard().setVisible(true);
             }
             
             else{
             try {
            Conn con = new Conn();

            String query = "SELECT * FROM accountdetails WHERE email='"
                    + mail + "' AND password='" + Pass + "'";

           java.sql.ResultSet rs = con.s.executeQuery(query);

            if (rs.next()) {
                JOptionPane.showMessageDialog(this,
                        "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid email or password");
            }

            con.c.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
             }
    

             
          }
        }
    
     
     else if (ev.getSource() == forgot) {
    dispose();
    new Forgotpassword();
     }
     else if (ev.getSource() == createacc) {
            setVisible(false);
            new CreateAccount().setVisible(true);
            dispose();
     }
     

}

 
 
  

 
public static void main(String args[]){
    new Login();
 
 }
}
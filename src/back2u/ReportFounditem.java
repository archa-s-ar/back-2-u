
package back2u;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;


public class ReportFounditem extends JFrame implements ActionListener{
    JComboBox<String>category;
    JTextField Itemtext, DateText, TimeText, Locationtext;
    JTextArea Descriptiontext, Uniquetext;
    JButton Cancel, Submit;
    ReportFounditem(){
        setTitle("Report Found Item");
        setSize(500,850);
        setLocation(300,0);
        setLayout(null);
        JLabel heading=new JLabel("Report Found Item");
        heading.setFont(new Font("Arial",Font.BOLD,26));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBounds(40,20,440,40);
        add(heading);
        
        JLabel subheading=new JLabel("Fill in the details below to report the item you found. ");
        subheading.setBounds(40,60,550,25);
        add(subheading);
        
        JLabel section1 =new JLabel("Item information");
        section1.setFont(new Font("raleway",Font.BOLD,20));
        section1.setBounds(40,140,250,30);
        add(section1);
        
        JLabel categoryLabel = new JLabel("Item Category");
        categoryLabel.setFont(new Font("raleway",Font.BOLD,16));
        categoryLabel.setBounds(40, 200, 110, 25);
        add(categoryLabel);
        
            category=new JComboBox<>(new String[]{
            "Select category","electronics","Bags","Books","Clothing","Accessories","Others"});
            category.setBounds(150,200,280,30);
            add(category);
            
            JLabel ItemName=new JLabel("Item name:");
            ItemName.setFont(new Font("raleway",Font.BOLD,16));
            ItemName.setBounds(40,270,100,30);
            add(ItemName);
            
            Itemtext=new JTextField();
            Itemtext.setBounds(150,270,280,30);
            add(Itemtext);
            
            JLabel Description=new JLabel("Description:");
            Description.setFont(new Font("raleway",Font.BOLD,16));
            Description.setBounds(40,320,100,30);
            add(Description);
            
            Descriptiontext=new JTextArea();
            Descriptiontext.setLineWrap(true);
            Descriptiontext.setWrapStyleWord(true);
            
            JScrollPane descScroll=new JScrollPane(Descriptiontext);
            descScroll.setBounds(150,320,280,70);
            add(descScroll);
            
            JLabel Section2=new JLabel("Found Details");
            Section2.setFont(new Font("raleway",Font.BOLD,20));
            Section2.setBounds(40,410,300,30);
            add(Section2);
            
            JLabel Datefound=new JLabel("Date found:");
            Datefound.setFont(new Font("raleway",Font.BOLD,16));
            Datefound.setBounds(40,450,300,30);
            add(Datefound);
            
            DateText=new JTextField();
            DateText.setToolTipText("Enter the date as dd-mm-yyyy");
            DateText.setBounds(150,450,300,30);
            add(DateText);
            
            JLabel Time=new JLabel("Time:");
            Time.setFont(new Font("raleway",Font.BOLD,16));
            Time.setBounds(40,490,300,30);
            add(Time);
            
             TimeText=new JTextField();
             TimeText.setBounds(150,490,300,30);
             add(TimeText);
             
             JLabel Location=new JLabel("Lost Location:");
             Location.setFont(new Font("raleway",Font.BOLD,16));
             Location.setBounds(40,540,300,30);
             add(Location);
             
             Locationtext=new JTextField();
             Locationtext.setBounds(150,540,300,30);
             add(Locationtext);
             
             JLabel Additional=new JLabel("Additional information");
             Additional.setFont(new Font("raleway",Font.BOLD,20));
             Additional.setBounds(40,610,300,30);
             add(Additional);
             
             JLabel Unique=new JLabel("<html>Additional details <br>like how it was kept:</html>"
                     );
             Unique.setFont(new Font("raleway",Font.BOLD,16));
             Unique.setBounds(40,650,165,40);
             add(Unique);
             
             Uniquetext=new JTextArea();
             Uniquetext.setLineWrap(true);
             Uniquetext.setWrapStyleWord(true);
             
             JScrollPane UniqueScroll=new JScrollPane(Uniquetext);
            UniqueScroll.setBounds(210,650,250,70);
            add(UniqueScroll);
             
             
             Cancel=new JButton("Cancel");
             Cancel.setBounds(210,730,110,35);
             Cancel.addActionListener(this);
             add(Cancel);
             
             Submit=new JButton("Submit");
             Submit.setBounds(340,730,120,35);
             Submit.addActionListener(this);
             add(Submit);
                     
  
            
 setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Cancel){
            Itemtext.setText("");
            Descriptiontext.setText("");
            DateText.setText("");
            TimeText.setText("");
            Locationtext.setText("");
            Uniquetext.setText("");
            category.setSelectedIndex(0);
            JOptionPane.showMessageDialog(this,"Form Cleared Successfully");
        }
        else if(e.getSource()==Submit){
            if(category.getSelectedIndex()==0||Itemtext.getText().trim().isEmpty()||Descriptiontext.getText().trim().isEmpty()||
                 DateText.getText().trim().isEmpty()|| TimeText.getText().trim().isEmpty()||Locationtext.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this,"Please fill in required fields");
                
            }
            else{
                JOptionPane.showMessageDialog(this,"Found item reported Successfully");
                LocalDateTime currentDateTime =LocalDateTime.now();
                try{
                 Conn c= new Conn();
                 String query = "INSERT INTO items(report_type,item_name,category,description,date_occured,time_occured,location,status,created_at) VALUES ("
                                    +"'FOUND','"
                    + Itemtext.getText().trim() + "', '"
                    + category.getSelectedItem().toString() + "', '"
                    + Descriptiontext.getText().trim() + "', '"
                    + DateText.getText().trim() + "', '"
                    + TimeText.getText().trim() + "', '"
                    + Locationtext.getText().trim() + "', 'OPEN', '"
                    + currentDateTime + "')";    
                    c.s.executeUpdate(query);
                  JOptionPane.showMessageDialog( null,"item reported successfully");
                }
                catch(Exception ex){
                     System.out.println(ex);
                }
            }       
                
    
        }
        
    }
     public static void main (String args[]){
        new ReportFounditem();
     }
}

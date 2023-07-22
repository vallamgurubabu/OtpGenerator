import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class OtpVerification extends JFrame implements ActionListener {
    JLabel l1;
    JLabel l2;
    JTextField phone;
    JButton Enter;
    JTextField verify_no;
    JButton Check;

    JDialog d1;
    JDialog d2;

    String generatedOtp;

    OtpVerification() {
        setTitle("OTP Verification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        l1 = new JLabel("Enter a Phone Number:");
        l1.setBounds(30, 30, 200, 30);
        phone = new JTextField(15);
        phone.setBounds(30, 70, 200, 30);
        Enter = new JButton("GET OTP");
        Enter.setBounds(250, 70, 100, 30);
        Enter.addActionListener(this); // Add action listener to "GET OTP" button

        l2 = new JLabel("Enter OTP:");
        l2.setBounds(30, 120, 200, 30);
        verify_no = new JTextField(6);
        verify_no.setBounds(30, 150, 200, 30);
        Check = new JButton("Verify");
        Check.setBounds(250, 150, 100, 30);
        Check.addActionListener(this); // Add action listener to "Verify" button
        Check.setEnabled(false); // Disable "Verify" button initially

        d1 = new JDialog();
        d1.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        d1.setSize(400, 400);
        d1.setVisible(true);
        d2 = new JDialog();
        d2.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        d2.setSize(400, 400);
        d2.setVisible(true);

        setVisible(true);
        setLayout(null);
        add(l1);
        add(l2);
        add(phone);
        add(Enter);
        add(verify_no);
        add(Check);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Enter) {
            try {
                String phoneNumber = phone.getText();
                if (isValidPhoneNumber(phoneNumber)) {
                    generatedOtp = generateOTP(6); // Generate a 6-digit OTP
                    JOptionPane.showMessageDialog(this, "OTP sent to " + phoneNumber + ": " + generatedOtp);
                    Check.setEnabled(true); // Enable "Verify" button after OTP is sent
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid phone number. Please enter a valid phone number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid phone number.");
            }
        } else if (e.getSource() == Check) {
            String enteredOtp = verify_no.getText();
            if (enteredOtp.equals(generatedOtp)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect OTP. Login failed.");
            }
        }
    }

    private String generateOTP(int len) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(otp);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {

         if (phoneNumber.length()!=10){
             return false;
         }
        // Your custom validation logic here (e.g., regex to check the format)
        return true;
    }

}

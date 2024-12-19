import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class CollegeEventManagementSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}

// Login Page
class LoginPage extends JFrame implements ActionListener {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnNext;

    private static final String USERNAME = "Madivalappa";
    private static final String PASSWORD = "7975234823";

    public LoginPage() {
        super("Login Page");
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("Login to Event System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(50, 30, 300, 30);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 80, 100, 20);
        txtUsername = new JTextField();
        txtUsername.setBounds(150, 80, 200, 20);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 120, 100, 20);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 120, 200, 20);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 160, 100, 30);

        btnNext = new JButton("Next");
        btnNext.setBounds(200, 160, 100, 30);
        btnNext.setEnabled(false);  // Initially disabled

        btnLogin.addActionListener(this);
        btnNext.addActionListener(this);

        add(lblTitle);
        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(btnNext);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                btnNext.setEnabled(true);  // Enable next button after successful login
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnNext) {
            dispose();
            new RegistrationPage();
        }
    }
}

// Registration Page
class RegistrationPage extends JFrame implements ActionListener {

    private JTextField txtEventName, txtEventDate, txtEventLocation, txtEventWinner;
    private JButton btnSubmitEvent, btnClearEvent, btnOpenFeedbackPage, btnBack, btnNext;
    private List<Event> events = new ArrayList<>();

    public RegistrationPage() {
        super("Event Registration Page");
        setLayout(null);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("Event Registration System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 20, 500, 30);

        JLabel lblEventName = new JLabel("Event Name:");
        lblEventName.setBounds(50, 70, 100, 20);
        txtEventName = new JTextField();
        txtEventName.setBounds(150, 70, 400, 20);

        JLabel lblEventDate = new JLabel("Event Date:");
        lblEventDate.setBounds(50, 110, 100, 20);
        txtEventDate = new JTextField();
        txtEventDate.setBounds(150, 110, 400, 20);

        JLabel lblEventLocation = new JLabel("Event Location:");
        lblEventLocation.setBounds(50, 150, 100, 20);
        txtEventLocation = new JTextField();
        txtEventLocation.setBounds(150, 150, 400, 20);

        JLabel lblEventWinner = new JLabel("Event Winner:");
        lblEventWinner.setBounds(50, 190, 100, 20);
        txtEventWinner = new JTextField();
        txtEventWinner.setBounds(150, 190, 400, 20);

        btnSubmitEvent = new JButton("Submit Event");
        btnSubmitEvent.setBounds(50, 240, 120, 30);

        btnClearEvent = new JButton("Clear Event");
        btnClearEvent.setBounds(180, 240, 120, 30);

        btnOpenFeedbackPage = new JButton("Feedback System");
        btnOpenFeedbackPage.setBounds(310, 240, 150, 30);

        btnBack = new JButton("Back");
        btnBack.setBounds(50, 300, 100, 30);

        btnNext = new JButton("Next");
        btnNext.setBounds(180, 300, 100, 30);

        btnSubmitEvent.addActionListener(this);
        btnClearEvent.addActionListener(this);
        btnOpenFeedbackPage.addActionListener(this);
        btnBack.addActionListener(this);
        btnNext.addActionListener(this);

        add(lblTitle);
        add(lblEventName);
        add(txtEventName);
        add(lblEventDate);
        add(txtEventDate);
        add(lblEventLocation);
        add(txtEventLocation);
        add(lblEventWinner);
        add(txtEventWinner);
        add(btnSubmitEvent);
        add(btnClearEvent);
        add(btnOpenFeedbackPage);
        add(btnBack);
        add(btnNext);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmitEvent) {
            String name = txtEventName.getText().trim();
            String date = txtEventDate.getText().trim();
            String location = txtEventLocation.getText().trim();
            String winner = txtEventWinner.getText().trim();

            if (name.isEmpty() || date.isEmpty() || location.isEmpty() || winner.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all event details!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            events.add(new Event(name, date, location, winner));
            JOptionPane.showMessageDialog(this, "Event added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else if (e.getSource() == btnClearEvent) {
            clearFields();
        } else if (e.getSource() == btnOpenFeedbackPage) {
            dispose();
            new FeedbackPage(events);
        } else if (e.getSource() == btnBack) {
            dispose();
            new LoginPage();
        } else if (e.getSource() == btnNext) {
            dispose();
            new FeedbackPage(events);
        }
    }

    private void clearFields() {
        txtEventName.setText("");
        txtEventDate.setText("");
        txtEventLocation.setText("");
        txtEventWinner.setText("");
    }
}

// Feedback Page
class FeedbackPage extends JFrame implements ActionListener {

    private JComboBox<String> eventDropdown, feedbackDropdown1, feedbackDropdown2;
    private JButton btnSubmitFeedback, btnClearFeedback, btnBack, btnNext;
    private List<Feedback> feedbackList = new ArrayList<>();

    public FeedbackPage(List<Event> events) {
        super("Feedback Page");
        setLayout(null);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("Feedback System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(100, 20, 300, 30);

        JLabel lblSelectEvent = new JLabel("Select Event:");
        lblSelectEvent.setBounds(50, 70, 100, 20);
        eventDropdown = new JComboBox<>();
        eventDropdown.setBounds(150, 70, 250, 20);

        for (Event event : events) {
            eventDropdown.addItem(event.getName());
        }

        JLabel lblFeedback1 = new JLabel("Rate Event Content:");
        lblFeedback1.setBounds(50, 110, 150, 20);
        feedbackDropdown1 = createRatingDropdown(210, 110);

        JLabel lblFeedback2 = new JLabel("Rate Event Organization:");
        lblFeedback2.setBounds(50, 150, 180, 20);
        feedbackDropdown2 = createRatingDropdown(210, 150);

        btnSubmitFeedback = new JButton("Submit Feedback");
        btnSubmitFeedback.setBounds(50, 200, 150, 30);

        btnClearFeedback = new JButton("Clear Feedback");
        btnClearFeedback.setBounds(220, 200, 150, 30);

        btnBack = new JButton("Back");
        btnBack.setBounds(50, 250, 100, 30);

        btnNext = new JButton("Next");
        btnNext.setBounds(220, 250, 100, 30);

        btnSubmitFeedback.addActionListener(this);
        btnClearFeedback.addActionListener(this);
        btnBack.addActionListener(this);
        btnNext.addActionListener(this);

        add(lblTitle);
        add(lblSelectEvent);
        add(eventDropdown);
        add(lblFeedback1);
        add(feedbackDropdown1);
        add(lblFeedback2);
        add(feedbackDropdown2);
        add(btnSubmitFeedback);
        add(btnClearFeedback);
        add(btnBack);
        add(btnNext);

        setVisible(true);
    }

    private JComboBox<String> createRatingDropdown(int x, int y) {
        JComboBox<String> dropdown = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            dropdown.addItem(String.valueOf(i));
        }
        dropdown.setBounds(x, y, 100, 20);
        return dropdown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmitFeedback) {
            String selectedEvent = (String) eventDropdown.getSelectedItem();
            String feedback1 = (String) feedbackDropdown1.getSelectedItem();
            String feedback2 = (String) feedbackDropdown2.getSelectedItem();

            if (selectedEvent != null && feedback1 != null && feedback2 != null) {
                feedbackList.add(new Feedback(selectedEvent, feedback1, feedback2));
                JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == btnClearFeedback) {
            feedbackDropdown1.setSelectedIndex(0);
            feedbackDropdown2.setSelectedIndex(0);
        } else if (e.getSource() == btnBack) {
            dispose();
            new RegistrationPage();
        } else if (e.getSource() == btnNext) {
            JOptionPane.showMessageDialog(this, "Thank you for your feedback!", "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}

// Supporting Classes
class Event {
    private String name, date, location, winner;

    public Event(String name, String date, String location, String winner) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.winner = winner;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getWinner() {
        return winner;
    }
}

class Feedback {
    private String eventName, feedback1, feedback2;

    public Feedback(String eventName, String feedback1, String feedback2) {
        this.eventName = eventName;
        this.feedback1 = feedback1;
        this.feedback2 = feedback2;
    }
}

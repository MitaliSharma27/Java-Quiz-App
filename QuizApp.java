import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp {
    private JFrame frame;
    private JPanel panel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton startButton;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton nextButton;
    private int currentQuestionIndex;
    private String selectedField;
    private String[][] quizData;
    private int score;

    public QuizApp() {
        frame = new JFrame("Java Quiz App");
        panel = new JPanel();
        nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField();
        startButton = new JButton("Start Quiz");
        questionLabel = new JLabel();
        optionButtons = new JRadioButton[4];
        nextButton = new JButton("Next");
        currentQuestionIndex = 0;
        score = 0;

        initializeUI();
    }

    private void initializeUI() {
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new GridLayout(7, 1));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStartButton();
            }
        });

        frame.add(panel);
    }

    private void handleStartButton() {
        String name = nameField.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter your name.");
        } else {
            panel.remove(nameLabel);
            panel.remove(nameField);
            panel.remove(startButton);

            panel.add(questionLabel);
            for (int i = 0; i < 4; i++) {
                optionButtons[i] = new JRadioButton();
                panel.add(optionButtons[i]);
            }
            panel.add(nextButton);

            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAnswer();
                    currentQuestionIndex++;
                    if (currentQuestionIndex < quizData.length) {
                        showQuestion();
                    } else {
                        showResult();
                    }
                }
            });

            frame.revalidate();
            frame.repaint();

            askField();
        }
    }

    private void askField() {
        String[] fields = {"Computer Science", "Mechanical Engineering", "Electrical Engineering",
                "Communication Engineering", "Agriculture Engineering", "Aerospace Engineering"};

        selectedField = (String) JOptionPane.showInputDialog(frame, "Choose a field:", "Field Selection",
                JOptionPane.QUESTION_MESSAGE, null, fields, fields[0]);

        if (selectedField != null) {
            loadQuizData();
            showQuestion();
        } else {
            frame.dispose();
        }
    }

    private void loadQuizData() {
        switch (selectedField) {
            case "Computer Science":
                quizData = new String[][]{
                        {"What is the primary purpose of an operating system?", "Run applications", "Manage hardware resources",
                            "Connect to the internet", "Play games", "Manage hardware resources"},
                    {"Which programming language is known for its portability?", "Java", "C++", "Python", "Assembly", "Java"},
                    {"What is the result of 2 + 2 in binary?", "100", "10", "111", "1000", "100"},
                    {"What does HTML stand for?", "Hyper Text Markup Language", "High Tech Multi Language", "Hyperlink and Text Markup Language",
                            "Hyper Transfer Markup Language", "Hyper Text Markup Language"},
                    {"In object-oriented programming, what is encapsulation?", "Hiding the implementation details", "Creating multiple instances",
                            "Inheriting from multiple classes", "Dynamic method invocation", "Hiding the implementation details"},
                    {"What is the purpose of the 'finally' block in Java?", "To catch exceptions", "To handle normal program flow",
                            "To ensure code is always executed", "To declare final variables", "To ensure code is always executed"},
                    {"What is the capital of Japan?", "Beijing", "Tokyo", "Seoul", "Bangkok", "Tokyo"},
                    {"Who is the author of 'To Kill a Mockingbird'?", "George Orwell", "J.K. Rowling", "Harper Lee", "Ernest Hemingway", "Harper Lee"},
                    {"What is the main function of an operating system?", "Memory management", "File system management", "Task scheduling", "All of the above", "All of the above"},
                    {"What is the purpose of the 'break' statement in Java?", "Terminate the loop", "Skip the current iteration", "Exit the program", "Resume the next iteration", "Terminate the loop"}
                };
                break;
            case "Mechanical Engineering":
                quizData = new String[][]{
                        {"What is the primary function of a gearbox?", "Increase speed", "Decrease speed", "Transfer power", "Store energy", "Transfer power"},
                    {"Which law of thermodynamics states that energy cannot be created or destroyed?", "First law", "Second law", "Zeroth law", "Third law", "First law"},
                    {"What is the SI unit of force?", "Newton", "Watt", "Joule", "Pascal", "Newton"},
                    {"What does CAD stand for in the context of engineering?", "Computer-Aided Design", "Computer-Aided Drafting", "Computer Analysis and Design",
                            "Computer Algorithm for Design", "Computer-Aided Design"},
                    {"What is the purpose of a flywheel in a machine?", "Store and release energy", "Increase friction", "Control temperature", "Generate electricity", "Store and release energy"},
                    {"Which material is commonly used for gears due to its hardness?", "Aluminum", "Copper", "Steel", "Plastic", "Steel"},
                    {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "Paris"},
                    {"Who is known as the 'Father of Modern Physics'?", "Isaac Newton", "Albert Einstein", "Galileo Galilei", "Niels Bohr", "Albert Einstein"},
                    {"What is the function of a camshaft in an engine?", "Control fuel injection", "Control valve timing", "Generate electricity", "Transfer motion", "Control valve timing"},
                    {"What is the speed of light in a vacuum?", "300,000 km/s", "150,000 km/s", "450,000 km/s", "600,000 km/s", "300,000 km/s"}
                };
                break;
            case "Electrical Engineering":
                quizData = new String[][]{
                        {"What is the unit of electrical resistance?", "Volt", "Ohm", "Ampere", "Watt", "Ohm"},
                    {"What is the function of a capacitor?", "Store electrical energy", "Amplify signals", "Generate heat", "Control resistance", "Store electrical energy"},
                    {"Which component is used to amplify a weak electrical signal?", "Resistor", "Capacitor", "Inductor", "Transistor", "Transistor"},
                    {"What is the purpose of a transformer?", "Convert DC to AC", "Step up or step down voltage", "Store electrical charge", "Generate electrical waves", "Step up or step down voltage"},
                    {"What is Kirchhoff's voltage law?", "The total current entering a junction is equal to the total current leaving the junction",
                            "The sum of the voltages in any closed loop is equal to the sum of the applied voltages",
                            "The voltage across a resistor is equal to the product of its current and resistance",
                            "The voltage across a capacitor is equal to the product of its capacitance and the rate of change of voltage across it",
                            "The sum of the voltages in any closed loop is equal to the sum of the applied voltages"},
                    {"What is the frequency of a signal with a period of 0.02 seconds?", "20 Hz", "40 Hz", "50 Hz", "60 Hz", "50 Hz"},
                    {"What is the main function of a diode in an electrical circuit?", "Amplify signals", "Control voltage", "Generate heat", "Allow current flow in one direction", "Allow current flow in one direction"},
                    {"What is Ohm's Law?", "V = IR", "P = VI", "V = I/R", "I = V/R", "V = I/R"},
                    {"What is the purpose of a relay in an electrical system?", "Amplify signals", "Control voltage", "Protect against overcurrent", "Convert AC to DC", "Protect against overcurrent"},
                    {"Which type of circuit has only one path for current flow?", "Parallel circuit", "Series circuit", "Combination circuit", "Complex circuit", "Series circuit"}
                };
                break;
            case "Communication Engineering":
                quizData = new String[][]{
                        {"What is the purpose of modulation in communication?", "Increase signal strength",
                            "Decrease signal strength", "Encode information on a carrier signal", "Generate random signals",
                            "Encode information on a carrier signal"},
                    {"Which of the following is a digital modulation scheme?", "AM (Amplitude Modulation)",
                            "FM (Frequency Modulation)", "PM (Phase Modulation)", "QPSK (Quadrature Phase Shift Keying)",
                            "QPSK (Quadrature Phase Shift Keying)"},
                    {"What is the unit of frequency?", "Hertz", "Watt", "Volt", "Ampere", "Hertz"},
                    {"What is the range of frequencies for the electromagnetic spectrum?",
                            "0 Hz to 1 MHz", "3 kHz to 300 GHz", "1 GHz to 10 GHz", "10 MHz to 100 MHz", "3 kHz to 300 GHz"},
                    {"Which device is used to combine signals from different channels in communication systems?",
                            "Amplifier", "Oscillator", "Mixer", "Filter", "Mixer"},
                    {"What is the Shannon-Hartley theorem related to in communication engineering?",
                            "Frequency modulation", "Amplitude modulation", "Information theory", "Signal processing", "Information theory"},
                    {"What is the main advantage of fiber-optic communication?",
                            "High data transmission rate", "Low cost", "Easy installation", "Short range", "High data transmission rate"},
                    {"What is the purpose of a router in computer networks?",
                            "Connects different networks", "Amplifies signals", "Stores data permanently", "Generates random IP addresses", "Connects different networks"},
                    {"Which wireless communication technology is used for short-range communication between devices like smartphones and headphones?",
                            "Wi-Fi", "Bluetooth", "3G", "4G", "Bluetooth"},
                    {"What is the function of a firewall in network security?",
                            "Filter and control incoming and outgoing network traffic",
                            "Boost internet speed", "Encrypt data", "Detect and remove viruses",
                            "Filter and control incoming and outgoing network traffic"}
                };
                break;
            case "Agriculture Engineering":
                quizData = new String[][]{
                        {"What is the primary purpose of a plough in agriculture?", "Harvest crops",
                            "Irrigate fields", "Prepare the soil for planting", "Store grains",
                            "Prepare the soil for planting"},
                    {"Which machine is used for cutting and gathering crops such as wheat or rice?",
                            "Tractor", "Combine harvester", "Seeder", "Sprayer", "Combine harvester"},
                    {"What is the main function of a drip irrigation system?",
                            "Flood the entire field", "Provide controlled water directly to the plant roots",
                            "Aerate the soil", "Create furrows for planting",
                            "Provide controlled water directly to the plant roots"},
                    {"Which of the following is a greenhouse gas emitted from agricultural activities?",
                            "Methane", "Ozone", "Nitrogen", "Carbon monoxide", "Methane"},
                    {"What is precision farming?", "Using advanced technology for precise and controlled agricultural practices",
                            "Large-scale traditional farming", "Hand cultivation", "Natural farming without technology",
                            "Using advanced technology for precise and controlled agricultural practices"},
                    {"Which type of soil is ideal for most crop plants?", "Sandy soil", "Clayey soil", "Loamy soil", "Rocky soil", "Loamy soil"},
                    {"What is the purpose of a seed drill in agriculture?", "Planting seeds at uniform depth and spacing",
                            "Watering crops", "Harvesting fruits", "Removing weeds", "Planting seeds at uniform depth and spacing"},
                    {"Which crop rotation technique involves growing different crops in a planned sequence over several seasons?",
                            "Monoculture", "Polyculture", "Cover cropping", "Crop rotation", "Crop rotation"},
                    {"What is the purpose of a pesticide in agriculture?", "Fertilize soil", "Control weeds",
                            "Protect crops from pests", "Increase crop yield", "Protect crops from pests"},
                    {"What is the role of nitrogen-fixing bacteria in agriculture?",
                            "Convert atmospheric nitrogen into a form plants can use",
                            "Decompose organic matter in the soil", "Control soil erosion",
                            "Increase soil acidity", "Convert atmospheric nitrogen into a form plants can use"}
                };
                break;
            case "Aerospace Engineering":
                quizData = new String[][]{
                       {"What is the primary function of an aircraft wing?", "Provide lift", "Control pitch",
                            "Generate thrust", "Stabilize in roll", "Provide lift"},
                    {"Which law of motion is fundamental to understanding the principles of flight?",
                            "Newton's First Law", "Newton's Second Law", "Newton's Third Law", "Law of Inertia",
                            "Newton's First Law"},
                    {"What is the purpose of the ailerons on an aircraft?",
                            "Control pitch", "Control roll", "Control yaw", "Provide lift",
                            "Control roll"},
                    {"Which type of aircraft propulsion system relies on the ejector effect to produce thrust?",
                            "Jet engine", "Turbofan engine", "Ramjet engine", "Rocket engine",
                            "Ramjet engine"},
                    {"What is the speed of sound at sea level in dry air?", "340 m/s", "300 m/s",
                            "400 m/s", "450 m/s", "340 m/s"},
                    {"Which NASA space shuttle was the first to fly in space?",
                            "Discovery", "Challenger", "Columbia", "Atlantis",
                            "Columbia"},
                    {"What is the purpose of a vertical stabilizer on an aircraft?",
                            "Control pitch", "Control roll", "Control yaw", "Provide lift",
                            "Control yaw"},
                    {"Which planet is known as the 'Red Planet'?", "Earth", "Mars",
                            "Jupiter", "Venus", "Mars"},
                    {"What is the main function of an aircraft fuselage?",
                            "House the cockpit and passengers", "Generate thrust",
                            "Stabilize in pitch", "Provide lift",
                            "House the cockpit and passengers"}
                };
                break;
            default:
                quizData = new String[][]{{"No questions available for this field."}};
        }
    }

    private void showQuestion() {
        String[] questionData = quizData[currentQuestionIndex];
        questionLabel.setText(questionData[0]);

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(questionData[i + 1]);
            optionButtons[i].setSelected(false);
        }
    }

    private void checkAnswer() {
        String userAnswer = "";
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                userAnswer = optionButtons[i].getText();
                break;
            }
        }

        String correctAnswer = quizData[currentQuestionIndex][5];
        if (userAnswer.equals(correctAnswer)) {
            score++;
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(frame, "Quiz completed!\nYour score: " + score + " out of " + quizData.length);
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApp().frame.setVisible(true);
            }
        });
    }
}
